package com.supercode.urlshortner.controller;

import com.supercode.urlshortner.entity.Url;
import com.supercode.urlshortner.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;

@RestController
@CrossOrigin(origins = "*")
public class UrlController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UrlValidator urlValidator = UrlValidator.getInstance();
    private UrlService urlService;

    @Autowired
    public UrlController(UrlService shortnerService)
    {
        this.urlService = shortnerService;
    }

    @GetMapping("/urls")
    public List<Url> getAllUrls(){
        List<Url> urls = urlService.getAllUrls();
        return urls;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createShortUrl(@RequestBody Url url)
    {
        String longUrl = url.getLongUrl();
        if (!urlValidator.isValid(longUrl))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // HTTP 400
        }
        String shortUrl = urlService.getShortUrl(longUrl);
        return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);  // HTTP 201
    }

    @RequestMapping(value = "/{hash}", method = RequestMethod.GET)
    public void redirect(@PathVariable String hash, HttpServletResponse response) throws IOException
    {
        if (hash == null || hash.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);  // HTTP 400
        }
        String longUrl = urlService.getLongUrl(hash);
        if (longUrl == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);  // HTTP 404
        } else {
            response.sendRedirect(longUrl);
        }
    }


}
