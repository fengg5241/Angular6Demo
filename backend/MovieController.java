package com.ifeng.doudou.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")

public class MovieController {

	@Value("classpath:movies.json")
	Resource moviesJson;
	
	@RequestMapping("/getAll")
	@CrossOrigin(origins = "http://localhost:4200")
    public String getAll(String LOGIN_ID,String PASSWORD) throws IOException, JSONException {
		
		String movieData =  IOUtils.toString(moviesJson.getInputStream(), Charset.forName("UTF-8"));
	  	return movieData;
    }
}
