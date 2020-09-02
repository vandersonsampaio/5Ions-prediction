package br.ufsc.ine.ppgcc.service.instances;

import java.util.List;

import static java.util.Arrays.asList;

public class TernaryUpDownStayInstances extends AInstances {

    @Override
    public List<String> buildClasses() {
        return asList("Up", "Down", "Stay");
    }

    @Override
    public int classifyInput(double t0, double t1) {
        return t0 < t1 ? 0 : ( t0 > t1 ? 1 : 2 );
    }
}
