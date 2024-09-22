/**
 * 
 */
package com.kaziabid.os.fileserverapi.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 */
@SpringBootApplication(scanBasePackages = "com.kaziabid.os.fileserverapi")
public class FileServerApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(FileServerApp.class, args);
    }

}
