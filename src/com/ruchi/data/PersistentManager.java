package com.ruchi.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class PersistentManager {
	public static HashMap<String, Double> getFoodRating(String city, String food) {
		HashMap<String, Double> foodMap = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "SELECT r.rest_name, rf.rating"
					+ " FROM restaurants AS r INNER JOIN"
					+ " city AS c ON r.city_id = c.city_id and c.city = ? INNER JOIN"
					+ " rest_food AS rf ON r.rest_id = rf.rest_id INNER JOIN"
					+ " foods AS f ON f.food_id = rf.food_id and f.food_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, city);
			ps.setString(2, food);
			rs = ps.executeQuery();
			foodMap = new HashMap<String, Double>();
			while (rs.next()) {
				String rest = rs.getString(1);
				int rating = rs.getInt(2);
				foodMap.put(rest, (double) rating);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(ps);
			DBConnection.closeResource(rs);
			DBConnection.closeConnection(con);
		}
		return foodMap;
	}

	public static HashMap<String, Double> getRestaurantRating(String city,
			String restaurant) {
		HashMap<String, Double> restMap = new HashMap<String, Double>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "SELECT f.food_name, rf.rating"
					+ " FROM restaurants AS r INNER JOIN"
					+ " city AS c ON r.city_id = c.city_id and c.city = ? and r.rest_name = ? INNER JOIN"
					+ " rest_food AS rf ON r.rest_id = rf.rest_id INNER JOIN"
					+ " foods AS f ON f.food_id = rf.food_id";
			ps = con.prepareStatement(sql);
			ps.setString(1, city);
			ps.setString(2, restaurant);
			rs = ps.executeQuery();
			restMap = new HashMap<String, Double>();
			while (rs.next()) {
				String rest = rs.getString(1);
				int rating = rs.getInt(2);
				restMap.put(rest, (double) rating);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(ps);
			DBConnection.closeResource(rs);
			DBConnection.closeConnection(con);
		}
		return restMap;
	}

	public static ArrayList<String> getCityList() {
		ArrayList<String> cityList = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "Select city from city";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			cityList = new ArrayList<String>();
			while (rs.next()) {
				String city = rs.getString(1);
				cityList.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(ps);
			DBConnection.closeResource(rs);
			DBConnection.closeConnection(con);
		}
		return cityList;
	}

	public static void main(String[] args) {
		HashMap<String, Double> foodRating = PersistentManager.getRestaurantRating(
				"Fountain Hills", "Vu Bistro");
		Set<String> keySet = foodRating.keySet();
		for (String rest : keySet) {
			System.out.println(rest + " " + foodRating.get(rest));
		}

	}
}
