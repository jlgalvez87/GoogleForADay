package com.google.fora.day.model;

public class HtmlLinkFound implements Comparable<HtmlLinkFound> {

    final String link;
    final String title;
    final String wordToFind;
    final int occurrences;

    public HtmlLinkFound(String link, String title,
                         String wordToFind, int occurrences) {
        this.link = link;
        this.title = title;
        this.wordToFind = wordToFind;
        this.occurrences = occurrences;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getWordToFind() {
        return wordToFind;
    }

    public int getOccurrences() {
        return occurrences;
    }

    @Override
    public int compareTo(HtmlLinkFound o) {
        return o.getOccurrences() - getOccurrences();
    }

    @Override
    public String toString() {

        return "Link: " + getLink() +
                ", Title: " + getTitle() +
                ", WordToFind: " + getWordToFind() +
                ", Ocurrences: " + getOccurrences();
    }
}
