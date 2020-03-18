package com.leroymerlin.pricesofferextractor.lmfr;


import com.leroymerlin.pricesofferextractor.xmlToExcel.PriceLine;
import com.leroymerlin.pricesofferextractor.xmlToExcel.XMLToExcel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.List;

public class SimulationOffer {
    public static String simulationCode = "";

    public SimulationOffer() {
    }

    public String getSimulationCode() {
        return simulationCode;
    }

    public List<PriceLine> getSimulationOffer(String simulationCode, String environnement, String schema) {
        
        String staticDir = "src/main/resources/static/";
        DeleteFile deleteFile = new DeleteFile();
        deleteFile.DeleteFilesByPath(staticDir);

        List<PriceLine> tabPriceElement = null;
        try {
            Statement state = ConnectionLMFR.getInstance(environnement, schema).createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet res = state.executeQuery("SELECT XML_CONF FROM T_CONF_STORAGE WHERE CONF_ID = '" + simulationCode + "'");
            ResultSetMetaData meta = res.getMetaData();

            Object[] column = new Object[meta.getColumnCount()];

            for (int i = 1; i <= meta.getColumnCount(); i++)
                column[i - 1] = meta.getColumnName(i);

            res.last();
            /*int rowCount = res.getRow();*/
            Object[][] data = new Object[res.getRow()][meta.getColumnCount()];

            //On revient au départ
            res.beforeFirst();
            int j = 1;

            //On remplit le tableau d'Object[][]
            while (res.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    data[j - 1][i - 1] = res.getObject(i);

                    Clob clob = res.getClob("XML_CONF");
                    Reader r = clob.getCharacterStream();
                    StringBuffer buffer = new StringBuffer();
                    int ch;
                    while ((ch = r.read()) != -1) {
                        buffer.append("" + (char) ch);
                    }
                    XmlWriter writingXML = new XmlWriter(buffer.toString());
                }
                j++;
            }
            res.close();
            state.close();

            XMLToExcel excel = new XMLToExcel();
            tabPriceElement = excel.generateExcel(simulationCode);

            /*PriceLineListExtractor priceLineListExtractor = new PriceLineListExtractor();
            priceLineListExtractor.generatePriceLineList(simulationCode);*/

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return tabPriceElement;
    }
}
