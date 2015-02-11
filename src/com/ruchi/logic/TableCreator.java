package com.ruchi.logic;

import java.util.HashMap;
import java.util.Set;

public class TableCreator {

	public static String getFoodTable(HashMap<String, Double> restMap){
		StringBuilder sb = new StringBuilder();
		sb.append("<table id=\"rest_table\" class=\"display\" cellspacing=\"0\" width=\"100%\">").append("\n");
		sb.append("<thead><tr><th>Restaurant</th><th>Rating</th></tr></thead>").append("\n");
		sb.append("<tfoot><tr><th>Restaurant</th><th>Rating</th></tr></tfoot>").append("\n");
		sb.append("<tbody>").append("\n");
		Set<String> keySet = restMap.keySet();
		for (String key : keySet) {
			Double rating = restMap.get(key);
			sb.append("<tr><td>"+key+"</td><td>"+rating+"</td></tr>").append("\n");
		}
		sb.append("</tbody>\n</table>\n");
		return sb.toString();
		
	}
	
	public static String getRestTable(HashMap<String, Double> foodMap){
		StringBuilder sb = new StringBuilder();
		sb.append("<table id=\"rest_table\" class=\"display\" cellspacing=\"0\" width=\"100%\">").append("\n");
		sb.append("<thead><tr><th>Food</th><th>Rating</th></tr></thead>").append("\n");
		sb.append("<tfoot><tr><th>Food</th><th>Rating</th></tr></tfoot>").append("\n");
		sb.append("<tbody>").append("\n");
		Set<String> keySet = foodMap.keySet();
		for (String key : keySet) {
			Double rating = foodMap.get(key);
			sb.append("<tr><td>"+key+"</td><td>"+rating+"</td></tr>").append("\n");
		}
		sb.append("</tbody>\n</table>\n");
		return sb.toString();
		
	}
}
