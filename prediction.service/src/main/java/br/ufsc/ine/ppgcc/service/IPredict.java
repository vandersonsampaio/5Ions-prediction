package br.ufsc.ine.ppgcc.service;

import java.util.Map;

public interface IPredict {

    Double accuracyBinaryInstanceUpDown(double[] inputData, double[] externalMeasure);
    Map<String, Integer> matrixConfusionBinaryInstanceUpDown(double[] inputData, double[] externalMeasure);

    Double accuracyBinaryInstanceChangeOrNot(double[] inputData, double[] externalMeasure);
    Map<String, Integer> matrixConfusionBinaryInstanceChangeOrNot(double[] inputData, double[] externalMeasure);

    Double accuracyTernaryInstanceUpDownStay(double[] inputData, double[] externalMeasure);
    Map<String, Integer> matrixConfusionTernaryInstanceUpDownStay(double[] inputData, double[] externalMeasure);
}
