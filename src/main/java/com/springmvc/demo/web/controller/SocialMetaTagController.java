package com.springmvc.demo.web.controller;

import com.springmvc.demo.domain.SocialMetaTag;
import com.springmvc.demo.service.SocialMetaTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/meta")
public class SocialMetaTagController {

    @Autowired
    SocialMetaTagService socialMetaTagService;

    @PostMapping("/info")
    public ResponseEntity<SocialMetaTag> metaTag(@RequestParam("url") String url){


        SocialMetaTag socialMetaTag = socialMetaTagService.getSocialMetaTagByUrl(url);

        return socialMetaTag != null ? ResponseEntity.ok(socialMetaTag) : ResponseEntity.notFound().build();
    }
}
