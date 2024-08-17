package com.hamami.borrowservice;

import org.springframework.boot.SpringApplication;

public class TestBorrowServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(BorrowServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
