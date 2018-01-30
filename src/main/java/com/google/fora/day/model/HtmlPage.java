package com.google.fora.day.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HtmlPage implements Serializable {

    private final String title;
    private final HtmlLink urlHost;

    private List<String> words;
    private List<HtmlLink> links;

    public HtmlPage(HtmlLink urlHost, String title) {
        this.title = title;
        this.urlHost = urlHost;

        this.words = new ArrayList<>();
        this.links = new LinkedList<>();
    }

    public HtmlLink getUrlHost() {
        return urlHost;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getWords() {
        return words;
    }

    public List<HtmlLink> getLinks() {
        return links;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public void setLinks(List<HtmlLink> links) {
        this.links = links;
    }
}
