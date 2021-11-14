package com.supercode.urlshortner.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Url
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "shortUrl")
    private String shortUrl;

    @Column(name = "longUrl")
    private String longUrl;

    @Column(name = "hits")
    private Integer hits;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "lastAccessedAt")
    private LocalDateTime lastAccessedAt;

    public Url() {
    }

    public Url(String hash, String shortUrl, String longUrl)
    {
        this.hash = hash;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.hits = 0;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString()
    {
        return String.format("Url[id=%d, hash='%s', shortUrl='%s', longUrl='%s']", id, hash, shortUrl, longUrl);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
}