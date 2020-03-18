package com.leroymerlin.pricesofferextractor.lmfr;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class XmlWriter {

    private String simulationCode = "";


    public XmlWriter(String stringToXML) {
        String directory = "src/main/resources/static/xml/";

        String nameDefaultFile = directory + "XML_CONF.xml";
        File f = new File(nameDefaultFile);

        Path source = Paths.get(nameDefaultFile);

        try {
            FileWriter fw = new FileWriter(f);
            XmlFormatter formatter = new XmlFormatter();
            fw.write(formatter.format(stringToXML));

            fw.flush();
            fw.close();

            XmlReader xmlReader = new XmlReader();
            simulationCode = xmlReader.XmlReader();

            // Get the file
            String path = directory + simulationCode + ".xml";

            File ancien = new File(nameDefaultFile);
            File nouveau = new File(path);
            ancien.renameTo(nouveau);


            // Check if the specified file
            // Exists or not
            if (nouveau.exists()) log.info("Le fichier \"" + simulationCode + ".xml\" a bien été créé.");
            else log.info("Le fichier \"" + simulationCode + ".xml\" n'a été créé...");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
