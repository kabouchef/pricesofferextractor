package com.leroymerlin.pricesofferextractor.xmlToExcel;

import lombok.Data;

import java.util.List;

import static com.leroymerlin.pricesofferextractor.xmlToExcel.MathUtils.*;

@Data
public class TotalPrices {
    double totalPriceHT = 0;
    double totalPriceTVAReduite = 0;
    double totalPriceTVAInter = 0;
    double totalPriceTVANormale = 0;
    double tvaReduceForbidden = 0;
    double tvaInterForbidden = 0;

    public TotalPrices() {
    }

    public void getTotalPrice(List<PriceLine> tarifPrestation){
        totalPriceHT = tarifPrestation.stream()
                .map(x -> Double.parseDouble(x.getTarif_prestation()))
                .reduce(0.0d, (x,y) -> x+y);

        tvaReduceForbidden = tarifPrestation.stream()
                .map(x -> Double.parseDouble(x.getTva_reduite()))
                .filter(x -> x < 1)
                .count();
        tvaInterForbidden = tarifPrestation.stream()
                .map(x -> Double.parseDouble(x.getTva_inter()))
                .filter(x -> x < 1)
                .count();

        if(tvaReduceForbidden == 0) totalPriceTVAReduite =  arrondiMathematique(multiplyDouble(totalPriceHT, 1.055));
        if(tvaInterForbidden == 0) totalPriceTVAInter = arrondiMathematique(multiplyDouble(totalPriceHT, 1.1));
        totalPriceTVANormale = arrondiMathematique(multiplyDouble(totalPriceHT, 1.2));
    }
}
