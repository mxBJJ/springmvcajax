package com.springmvc.demo.service;

import com.springmvc.demo.domain.SocialMetaTag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.io.IOException;

@Service
public class SocialMetaTagService {

    private static Logger LOG = LoggerFactory.getLogger(SocialMetaTagService.class);

    public SocialMetaTag getSocialMetaTagByUrl(String url){
        SocialMetaTag og = getOpenGraphByUrl(url);
        SocialMetaTag twitter = getTwitterCardByUrl(url);

        if(!this.isEmpty(og)){
            if(!this.isEmpty(twitter)){
                if(og.getSite().isEmpty()){
                    return twitter;
                }else{
                    return og;
                }
            }else{
                return og;
            }
        }else if(!this.isEmpty(twitter)){
            return twitter;
        }

        return null;
    }

    private SocialMetaTag getOpenGraphByUrl(String url){
        SocialMetaTag socialMetaTag = new SocialMetaTag();

        try {
            Document document = Jsoup.connect(url).get();
            socialMetaTag.setTitle(document.head().select("meta[property=og:title]").attr("content"));
            socialMetaTag.setSite(document.head().select("meta[property=og:site_name]").attr("content"));
            socialMetaTag.setUrl(document.head().select("meta[property=og:url]").attr("content"));
            socialMetaTag.setImage(document.head().select("meta[property=og:image]").attr("content"));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e.getCause());
        }

        return socialMetaTag;
    }

    private SocialMetaTag getTwitterCardByUrl(String url){
        SocialMetaTag socialMetaTag = new SocialMetaTag();

        try {
            Document document = Jsoup.connect(url).get();
            socialMetaTag.setTitle(document.head().select("meta[name=twitter:title]").attr("content"));
            socialMetaTag.setSite(document.head().select("meta[name=twitter:name]").attr("content"));
            socialMetaTag.setUrl(document.head().select("meta[name=twitter:url]").attr("content"));
            socialMetaTag.setImage(document.head().select("meta[name=twitter:image]").attr("content"));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e.getCause());
        }

        return socialMetaTag;
    }

    public boolean isEmpty(SocialMetaTag obj){
        if(obj.getImage().isEmpty()) return true;
        if(obj.getTitle().isEmpty()) return true;
        if(obj.getUrl().isEmpty()) return true;

        return false;
    }
}
