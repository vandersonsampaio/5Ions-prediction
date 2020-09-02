package br.ufsc.ine.ppgcc.service.instances;

import java.util.List;

import static java.util.Arrays.asList;

public class BinaryChangeOrNotInstances extends AInstances {

    @Override
    public List<String> buildClasses() {
        return asList("Change", "NotChange");
    }

    @Override
    public int classifyInput(double t0, double t1) {
        return t0 != t1 ? 0 : 1;
    }
}
