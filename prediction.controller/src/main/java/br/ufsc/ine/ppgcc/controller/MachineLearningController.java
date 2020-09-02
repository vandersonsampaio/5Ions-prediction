package br.ufsc.ine.ppgcc.controller;

import br.ufsc.ine.ppgcc.service.MLService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("ml")
public class MachineLearningController {

    private final MLService service;

    public MachineLearningController(MLService service){
        this.service = service;
    }

    @PostMapping(path = "ternaryclassifier/accuracy", produces = APPLICATION_JSON_VALUE)
    public double getAccuracy(double[] inputData, double[] externalMeasure){
        return service.accuracyTernaryInstanceUpDownStay(inputData, externalMeasure);
    }
}