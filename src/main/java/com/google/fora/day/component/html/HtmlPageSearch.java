package com.google.fora.day.component.html;

import com.google.fora.day.model.HtmlLinkFound;
import com.google.fora.day.model.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class HtmlPageSearch {

    private final HtmlPageIndex htmlPageIndex;

    @Autowired
    public HtmlPageSearch(HtmlPageIndex htmlPageIndex) {
        this.htmlPageIndex = htmlPageIndex;
    }

    public List<HtmlLinkFound> findWordOccurrence(String wordToFind) {

        final  List<HtmlLinkFound> htmlLinkFounds = new LinkedList<>();
        final List<HtmlPage> htmlPages = htmlPageIndex.getHtmlPages();

        wordToFind = wordToFind.toLowerCase().trim();

        for (HtmlPage htmlPage :
                htmlPages) {

            final List<String> words = htmlPage.getWords();
            int occurrence = 0;

            for (String current_word :
                    words) {
                occurrence += current_word.toLowerCase().contains(wordToFind) ? 1 : 0;
            }

            if (occurrence != 0) {

                String title = htmlPage.getTitle();
                title = title.trim().isEmpty() ? "No Title" : title;

                HtmlLinkFound htmlLinkFound =
                        new HtmlLinkFound(htmlPage.getUrlHost().getLink(),
                                title, wordToFind, occurrence);

                htmlLinkFounds.add(htmlLinkFounds.size(), htmlLinkFound);
            }
        }

        Collections.sort(htmlLinkFounds);

        showAllOccurrences(htmlLinkFounds);

        return htmlLinkFounds;
    }

    private void showAllOccurrences(List<HtmlLinkFound> htmlLinkFounds) {

        for (HtmlLinkFound found :
                htmlLinkFounds) {
            System.out.println("*****************************************");
            System.out.println("Title: " + found.getTitle());
            System.out.println("Link: " + found.getLink());
            System.out.println("WordToFind: " + found.getWordToFind());
            System.out.println("Occurrences: " + found.getOccurrences());
            System.out.println("*****************************************");
        }
    }

}
