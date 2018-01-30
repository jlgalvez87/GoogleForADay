package com.google.fora.day.component.parser.impl;

import com.google.fora.day.component.core.URLContentReader;
import com.google.fora.day.component.parser.HtmlParser;
import com.google.fora.day.exception.MalformedException;
import com.google.fora.day.exception.OpenUrlException;
import com.google.fora.day.model.HtmlLink;
import com.google.fora.day.model.HtmlPage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GenericHtmlParserImpl implements HtmlParser {

    String urlString;
    final String URL_PATTERN = "^(?i)(https?)://.*$";

    private final URLContentReader urlContentReader;

    protected GenericHtmlParserImpl(URLContentReader urlContentReader) {
        this.urlContentReader = urlContentReader;
    }

    @Override
    public HtmlPage parseUrl(String urlString) throws OpenUrlException, MalformedException {

        this.urlString = urlString;

        String content = getUrlContentReader().getHtmlContent(urlString);

        if (content != null){

            HtmlPage htmlPage = new HtmlPage(new HtmlLink(urlString), getTitle(content));
            htmlPage.setLinks(getLinks(content));
            htmlPage.setWords(getWords(content));

            return htmlPage;
        }

        return null;
    }

    protected abstract List<HtmlLink> getLinks(String content);

    protected abstract List<String> getWords(String content);

    String getUrlSimple(String url){

        final String pattern = "/";
        String[] split = url.split(pattern);

        return split[0] + pattern + pattern + split[2];
    }

    boolean containHtmlLink(List<HtmlLink> list, String url){

        for (HtmlLink htmlLink :
                list) {
            if (htmlLink.getLink().equals(url)){
                return true;
            }
        }
        return false;
    }

    private String getTitle(String content) {

        Pattern p = Pattern.compile("<head>.*?<title>(.*?)</title>.*?</head>", Pattern.DOTALL);
        Matcher m = p.matcher(content);

        StringBuilder title = new StringBuilder();

        while (m.find()) {
            title.append(m.group(1).trim());
        }

        return title.toString();
    }

    private URLContentReader getUrlContentReader() {
        return urlContentReader;
    }
}
