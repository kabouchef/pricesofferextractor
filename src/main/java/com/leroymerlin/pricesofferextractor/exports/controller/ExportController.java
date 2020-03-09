package com.leroymerlin.pricesofferextractor.exports.controller;

import com.leroymerlin.pricesofferextractor.exports.services.PriceLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

@Controller
public class ExportController {

    @Autowired
    private PriceLineService priceLineService;

    /**
     * Handle request to download an Excel document
     */
    @GetMapping("/download")
    public String download(Model model) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {

        model.addAttribute("priceLineService", priceLineService.findAllPriceLine());
        return "";
    }


}
