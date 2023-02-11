package com.utm.msei;

import com.utm.msei.executor.ExecuteI;
import com.utm.msei.executor.JavaFxInitializer;
import com.utm.msei.handler.ServiceHandler;
import com.utm.msei.persistence.service.ZiSaptamanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main implements ExecuteI {

    @Autowired
    public ZiSaptamanaService ziSaptamanaService;

    // TODO ALL SERVICES

    public static ServiceHandler serviceHandler = new ServiceHandler();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String[] args) throws Exception {
        serviceHandler.save(ziSaptamanaService);
        JavaFxInitializer.launch();
    }
}
