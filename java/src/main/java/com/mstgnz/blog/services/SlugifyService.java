package com.mstgnz.blog.services;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;

@Service
public class SlugifyService {

    private Slugify slugify;

    public SlugifyService() {
        this.slugify = new Slugify();
    }

    public String getSlug(String text){
        return slugify.slugify(text);
    }
}
