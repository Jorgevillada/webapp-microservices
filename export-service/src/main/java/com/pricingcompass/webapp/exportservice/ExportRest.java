package com.pricingcompass.webapp.exportservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportRest{

    @Value("${test}")
    String value;

    @Autowired
    Environment environment;

    @GetMapping("/hola-mundo")
    @HystrixCommand(commandKey = "HolaMundoCommand")
    public String holaMundo(){
        return "hola mundo..."+value + " port: "+environment.getProperty("local.server.port");
    }

    @GetMapping("/fault")
    @HystrixCommand(commandKey = "FaultCommand", fallbackMethod = "faultFallback")
    public String fault(){
        throw new RuntimeException("");
    }

    public String faultFallback(){
        return "faultFallback";
    }



}
