package com.ezdi.pbac.pref.runners;

import com.ezdi.pbac.beans.SecurityContext;
import com.ezdi.pbac.perf.generators.Generator;

import java.util.HashMap;

/**
 * Created by EZDI\ganesh.s on 20/9/16.
 */
public class RandomRequest {
    private Generator[] generators;
    private String[] paramList;
    private String entity;

    public RandomRequest(String entity, String[] paramList, Generator[] generators) {
        this.entity = entity;
        this.paramList = paramList;
        this.generators = generators;
    }

    public SecurityContext getRandomContext() {
        SecurityContext context = new SecurityContext();
        context.setUserId(generators[0].generate().toString());
        context.setEntityType(entity);
        HashMap<String, String> map = new HashMap<>();
        int i=1;
        for( String name : paramList) {
            map.put(name, generators[i++].generate().toString());
        }
        context.setContextParams(map);

        return context;
    }
}
