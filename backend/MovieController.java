package com.sp.friend.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
	  	return IOUtils.toString(moviesJson.getInputStream(), Charset.forName("UTF-8"));
    }
	
	@RequestMapping("/add")
    //public void add(@RequestBody String json) throws IOException, JSONException {
	public void add(String json) throws IOException, JSONException {	
		json = "{\"releaseDate\":\"29/10/2014\",\"director\":\"David Leitch\",\"title\":\"John Wick\",\"type\":\"Thriller\"}";
		JSONObject object = new JSONObject(json);
		JSONArray array = getCurrentMovies();
		array.put(object);
		
		URL url = this.getClass().getClassLoader().getResource("movies.json");
		 File file = null;
		    try {
		        file = new File(url.toURI());
		    } catch (URISyntaxException e) {
		        file = new File(url.getPath());
		    } 
		    
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(array.toString());
		fileWriter.flush();
    }
	
	@RequestMapping("/update")
    //public void add(@RequestBody String json) throws IOException, JSONException {
	public void update(String json) throws IOException, JSONException {	
		
		JSONArray array = new JSONArray(json);
		
		URL url = this.getClass().getClassLoader().getResource("movies.json");
		 File file = null;
		    try {
		        file = new File(url.toURI());
		    } catch (URISyntaxException e) {
		        file = new File(url.getPath());
		    } 
		    
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(array.toString());
		fileWriter.flush();
    }
	
	private JSONArray getCurrentMovies() throws IOException, JSONException {
		String movieData =  IOUtils.toString(moviesJson.getInputStream(), Charset.forName("UTF-8"));
		return new JSONArray(movieData);
	} 
	

	private Movie mapMovieFromJsonObject(JSONObject object) throws JSONException {
		
		return new Movie(
				object.getString(Constant.TITLE),
				object.getString(Constant.DIRECTOR),
				object.getString(Constant.RELEASEDATE),
				object.getString(Constant.MOVIE_TYPE));
	}
}
