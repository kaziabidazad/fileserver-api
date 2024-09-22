package com.kaziabid.os.fileserverapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kazi
 */
@RestController
public class HomeController {

    @GetMapping
    public Map<String, String> home() {
	Map<String, String> result = new HashMap<String, String>();
	result.put("Status", "Up.");
	return result;
    }

}
