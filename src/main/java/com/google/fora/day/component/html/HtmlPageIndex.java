package com.google.fora.day.component.html;

import com.google.fora.day.component.parser.HtmlParser;
import com.google.fora.day.exception.MalformedException;
import com.google.fora.day.exception.OpenUrlException;
import com.google.fora.day.model.HtmlLink;
import com.google.fora.day.model.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class HtmlPageIndex {

    private int newIndexedPageCount = 0;
    private int newIndexedWordCount = 0;

    private final List<HtmlPage> htmlPages;
    private final HtmlParser htmlParser;

    @Autowired
    protected HtmlPageIndex(@Qualifier("editorHtmlParser") HtmlParser htmlParser) {

        this.htmlParser = htmlParser;
        this.htmlPages = new ArrayList<>();
    }

    public int newIndexedPageCount(){

        newIndexedPageCount = allIndexedPageCount() - newIndexedPageCount;
        return newIndexedPageCount;
    }

    public int newIndexedWordCount(){

        newIndexedWordCount = allIndexedWordCount() - newIndexedWordCount;
        return newIndexedWordCount;
    }

    public int allIndexedPageCount(){

        int pageCount = 0;
        for (HtmlPage htmlPage :
                htmlPages) {
            pageCount += htmlPage.getLinks().size();
        }
        return pageCount;
    }

    public int allIndexedWordCount(){

        int wordCount = 0;
        for (HtmlPage htmlPage :
                htmlPages) {
            wordCount += htmlPage.getWords().size();
        }
        return wordCount;
    }

    public void clearAllHtmlPages() {

        newIndexedPageCount = 0;
        newIndexedWordCount = 0;

        getHtmlPages().clear();
    }

    public void indexPage(String urlPage) {
        indexPage(urlPage, 3);
    }

    List<HtmlPage> getHtmlPages() {
        return htmlPages;
    }

    private void indexPage(String urlPage, int depth_level) {

        if (depth_level == 0) {
            return;
        }

        HtmlPage htmlPage = parseUrl(urlPage);

        if (htmlPage != null) {

            htmlPages.add(htmlPages.size(), htmlPage);

            showPageContent(htmlPage, urlPage);

            final List<HtmlLink> links = htmlPage.getLinks();

            for (HtmlLink link :
                    links) {

                if (depth_level != 2 || !link.getLink().equals(urlPage)) {
                    indexPage(link.getLink(), depth_level - 1);
                }
            }
        }
    }

    private HtmlPage parseUrl(String link) {

        try {
            return htmlParser.parseUrl(link);
        } catch (OpenUrlException | MalformedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void showPageContent(HtmlPage htmlPage, String urlPage) {

        System.out.println("*************************************************");
        System.out.println("Root Page Name: " + urlPage);
        System.out.println("Root Page Title: " + htmlPage.getTitle());
        System.out.println("Root Page Words: " + htmlPage.getWords());
        System.out.println("Root Page Links: " + htmlPage.getLinks());
        System.out.println("*************************************************");
        System.out.println();
    }
}
