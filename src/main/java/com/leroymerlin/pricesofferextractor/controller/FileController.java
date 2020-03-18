package com.leroymerlin.pricesofferextractor.controller;

import com.leroymerlin.pricesofferextractor.authentification.Authentification;
import com.leroymerlin.pricesofferextractor.authentification.UserAccess;
import com.leroymerlin.pricesofferextractor.lmfr.SimulationOffer;
import com.leroymerlin.pricesofferextractor.service.ExportFileService;
import com.leroymerlin.pricesofferextractor.xmlToExcel.PriceLine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Slf4j
@Api("API pour extraire les tarifs de simulation OAP.")
@RestController
@AllArgsConstructor
public class FileController {

    public static final String API_PATH = "/api/v1";
    private final ExportFileService exportFileService;

    @ApiOperation(value = "Récupère la liste des lignes de prix de la simulation en fonction du simulationCode et de l'environnement")
    @CrossOrigin
    @GetMapping(API_PATH + "/request")
    @ResponseBody
    public List<PriceLine> getSimulation(@RequestParam(name = "simulationCode") String simulationCode, @RequestParam(name = "environnement") String environnement, @RequestParam(name = "schema") String schema) {
        SimulationOffer simulationOffer = new SimulationOffer();
        List<PriceLine> priceListElement = simulationOffer.getSimulationOffer(simulationCode, environnement, schema);
        return priceListElement;
    }

    @ApiOperation(value = "Récupère les droit d'accès à un LDAP")
    @CrossOrigin
    @GetMapping(API_PATH + "/authentification")
    @ResponseBody
    public UserAccess getAccessRight(@RequestParam(name = "idLDAP") String idLDAP) {
        Authentification authentification = new Authentification();
        return authentification.getAccessRight(idLDAP);
    }

    @ApiOperation(value = "Récupère le fichier xls de la simulation présoumise par le requête '/request'")
    @GetMapping(API_PATH + "/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam(name = "simulationCode") String simulationCode) {
        File file = new File("src/main/resources/static/xls/PRICE_FROM_" + simulationCode + ".xls");

        // Load file as Resource
        Resource resource = this.exportFileService.loadFileAsResource(file.getAbsolutePath());

        String contentType = "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
