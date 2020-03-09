package com.leroymerlin.pricesofferextractor.lmfr;

import java.io.File;

public class DeleteFile{
    public DeleteFile(){

    }
    public void DeleteFilesByPath(String directory){
        File dirXML = new File(directory + "/xml/");
        File dirXLS = new File(directory + "/xls/");

        File[] listFilesXML = dirXML.listFiles();
        File[] listFilesXLS = dirXLS.listFiles();

        for(File file : listFilesXML){
            System.out.println("Deleting "+file.getName());
            file.delete();
        }

        for(File file : listFilesXLS){
            System.out.println("Deleting "+file.getName());
            file.delete();
        }
    }
    public void DeleteFilesByPathExceptSim(String directory, String simulationCode){
        File dirXML = new File(directory + "/xml/");
        File dirXLS = new File(directory + "/xls/");

        File[] listFilesXML = dirXML.listFiles();
        File[] listFilesXLS = dirXLS.listFiles();

        for(File file : listFilesXML){
            System.out.println(simulationCode + ".xml");
            System.out.println(file.getName());
            System.out.println(simulationCode + ".xml" == file.getName());

            if (file.getName().equals(simulationCode + ".xml")) {

            }else{
                System.out.println("Deleting " + file.getName());
                file.delete();
            }
        }
        for(File file : listFilesXLS){
            System.out.println("PRICE_FROM_" + simulationCode + ".xls");
            System.out.println(file.getName().toString());
            System.out.println("PRICE_FROM_" + simulationCode + ".xls" == file.getName());
            if (file.getName().equals("PRICE_FROM_" + simulationCode + ".xls")){

            }else{
                System.out.println("Deleting "+file.getName());
                file.delete();
            }
        }
    }
    public void DeleteFileByFile(String directory){
        File dir = new File(directory);

        File[] listFiles = dir.listFiles();
        for(File file : listFiles){
            System.out.println("Deleting "+file.getName());
            file.delete();
        }
    }
}
