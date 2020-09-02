package br.ufsc.ine.ppgcc.service.classifier.ml;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

public abstract class IMachineLearning {

    private Classifier classifier;

    public IMachineLearning(Classifier classifier){
        this.classifier = classifier;
    }

    public void trainingPredictor(Instances instances) {
        try {
            classifier.buildClassifier(instances);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Double accuracy(Instances instances) {

        try {
            Evaluation evaluation = generateEvaluation(instances);
            return 1 - evaluation.errorRate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Double fOneScore(Instances instances) {
        return 2 * precision(instances) * recall(instances) / (precision(instances) + recall(instances));
    }

    public Double precision(Instances instances) {
        return truePositive(instances) / (double) (truePositive(instances) + falsePositive(instances));
    }

    public Double recall(Instances instances) {
        return truePositive(instances) / (double) (truePositive(instances) + falseNegative(instances));
    }

    public Integer truePositive(Instances instances) {
        int truePositives = 0;

        try {
            int numClasses = instances.classAttribute().numValues();
            Evaluation evaluation = generateEvaluation(instances);

            for (int i = 0; i < numClasses; i++) {
                truePositives += evaluation.numTruePositives(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return truePositives;
    }

    public Integer trueNegative(Instances instances) {
        int trueNegatives = 0;

        try {
            int numClasses = instances.classAttribute().numValues();
            Evaluation evaluation = generateEvaluation(instances);

            for (int i = 0; i < numClasses; i++) {
                trueNegatives += evaluation.numTrueNegatives(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return trueNegatives;
    }

    public Integer falsePositive(Instances instances) {
        int falsePositives = 0;

        try {
            int numClasses = instances.classAttribute().numValues();
            Evaluation evaluation = generateEvaluation(instances);

            for (int i = 0; i < numClasses; i++) {
                falsePositives += evaluation.numFalsePositives(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return falsePositives;
    }

    public Integer falseNegative(Instances instances) {
        int falseNegatives = 0;

        try {
            int numClasses = instances.classAttribute().numValues();
            Evaluation evaluation = generateEvaluation(instances);

            for (int i = 0; i < numClasses; i++) {
                falseNegatives += evaluation.numFalseNegatives(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return falseNegatives;
    }

    private Evaluation generateEvaluation(Instances instances) throws Exception {
        Evaluation evaluation = new Evaluation(instances);
        evaluation.crossValidateModel(classifier, instances, 10, new Random(1));

        return evaluation;
    }
}
