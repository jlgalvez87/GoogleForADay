package com.google.fora.day.component.parser;

import com.google.fora.day.exception.MalformedException;
import com.google.fora.day.exception.OpenUrlException;
import com.google.fora.day.model.HtmlPage;

public interface HtmlParser {

    HtmlPage parseUrl(String urlString) throws OpenUrlException, MalformedException;
}
