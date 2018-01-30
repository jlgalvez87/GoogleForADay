package com.google.fora.day.controller;

import com.google.fora.day.component.html.HtmlPageSearch;
import com.google.fora.day.model.HtmlLinkFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final HtmlPageSearch htmlPageSearch;

    @Autowired
    public SearchController(HtmlPageSearch htmlPageSearch) {
        this.htmlPageSearch = htmlPageSearch;
    }

    @RequestMapping("/")
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public String findOccurrences(Model model, @RequestParam("word") String word) {

        final List<HtmlLinkFound> wordOccurrence = htmlPageSearch.findWordOccurrence(word);

        model.addAttribute("occurrences", wordOccurrence.size());
        model.addAttribute("links", wordOccurrence);

        return "search";
    }

}
