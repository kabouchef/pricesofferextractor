package com.leroymerlin.pricesofferextractor.controller;

import com.leroymerlin.pricesofferextractor.authentification.Authentification;
import com.leroymerlin.pricesofferextractor.authentification.UserAccess;
import com.leroymerlin.pricesofferextractor.lmfr.SimulationOffer;
import com.leroymerlin.pricesofferextractor.xmlToExcel.PriceLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    public static final String API_PATH = "/api/v1";

    @CrossOrigin
    @GetMapping(API_PATH + "/request")
    @ResponseBody
    public List<PriceLine> getSimulation(@RequestParam(name = "simulationCode") String simulationCode,
                                         @RequestParam(name = "environnement") String environnement,
                                         @RequestParam(name = "schema") String schema) {
        SimulationOffer simulationOffer = new SimulationOffer();
        List<PriceLine> priceListElement = simulationOffer.getSimulationOffer(simulationCode, environnement, schema);
        return priceListElement;
    }

    @CrossOrigin
    @GetMapping(API_PATH + "/authentification")
    @ResponseBody
    public UserAccess getAccessRight(@RequestParam(name = "idLDAP") String idLDAP){
        Authentification authentification = new Authentification();
        return authentification.getAccessRight(idLDAP);
    }


}
