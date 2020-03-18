package com.leroymerlin.pricesofferextractor.lmfr;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class DeleteFile{
    public DeleteFile(){

    }
    public void DeleteFilesByPath(String directory){
        File dirXML = new File(directory + "/xml/");
        File dirXLS = new File(directory + "/xls/");

        File[] listFilesXML = dirXML.listFiles();
        File[] listFilesXLS = dirXLS.listFiles();

        log.info("*******************************");
        for(File file : listFilesXML){
            log.info("Deleting "+file.getName());
            file.delete();
        }

        for(File file : listFilesXLS){
            log.info("Deleting "+file.getName());
            file.delete();
        }
        log.info("*******************************");
    }
    /*public void DeleteFilesByPathExceptSim(String directory, String simulationCode){
        File dirXML = new File(directory + "/xml/");
        File dirXLS = new File(directory + "/xls/");

        File[] listFilesXML = dirXML.listFiles();
        File[] listFilesXLS = dirXLS.listFiles();

        for(File file : listFilesXML){
            log.info(simulationCode + ".xml");
            log.info(file.getName());
            log.info(simulationCode + ".xml" == file.getName());

            if (file.getName().equals(simulationCode + ".xml")) {

            }else{
                log.info("Deleting " + file.getName());
                file.delete();
            }
        }
        for(File file : listFilesXLS){
            log.info("PRICE_FROM_" + simulationCode + ".xls");
            log.info(file.getName().toString());
            log.info("PRICE_FROM_" + simulationCode + ".xls" == file.getName());
            if (file.getName().equals("PRICE_FROM_" + simulationCode + ".xls")){

            }else{
                log.info("Deleting "+file.getName());
                file.delete();
            }
        }
    }*/
    public void DeleteFileByFile(String directory){
        File dir = new File(directory);

        File[] listFiles = dir.listFiles();
        for(File file : listFiles){
            log.info("Deleting "+file.getName());
            file.delete();
        }
    }
}
