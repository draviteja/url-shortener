package com.supercode.urlshortner.service;

import com.google.common.hash.Hashing;
import com.supercode.urlshortner.entity.Url;
import com.supercode.urlshortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UrlService {

    @Value("${baseUrl}")
    private String baseUrl;

    final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String getShortUrl(String longUrl) {
        String hash = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
        int counter = 0;
        while (urlRepository.findOneByHash(hash) != null && counter < 2)
        {
            hash = Hashing.murmur3_32().hashString(System.currentTimeMillis() + longUrl, StandardCharsets.UTF_8).toString();
            counter++;
        }
        if (counter == 2)
        {
            return null;
        }
        String shortUrl = baseUrl + hash;
        Url url = new Url(hash, shortUrl, longUrl);
        urlRepository.save(url);
        return shortUrl;
    }

    public String getLongUrl(String hash) {
        String longUrl = null;
        Url url = urlRepository.findOneByHash(hash);

        if(url!=null) {
            longUrl = url.getLongUrl();
            updateHitCount(url);
        }
        return longUrl;
    }

    @Async
    void updateHitCount(Url url) {
        url.setHits(url.getHits()+1);
        url.setLastAccessedAt(LocalDateTime.now());
        urlRepository.save(url);
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }
}
