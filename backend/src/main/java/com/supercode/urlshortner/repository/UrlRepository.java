package com.supercode.urlshortner.repository;

import com.supercode.urlshortner.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,Long>
{
    public Url findOneByHash(String hash);
}
