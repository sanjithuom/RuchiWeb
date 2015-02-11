package com.ruchi.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is used to parse java object to json
 * 
 * @author SANJI
 *
 */
public class JacksonParsor {
	/**
	 * parse to JSON
	 * @param typeAhead POJO
	 * @return
	 */
	public static String toJson(TypeAhead typeAhead) {
		String toJson = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			toJson = mapper.writeValueAsString(typeAhead);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return toJson;
	}
	// main method
	public static void main(String[] args) {

		List<String> local = new ArrayList<String>();
		local.add("Chicago");
		local.add("Texas");
		TypeAhead typeAhead = new TypeAhead("cities", local);
		ObjectMapper mapper = new ObjectMapper();

		try {

			// convert user object to json string, and save to a file

			// display to console
			System.out.println(mapper.writeValueAsString(typeAhead));

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
