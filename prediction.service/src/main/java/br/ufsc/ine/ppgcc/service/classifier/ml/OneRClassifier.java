package br.ufsc.ine.ppgcc.service.classifier.ml;

import weka.classifiers.rules.OneR;

public class OneRClassifier extends IMachineLearning {

    public OneRClassifier(){
        super(new OneR());
    }
}
