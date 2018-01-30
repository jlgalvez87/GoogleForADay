package com.google.fora.day.component.parser.impl;

import com.google.fora.day.component.core.URLContentReader;
import com.google.fora.day.model.HtmlLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("customHtmlParser")
public class CustomHtmlParserImpl extends GenericHtmlParserImpl {

    private Pattern patternTag, patternLink;

    private static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
    private static final String HTML_A_HREF_TAG_PATTERN =
            "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

    @Autowired
    public CustomHtmlParserImpl(URLContentReader urlContentReader) {
        super(urlContentReader);

        patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
        patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
    }

    @Override
    protected List<HtmlLink> getLinks(String content) {

        List<HtmlLink> result = new LinkedList<>();

        Matcher matcherTag = patternTag.matcher(content);

        while (matcherTag.find()) {

            String href = matcherTag.group(1); // href

            Matcher matcherLink = patternLink.matcher(href);

            while (matcherLink.find()) {

                String link = matcherLink.group(1);

                HtmlLink obj = new HtmlLink(link);

                if (obj.getLink().matches(URL_PATTERN)){

                    final String urlSimple = getUrlSimple(obj.getLink());

                    if (!containHtmlLink(result, urlSimple)){
                        result.add(result.size(), new HtmlLink(urlSimple));
                    }

                }
            }
        }

        return result;
    }

    @Override
    protected List<String> getWords(String content) {

        List<String> words = new LinkedList<>();

        String nohtml = content.replaceAll("<.*?>", " ");
        nohtml = nohtml.replaceAll("(\\s+)", " ");

        StringTokenizer tokenizer = new StringTokenizer(nohtml);

        while (tokenizer.hasMoreTokens()){
            words.add(words.size(), tokenizer.nextToken());
        }

        return words;
    }


}
