package br.ufsc.ine.ppgcc.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient
public interface MachineLearningClient {

    @PostMapping(path = "ml/ternaryclassifier/accuracy", produces = APPLICATION_JSON_VALUE)
    double getAccuracy(double[] inputData, double[] externalMeasure);
}
