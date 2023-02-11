package com.utm.msei.executor;

import org.springframework.boot.CommandLineRunner;

public interface ExecuteI extends CommandLineRunner {
    void run(String... args) throws Exception;
}
