package com.leroymerlin.pricesofferextractor.exports.services;

import com.leroymerlin.pricesofferextractor.exports.Utils;
import com.leroymerlin.pricesofferextractor.xmlToExcel.PriceLine;
import com.leroymerlin.pricesofferextractor.xmlToExcel.PriceLineListExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@Service
public class PriceLineService {

    @Autowired
    private Utils appUtil;

    public List<PriceLine> findAllPriceLine() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        PriceLineListExtractor priceLineListExtractor = new PriceLineListExtractor();
        List<PriceLine> priceLineExtracted = priceLineListExtractor.generatePriceLineList("20190415S38248");

        return priceLineExtracted;
        /*return IntStream.rangeClosed(1, 20)
                .mapToObj(i -> new User(priceLineExtracted))
                .collect(Collectors.toList());*/

    }
}
