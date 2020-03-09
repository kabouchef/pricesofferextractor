package com.leroymerlin.pricesofferextractor.xmlToExcel;

import lombok.Data;

@Data
public class PriceLine {
    private String identifiant;
    private String detail_prestation;
    private String quantite;
    private String tarif_unitaire;
    private String tarif_prestation;
    private String type_prestation;
    private String prestation_de;
    private String tva_reduite;
    private String tva_inter;
    private String tva_normale;
    private String code_49;
    private String cod_type_prestation;
    private String temp_pose;
    private String ordre;
    private double totalPriceHT;
    private double totalPriceTVAReduite;
    private double totalPriceTVAInter;
    private double totalPriceTVANormale;

    public String toString() {
        String s = "identifiant : " + identifiant + "\n";
        s +="detail_prestation : " + detail_prestation + "\n";
        s +="quantite : " + quantite + "\n";
        s +="tarif_unitaire : " + tarif_unitaire + "\n";
        s +="tarif_prestation : " + tarif_prestation + "\n";
        s +="type_prestation : " + type_prestation + "\n";
        s +="prestation_de : " + prestation_de + "\n";
        s +="tva_reduite : " + tva_reduite + "\n";
        s +="tva_inter : " + tva_inter + "\n";
        s +="tva_normale : " + tva_normale + "\n";
        s +="code_49 : " + code_49 + "\n";
        s +="cod_type_prestation : " + cod_type_prestation + "\n";
        s +="temp_pose : " + temp_pose + "\n";
        s +="ordre : " + ordre + "\n";
        s +="totalPriceHT : " + totalPriceHT + "\n";
        s +="totalPriceTVAReduite : " + totalPriceTVAReduite + "\n";
        s +="totalPriceTVAInter : " + totalPriceTVAInter + "\n";
        s +="totalPriceTVANormale : " + totalPriceTVANormale + "\n";

        return s;
    }


}
