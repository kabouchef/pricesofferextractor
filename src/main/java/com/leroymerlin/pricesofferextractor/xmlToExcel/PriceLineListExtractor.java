package com.leroymerlin.pricesofferextractor.xmlToExcel;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriceLineListExtractor {
    public List<PriceLine> generatePriceLineList(String simulationCode) throws ParserConfigurationException,
            IOException, SAXException, XPathExpressionException {

        String directory = "src/main/resources/static/xls/";

        String nameDefaultFile = "src/main/resources/static/xml/" + simulationCode + ".xml";
        String priceLines = "";
        List<String> priceList = new ArrayList<String>();
        List<PriceLine> priceLineList = new ArrayList<PriceLine>();

        String[] priceElement = new String[0];

        // Parsing XML Document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(nameDefaultFile);

        String[] entete = {"IDENTIFIANT", "DETAIL_PRESTATION", "QUANTITE", "TARIF_UNITAIRE",
                "TARIF_PRESTATION", "TYPE_PRESTATION", "PRESTATION_DE", "TVA_REDUITE", "TVA_INTER",
                "TVA_NORMALE", "CODE_49", "COD_TYPE_PRESTATION", "TEMP_POSE", "ORDRE"};

        /**
         * Récupération du nombre de lignes de prix
         */
        String nbLines = "count(//*[@name='fpPriceHtChiffragePrecisSurrogate']" + "//Value[not(preceding-sibling::Value = .)])";
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression nbLine = xPath.compile(nbLines);
        String nbPrice = xPath.compile(nbLines).evaluate(xmlDocument, XPathConstants.STRING).toString();

        int nbPriceLines = Integer.parseInt(nbPrice);

        priceElement = new String[0];
        double totalPriceHT = 0;
        for (int i = 1; i < nbPriceLines + 1; i++) {
            /**
             * Récupération des lignes de prix
             */
            String cible = "//*[@name='fpPriceHtChiffragePrecisSurrogate']//Value[" + i + "]/longTextValue";
            XPathExpression expr = xPath.compile(cible);

            priceLines = xPath.compile(cible).evaluate(xmlDocument, XPathConstants.STRING).toString();
            priceElement = priceLines.split("#");

            PriceLine priceLine = new PriceLine();
            if (priceElement.length >= 1) priceLine.setIdentifiant(priceElement[0]);
            if (priceElement.length >= 2) priceLine.setDetail_prestation(priceElement[1]);
            if (priceElement.length >= 3) priceLine.setQuantite(priceElement[2]);
            if (priceElement.length >= 4) priceLine.setTarif_unitaire(priceElement[3]);
            if (priceElement.length >= 5) priceLine.setTarif_prestation(priceElement[4]);
            if (priceElement.length >= 6) priceLine.setType_prestation(priceElement[5]);
            if (priceElement.length >= 7) priceLine.setPrestation_de(priceElement[6]);
            if (priceElement.length >= 8) priceLine.setTva_reduite(priceElement[7]);
            if (priceElement.length >= 9) priceLine.setTva_inter(priceElement[8]);
            if (priceElement.length >= 10) priceLine.setTva_normale(priceElement[9]);
            if (priceElement.length >= 11) priceLine.setCode_49(priceElement[10]);
            if (priceElement.length >= 12) priceLine.setCod_type_prestation(priceElement[11]);
            if (priceElement.length >= 13) priceLine.setTemp_pose(priceElement[12]);
            if (priceElement.length >= 14) priceLine.setOrdre(priceElement[13]);

            priceLineList.add(priceLine);

        }
        /**
         * Calcul of Total Prices (HT, TVA 5.5%, 10%, 20%)
         */
        TotalPrices totalPrices = new TotalPrices();
        totalPrices.getTotalPrice(priceLineList);
        priceLineList.stream()
                .forEach(x -> x.setTotalPriceHT(totalPrices.getTotalPriceHT()));
        priceLineList.stream()
                .forEach(x -> x.setTotalPriceTVAReduite(totalPrices.getTotalPriceTVAReduite()));
        priceLineList.stream()
                .forEach(x -> x.setTotalPriceTVAInter(totalPrices.getTotalPriceTVAInter()));
        priceLineList.stream()
                .forEach(x -> x.setTotalPriceTVANormale(totalPrices.getTotalPriceTVANormale()));

        return priceLineList;
    }
}
