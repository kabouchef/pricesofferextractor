package com.leroymerlin.pricesofferextractor.exports.model;

import org.springframework.stereotype.Component;

@Component
public class PriceLineList {

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

    public PriceLineList(){

    }


    public PriceLineList(String identifiant,
                String detail_prestation,
                String quantite,
                String tarif_unitaire,
                String tarif_prestation,
                String type_prestation,
                String prestation_de,
                String tva_reduite,
                String tva_inter,
                String tva_normale,
                String code_49,
                String cod_type_prestation,
                String temp_pose,
                String ordre)
    {
        this.identifiant = identifiant;
        this.detail_prestation = detail_prestation;
        this.quantite = quantite;
        this.tarif_unitaire = tarif_unitaire;
        this.tarif_prestation = tarif_prestation;
        this.type_prestation = type_prestation;
        this.prestation_de = prestation_de;
        this.tva_reduite = tva_reduite;
        this.tva_inter = tva_inter;
        this.tva_normale = tva_normale;
        this.code_49 = code_49;
        this.cod_type_prestation = cod_type_prestation;
        this.temp_pose = temp_pose;
        this.ordre = ordre;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant){
        this.identifiant=identifiant;
    }
    public String getDetail_prestation() {
        return detail_prestation;
    }

    public void setDetail_prestation(String detail_prestation){
        this.detail_prestation=detail_prestation;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite){
        this.quantite=quantite;
    }

    public String getTarif_unitaire() {
        return tarif_unitaire;
    }

    public void setTarif_unitaire(String tarif_unitaire){
        this.tarif_unitaire=tarif_unitaire;
    }

    public String getTarif_prestation() {
        return tarif_prestation;
    }

    public void setTarif_prestation(String tarif_prestation){
        this.tarif_prestation=tarif_prestation;
    }

    public String getType_prestation() {
        return type_prestation;
    }

    public void setType_prestation(String type_prestation){
        this.type_prestation=type_prestation;
    }

    public String getPrestation_de() {
        return prestation_de;
    }

    public void setPrestation_de(String prestation_de){
        this.prestation_de=prestation_de;
    }

    public String getTva_reduite() {
        return tva_reduite;
    }

    public void setTva_reduite(String tva_reduite){
        this.tva_reduite=tva_reduite;
    }

    public String getTva_inter() {
        return tva_inter;
    }

    public void setTva_inter(String tva_inter){
        this.tva_inter=tva_inter;
    }

    public String getTva_normale() {
        return tva_normale;
    }

    public void setTva_normale(String tva_normale){
        this.tva_normale=tva_normale;
    }

    public String getCode_49() {
        return code_49;
    }

    public void setCode_49(String code_49){
        this.code_49=code_49;
    }

    public String getCod_type_prestation() {
        return cod_type_prestation;
    }

    public void setCod_type_prestation(String cod_type_prestation){
        this.cod_type_prestation=cod_type_prestation;
    }

    public String getTemp_pose() {
        return temp_pose;
    }

    public void setTemp_pose(String temp_pose){
        this.temp_pose=temp_pose;
    }

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre){
        this.ordre=ordre;
    }


    public int getColumnCount() {

        return getClass().getDeclaredFields().length;
    }
}
