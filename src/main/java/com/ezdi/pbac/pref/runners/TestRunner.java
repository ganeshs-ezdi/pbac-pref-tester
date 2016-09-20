package com.ezdi.pbac.pref.runners;

import com.ezdi.pbac.beans.SecurityContext;
import com.ezdi.pbac.perf.generators.Generator;
import com.ezdi.pbac.perf.generators.IntegerGenerator;
import com.ezdi.pbac.perf.generators.ListGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by EZDI\ganesh.s on 20/9/16.
 */
@Component
public class TestRunner implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(TestRunner.class);

    @Value("${pbac.url}")
    private String url;

    @Value("${pbac.count}")
    private int count;

    private RestTemplate template;
    private RandomRequest requestGenerator;

    @Override
    public void run(String[] strings) throws Exception {
        logger.info("pbac.url={}", url);
        logger.info("pbac.count={}", count);

        template = restTemplate();
        requestGenerator = randomRequest();
        for( int i=0 ; i<count ; i++ ) {
            test(i);
        }

        System.exit(0);
    }

    public void test(int idx) {
        SecurityContext context = requestGenerator.getRandomContext();
        logger.info("Executing {}", idx);

        long startTime = System.nanoTime();
        ResponseEntity<String[]> permissions = template.postForEntity(
                url, (Object)new HttpEntity<>(context),
                String[].class);
        long endTime = System.nanoTime();

        logger.info("Finished {}", idx);
        logger.info("permissions={}", Arrays.toString(permissions.getBody()));
        logger.info("row={},{},{},{}", permissions.getBody().length, startTime, endTime, endTime-startTime);
    }

    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        template.setMessageConverters(converters());
        return template;
    }

    public RandomRequest randomRequest() {
        Generator[] generators = new Generator[5];
        generators[0] = new IntegerGenerator(1,1000);
        generators[1] = new ListGenerator<String>(new String[]{"InPatient","OutPatient","Emergency"});
        generators[2] = new ListGenerator<String>(new String[]{"InPatient","OutPatient","Emergency"});
        generators[3] = new ListGenerator<String>(new String[]{"SL1", "SL2", "SL3", "SL4", "SL5", "SL6", "SL7"});
        generators[4] = new ListGenerator<String>(new String[]{"POC1","POC2","POC3","POC4","POC5","POC6","POC7"});

        return new RandomRequest("document",
                new String[]{"patient_class", "patient_type", "service_list", "point_of_care"},
                generators);
    }

    public List<HttpMessageConverter<?>> converters() {
        ArrayList<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }
}
