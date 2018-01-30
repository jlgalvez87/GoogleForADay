package com.google.fora.day.controller;

import com.google.fora.day.component.html.HtmlPageIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private final HtmlPageIndex htmlPageIndex;

    @Autowired
    public IndexController(HtmlPageIndex htmlPageIndex) {
        this.htmlPageIndex = htmlPageIndex;
    }

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("newIndexedPage", htmlPageIndex.newIndexedPageCount());
        model.addAttribute("newIndexedWord", htmlPageIndex.newIndexedWordCount());
        model.addAttribute("allIndexedPage", htmlPageIndex.allIndexedPageCount());
        model.addAttribute("allIndexedWord", htmlPageIndex.allIndexedWordCount());

        return "index";
    }

    @RequestMapping(value = "/addUrl", method = RequestMethod.POST)
    public String add(@RequestParam("url") String urlPage) {

        if(urlPage != null && !urlPage.isEmpty()){
            if (!urlPage.contains("https://www.google.com") &&
                    !urlPage.contains("http://www.google.com")){
                htmlPageIndex.indexPage(urlPage);
            }
        }

        return "redirect:/";
    }

    @RequestMapping("/clearAll")
    public String clearAll() {

        htmlPageIndex.clearAllHtmlPages();

        return "redirect:/";
    }



}
