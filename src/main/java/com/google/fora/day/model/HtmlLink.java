package com.google.fora.day.model;

public final class HtmlLink {

    private final String link;

    public HtmlLink(String link){
        this.link = replaceInvalidChar(link);
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return getLink();
    }

    private String replaceInvalidChar(String link){

        link = link.replaceAll("'", "");
        link = link.replaceAll("\"", "");

        return link;
    }

}
