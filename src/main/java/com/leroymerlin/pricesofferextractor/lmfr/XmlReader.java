package com.leroymerlin.pricesofferextractor.lmfr;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.logging.Logger;

@Slf4j
public class XmlReader {
    String offerCode = "";
    String nameDefaultFile = "src/main/resources/static/xml/XML_CONF.xml";


    /**
     * @return offerCode;
     */
    public String XmlReader() {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();


            /**
             * xmlDocument correspond au document xml parsé
             */
            try {
                Document xmlDocument = dBuilder.parse(nameDefaultFile);
            } catch (Exception e) {
                Logger.getLogger(e.getMessage());
            }


            /**
             * Recherche du resultat du xpath dans le xmlDocument
             */
            String expression = "//*[@cpe=\"CPE.Settings.Session.CodeOffre\"]/@value";
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xPath.compile(expression);
            /**
             * @offerCode : Récupération du numéro de simulation de l'offre
             */
            offerCode = xPath.compile(expression).evaluate(dBuilder.parse(nameDefaultFile), XPathConstants.STRING).toString();
            log.info("*******************************");
            log.info("Numéro de simulation : " + offerCode);
            log.info("*******************************");
            /*JOptionPane.showMessageDialog(null, "Le fichier XML a bien été créé.", "Succès", JOptionPane.INFORMATION_MESSAGE);
*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerCode;
    }
}
