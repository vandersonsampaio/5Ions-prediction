package br.ufsc.ine.ppgcc.service.instances;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

public abstract class AInstances {

    public Instances buildInstances(double[] inputData, double[] externalIndicator){
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("t0"));
        attributes.add(new Attribute("t1"));
        attributes.add(new Attribute("class", buildClasses()));

        Instances instances = new Instances("relation", attributes, 0);
        instances.setClassIndex(instances.numAttributes() - 1);

        double[] instanceValues = new double[instances.numAttributes()];

        for(int i = 0; i < inputData.length - 1; i++){
            instanceValues[0] = inputData[i];
            instanceValues[1] = inputData[i + 1];
            instanceValues[2] = classifyInput(externalIndicator[i], externalIndicator[i + 1]);

            if(instanceValues[2] < 0){
                continue;
            }

            instances.add(new DenseInstance(1, instanceValues));
        }

        return instances;
    }

    public abstract List<String> buildClasses();
    public abstract int classifyInput(double t0, double t1);
}
