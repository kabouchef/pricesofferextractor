package com.leroymerlin.pricesofferextractor.exports.view;

import com.leroymerlin.pricesofferextractor.exports.model.PriceLineList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"Price from simulation.xls\"");

        @SuppressWarnings("unchecked")
        List<PriceLineList> priceLineList = (List<PriceLineList>) model.get("priceLineList");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Price from simulation");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Leroy Merlin Sans");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("IDENTIFIANT");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("DETAIL_PRESTATION");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("QUANTITE");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("TARIF_UNITAIRE");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("TARIF_PRESTATION");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("TYPE_PRESTATION");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("PRESTATION_DE");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("TVA_REDUITE");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("TVA_INTER");
        header.getCell(8).setCellStyle(style);
        header.createCell(9).setCellValue("TVA_NORMALE");
        header.getCell(9).setCellStyle(style);
        header.createCell(10).setCellValue("CODE_49");
        header.getCell(10).setCellStyle(style);
        header.createCell(11).setCellValue("COD_TYPE_PRESTATION");
        header.getCell(11).setCellStyle(style);
        header.createCell(12).setCellValue("TEMP_POSE");
        header.getCell(12).setCellStyle(style);
        header.createCell(13).setCellValue("ORDRE");
        header.getCell(13).setCellStyle(style);

        int rowCount = 1;

        for(PriceLineList priceLine : priceLineList){
            Row priceLineRow =  sheet.createRow(rowCount++);
            priceLineRow.createCell(0).setCellValue(priceLine.getIdentifiant());
            priceLineRow.createCell(1).setCellValue(priceLine.getDetail_prestation());
            priceLineRow.createCell(2).setCellValue(priceLine.getQuantite());
            priceLineRow.createCell(3).setCellValue(priceLine.getTarif_unitaire());
            priceLineRow.createCell(4).setCellValue(priceLine.getTarif_prestation());
            priceLineRow.createCell(5).setCellValue(priceLine.getType_prestation());
            priceLineRow.createCell(6).setCellValue(priceLine.getPrestation_de());
            priceLineRow.createCell(7).setCellValue(priceLine.getTva_reduite());
            priceLineRow.createCell(8).setCellValue(priceLine.getTva_inter());
            priceLineRow.createCell(9).setCellValue(priceLine.getTva_normale());
            priceLineRow.createCell(10).setCellValue(priceLine.getCode_49());
            priceLineRow.createCell(11).setCellValue(priceLine.getCod_type_prestation());
            priceLineRow.createCell(12).setCellValue(priceLine.getTemp_pose());
            priceLineRow.createCell(12).setCellValue(priceLine.getOrdre());
        }

    }

}
