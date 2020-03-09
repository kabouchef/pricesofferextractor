package com.leroymerlin.pricesofferextractor.xmlToExcel;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLToExcel {
    public List<PriceLine> generateExcel(String simulationCode) throws ParserConfigurationException, IOException, SAXException {
        String directory = "src/main/resources/static/xls/";

        String nameDefaultFile = "src/main/resources/static/xml/" + simulationCode + ".xml";
        String priceLines = "";
        List<String> priceList = new ArrayList<String>();
        List<PriceLine> priceLineList = new ArrayList<PriceLine>();

        String[] priceElement = new String[0];

        try {// Creating a Workbook
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet spreadSheet = wb.createSheet(simulationCode);

            for (int i = 0; i < 14; i++) {
                if (i == 0) spreadSheet.setColumnWidth(i, 500 * 25);
                else if (i == 2 || i == 3) spreadSheet.setColumnWidth(i, 156 * 25);
                else spreadSheet.setColumnWidth(i, 256 * 25);
            }
            // Parsing XML Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(nameDefaultFile);

            /**
             * Defintion of Cells Style
             */

            // Style of Title Cell
            StyleOfCells customStyleTitle = new StyleOfCells();
            HSSFCellStyle styleTitle = customStyleTitle.getCustomStyleTitle(wb, spreadSheet);

            // Style of Title Cell
            StyleOfCells customStyleHead = new StyleOfCells();
            HSSFCellStyle styleHead = customStyleHead.getCustomStyleHead(wb);

            // Style of Global Content Cells
            StyleOfCells customStyleGlobalContent = new StyleOfCells();
            HSSFCellStyle styleGlobalContent = customStyleGlobalContent.getCustomGlobalContent(wb);

            // Style of Price Content Cells
            StyleOfCells customStylePriceContent = new StyleOfCells();
            HSSFCellStyle stylePriceContent = customStylePriceContent.getCustomPriceContent(wb);

            // Style of Quantifier Content Cells
            StyleOfCells customStyleQuantifyContent = new StyleOfCells();
            HSSFCellStyle styleQuantifyContent = customStyleQuantifyContent.getCustomQuantifyContent(wb);

            // Style of Head Total Price Effected Cells
            StyleOfCells customStyleHeadTotalPriceEffected = new StyleOfCells();
            HSSFCellStyle styleHeadTotalPriceEffected = customStyleHeadTotalPriceEffected.getCustomHeadTotalPriceEffected(wb);

            // Style of Total Price Effected Cells
            StyleOfCells customStyleTotalPriceEffected = new StyleOfCells();
            HSSFCellStyle styleTotalPriceEffected = customStyleTotalPriceEffected.getCustomTotalPriceEffected(wb);

            // Style of Head Total Price Cells
            StyleOfCells customStyleHeadTotalPrice = new StyleOfCells();
            HSSFCellStyle styleHeadTotalPrice = customStyleHeadTotalPrice.getCustomHeadTotalPrice(wb);

            // Style of Total Price Cells
            StyleOfCells customStyleTotalPrice = new StyleOfCells();
            HSSFCellStyle styleTotalPrice = customStyleTotalPrice.getCustomTotalPrice(wb);

            /**
             * Création of Title and Head Rows
             */
            // Creating Row of Title
            HSSFRow row0 = spreadSheet.createRow(0);
            HSSFCell cell = row0.createCell(0);
            cell.setCellValue("LEROY MERLIN - OAP - PRICE_FROM_" + simulationCode);
            cell.setCellStyle(styleTitle);

            //Entete
            HSSFRow row = spreadSheet.createRow(2);
            String[] head = {"IDENTIFIANT", "DETAIL_PRESTATION", "QUANTITE", "TARIF_UNITAIRE",
                    "TARIF_PRESTATION", "TYPE_PRESTATION", "PRESTATION_DE", "TVA_REDUITE", "TVA_INTER",
                    "TVA_NORMALE", "CODE_49", "COD_TYPE_PRESTATION", "TEMP_POSE", "ORDRE"};

            // Creating Row of Head
            for (int a = 0; a < head.length; a++) {
                cell = row.createCell(a);
                cell.setCellValue(head[a]);
                cell.setCellStyle(styleHead);
            }

            /**
             * Getting Price Lines
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
                 * Extraction of Price Lines from XML File
                 */
                String cible = "//*[@name='fpPriceHtChiffragePrecisSurrogate']//Value[" + i + "]/longTextValue";
                XPathExpression expr = xPath.compile(cible);

                HSSFRow row1 = spreadSheet.createRow(i + 2);
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

                for (int j = 0; j < priceElement.length; j++) {
                    cell = row1.createCell(j);

                    if (j == 3 || j == 4) {
                        double price = Double.parseDouble(priceElement[j]);
                        cell.setCellValue(price);
                        cell.setCellStyle(stylePriceContent);
                    } else if (j < 14 && (j == 2 || j > 6)) {
                        int quantity = Integer.parseInt(priceElement[j]);
                        cell.setCellValue(quantity);
                        cell.setCellStyle(styleQuantifyContent);
                    } else {
                        cell.setCellValue(priceElement[j]);
                        cell.setCellStyle(styleGlobalContent);
                    }
                }
            }
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


            Sheet sheet = wb.getSheetAt(0);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

            boolean tvaReduceAllowed = true;
            boolean tvaInterAllowed = true;

            for (int i = 4; i < nbPriceLines + 4; i++) {
                CellReference cellReferenceReduce = new CellReference("H" + i);
                Row lineReduce = sheet.getRow(cellReferenceReduce.getRow());
                Cell celluleReduce = lineReduce.getCell(cellReferenceReduce.getCol());
                CellValue cellValueReduce = evaluator.evaluate(celluleReduce);
                String cReduceString = celluleReduce.toString();

                CellReference cellReferenceInter = new CellReference("I" + i);
                Row ligneInter = sheet.getRow(cellReferenceReduce.getRow());
                Cell celluleInter = ligneInter.getCell(cellReferenceInter.getCol());
                CellValue cellValueInter = evaluator.evaluate(celluleInter);
                String cInterString = celluleInter.toString();

                if (cReduceString.equals("0.0")) tvaReduceAllowed = false;
                if (cInterString.equals("0.0")) tvaInterAllowed = false;

            }
            /**
             * Calcul of HT Price
             */
            int index = nbPriceLines + 5;
            HSSFRow rowTotalHT = spreadSheet.createRow(index);
            cell = rowTotalHT.createCell(3);
            cell.setCellValue("Prix HT");
            cell.setCellStyle(styleHeadTotalPrice);
            cell = rowTotalHT.createCell(4);
            int indexHT = nbPriceLines + 3;
            cell.setCellFormula("SUM(E4:E" + indexHT + ")");
            cell.setCellStyle(styleTotalPrice);

            /**
             * Calcul of TVA 5.5% Price
             */
            index++;
            HSSFRow rowTotalReduite = spreadSheet.createRow(index);
            cell = rowTotalReduite.createCell(3);
            cell.setCellValue("Prix TVA 5.5%");
            if (tvaReduceAllowed == false) cell.setCellStyle(styleHeadTotalPrice);
            else cell.setCellStyle(styleHeadTotalPriceEffected);
            cell = rowTotalReduite.createCell(4);
            cell.setCellFormula("E" + index + "*1.055");
            Integer indexSortie = index;
            cell.setCellStyle(styleTotalPrice);
            if (tvaReduceAllowed == false) rowTotalReduite.setZeroHeight(true);
            else cell.setCellStyle(styleTotalPriceEffected);

            /**
             * Calcul of TVA 10% Price
             */
            index++;
            HSSFRow rowTotalInter = spreadSheet.createRow(index);
            cell = rowTotalInter.createCell(3);
            cell.setCellValue("Prix TVA 10%");
            if (tvaReduceAllowed == false) cell.setCellStyle(styleHeadTotalPriceEffected);
            else cell.setCellStyle(styleHeadTotalPrice);
            cell = rowTotalInter.createCell(4);
            cell.setCellFormula("E$" + indexSortie + "*1.1");
            cell.setCellStyle(styleTotalPrice);
            if (tvaInterAllowed == false) rowTotalInter.setZeroHeight(true);
            else cell.setCellStyle(styleTotalPriceEffected);
            /**
             * Calcul of TVA 20% Price
             */
            index++;
            HSSFRow rowTotalNormale = spreadSheet.createRow(index);
            cell = rowTotalNormale.createCell(3);
            cell.setCellValue("Prix TVA 20%");
            if (tvaReduceAllowed == false && tvaInterAllowed == false) cell.setCellStyle(styleHeadTotalPriceEffected);
            else cell.setCellStyle(styleHeadTotalPrice);
            cell = rowTotalNormale.createCell(4);
            cell.setCellFormula("E$" + indexSortie + "*1.2");
            cell.setCellStyle(styleTotalPriceEffected);
            if (tvaReduceAllowed == true || tvaInterAllowed == true) cell.setCellStyle(styleTotalPrice);

            /**
             * Outputting to Excel spreadsheet
             */
            FileOutputStream fos = new FileOutputStream(new File(directory + "PRICE_FROM_" + simulationCode + ".xls"));
            wb.write(fos);
            fos.flush();
            fos.close();

            File file = new File(directory + "PRICE_FROM_" + simulationCode + ".xls");
            if (file.exists()) {
                System.out.println("Le fichier Excel a bien été créé.");
            } else {
                System.out.println("Le fichier Excel n'a pas été créé...");
            }

        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("SAXException " + e.getMessage());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return priceLineList;
    }
}
