package com.leroymerlin.pricesofferextractor.exports.controller.rest;

import com.leroymerlin.pricesofferextractor.exports.services.PriceLineService;
import com.leroymerlin.pricesofferextractor.xmlToExcel.PriceLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private PriceLineService priceLineService;

    /**
     * Handle request to the default page
     * @return
     */
    @GetMapping("/")
    public List<PriceLine> viewHome() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        return priceLineService.findAllPriceLine();
    }


}
