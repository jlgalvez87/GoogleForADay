package com.google.fora.day.component.parser.impl;

import com.google.fora.day.component.core.URLContentReader;
import com.google.fora.day.model.HtmlLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

@Component("editorHtmlParser")
public class EditorKitHtmlParserImpl extends GenericHtmlParserImpl {

    @Autowired
    public EditorKitHtmlParserImpl(URLContentReader urlContentReader) {
        super(urlContentReader);
    }

    @Override
    protected List<HtmlLink> getLinks(String content) {

        final LinkParserCallback linkParserCallback = new LinkParserCallback();

        if (this.parse(new StringReader(content), linkParserCallback, new ParserDelegator())) {
            return linkParserCallback.getLinks();
        }
        return new LinkedList<>();
    }

    @Override
    protected List<String> getWords(String content) {

        final WordParserCallback wordParserCallback = new WordParserCallback();

        if (this.parse(new StringReader(content), wordParserCallback, new ParserDelegator())) {
            return wordParserCallback.getWords();
        }
        return new LinkedList<>();
    }

    private boolean parse(Reader reader, ParserCallback parserCallback, ParserDelegator parserDelegator) {

        try {
            parserDelegator.parse(reader, parserCallback, true);
            reader.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private class WordParserCallback extends ParserCallback {

        private final List<String> words;

        WordParserCallback() {
            words = new LinkedList<>();
        }

        List<String> getWords() {
            return words;
        }

        @Override
        public void handleText(char[] data, int pos) {

            StringTokenizer tokenizer = new StringTokenizer(String.valueOf(data));

            while (tokenizer.hasMoreTokens()){

                final String token = tokenizer.nextToken();

                if (!token.trim().isEmpty()){
                    words.add(words.size(), token);
                }
            }

            super.handleText(data, pos);
        }
    }

    private class LinkParserCallback extends ParserCallback {

        private final List<HtmlLink> links;

        LinkParserCallback() {
            links = new LinkedList<>();
        }

        List<HtmlLink> getLinks() {
            return links;
        }

        @Override
        public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {

            if(t == HTML.Tag.A) {
                String link = (String) a.getAttribute(HTML.Attribute.HREF);
                if(link != null && link.matches(URL_PATTERN)) {
                    addLink(link);
                }
            }

            super.handleStartTag(t, a, pos);
        }

        private void addLink(String url){

            String url_root = getUrlSimple(urlString);
            url = getUrlSimple(url);

            if (!url_root.equals(url) && !containHtmlLink(links, url)){
                links.add(links.size(), new HtmlLink(url));
            }
        }
    }
}
