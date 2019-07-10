package com.tinlm.snef;

import com.tinlm.snef.connection.MyConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class SnefApplication {


    public static void main(String[] args) {
        SpringApplication.run(SnefApplication.class, args);

    }



}
