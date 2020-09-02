package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.service.classifier.ml.IMachineLearning;
import br.ufsc.ine.ppgcc.service.classifier.ml.OneRClassifier;
import br.ufsc.ine.ppgcc.service.instances.AInstances;
import br.ufsc.ine.ppgcc.service.instances.BinaryChangeOrNotInstances;
import br.ufsc.ine.ppgcc.service.instances.BinaryUpDownInstances;
import br.ufsc.ine.ppgcc.service.instances.TernaryUpDownStayInstances;
import org.springframework.stereotype.Service;
import weka.core.Instances;

import java.util.HashMap;
import java.util.Map;

@Service
public class MLService implements IPredict {

    private IMachineLearning machineLearning;

    public MLService(){
        this.machineLearning = new OneRClassifier();
    }

    @Override
    public Double accuracyBinaryInstanceUpDown(double[] inputData, double[] externalMeasure) {
        AInstances binaryInstance = new BinaryUpDownInstances();
        Instances instance = binaryInstance.buildInstances(inputData, externalMeasure);

        return machineLearning.accuracy(instance);
    }

    @Override
    public Map<String, Integer> matrixConfusionBinaryInstanceUpDown(double[] inputData, double[] externalMeasure) {
        AInstances binaryInstance = new BinaryUpDownInstances();
        Instances instance = binaryInstance.buildInstances(inputData, externalMeasure);

        return buildMatrixConfusion(instance);
    }

    @Override
    public Double accuracyBinaryInstanceChangeOrNot(double[] inputData, double[] externalMeasure) {
        AInstances binaryInstance = new BinaryChangeOrNotInstances();
        Instances instance = binaryInstance.buildInstances(inputData, externalMeasure);

        return machineLearning.accuracy(instance);
    }

    @Override
    public Map<String, Integer> matrixConfusionBinaryInstanceChangeOrNot(double[] inputData, double[] externalMeasure) {
        AInstances binaryInstance = new BinaryChangeOrNotInstances();
        Instances instance = binaryInstance.buildInstances(inputData, externalMeasure);

        return buildMatrixConfusion(instance);
    }

    @Override
    public Double accuracyTernaryInstanceUpDownStay(double[] inputData, double[] externalMeasure) {
        AInstances ternaryInstance = new TernaryUpDownStayInstances();
        Instances instance = ternaryInstance.buildInstances(inputData, externalMeasure);

        return machineLearning.accuracy(instance);
    }

    @Override
    public Map<String, Integer> matrixConfusionTernaryInstanceUpDownStay(double[] inputData, double[] externalMeasure) {
        AInstances ternaryInstance = new TernaryUpDownStayInstances();
        Instances instance = ternaryInstance.buildInstances(inputData, externalMeasure);

        return buildMatrixConfusion(instance);
    }

    private Map<String, Integer> buildMatrixConfusion(Instances instance){
        Map<String, Integer> matrix = new HashMap<>();

        int tpValue = machineLearning.truePositive(instance);
        int tnValue = machineLearning.trueNegative(instance);
        int fpValue = machineLearning.falsePositive(instance);
        int fnValue = machineLearning.falseNegative(instance);

        matrix.put("TP", tpValue);
        matrix.put("TN", tnValue);
        matrix.put("FP", fpValue);
        matrix.put("FN", fnValue);

        return matrix;
    }
}
