package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Coppia;
import it.polito.tdp.yelp.model.Distanza;
import it.polito.tdp.yelp.model.Review;
import it.polito.tdp.yelp.model.User;

public class YelpDao {
	
	
	public void getAllBusiness( Map<String, Business> idMap){
		String sql = "SELECT * FROM Business";
		//List<Business> result = new ArrayList<Business>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				if(!idMap.containsKey(res.getString("business_id"))) {
				Business business = new Business(res.getString("business_id"), 
						res.getString("full_address"),
						res.getString("active"),
						res.getString("categories"),
						res.getString("city"),
						res.getInt("review_count"),
						res.getString("business_name"),
						res.getString("neighborhoods"),
						res.getDouble("latitude"),
						res.getDouble("longitude"),
						res.getString("state"),
						res.getDouble("stars"));

				idMap.put(business.getBusinessId(), business);
				}
			}
			res.close();
			st.close();
			conn.close();
			//return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ;
		}
	}
	
	public List<Review> getAllReviews(){
		String sql = "SELECT * FROM Reviews";
		List<Review> result = new ArrayList<Review>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Review review = new Review(res.getString("review_id"), 
						res.getString("business_id"),
						res.getString("user_id"),
						res.getDouble("stars"),
						res.getDate("review_date").toLocalDate(),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("review_text"));
				result.add(review);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getAllUsers(){
		String sql = "SELECT * FROM Users";
		List<User> result = new ArrayList<User>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				User user = new User(res.getString("user_id"),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("name"),
						res.getDouble("average_stars"),
						res.getInt("review_count"));
				
				result.add(user);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getCitta(){
		String sql="SELECT DISTINCT b.city as c "
				+ "FROM business b "
				+"ORDER BY c ";
		List<String> result = new ArrayList<String>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				result.add(res.getString("c"));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<Distanza> getDistanza(String citta, Map<String, Business> idMap){
		String sql = "SELECT b.business_id AS b, AVG(b.latitude) AS lat, AVG(b.longitude) AS lng "
				+ "FROM business b "
				+ "WHERE b.city = ? "
				+ "GROUP BY b.business_id ";
		List<Distanza> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, citta);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
					result.add(new Distanza(idMap.get(res.getString("b")), new LatLng(res.getDouble("lat"),
							 (res.getDouble("lng")))));
				
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<Business> getVertici(String c, Map<String,Business> idMap){
		String sql = "SELECT business_id  "
				+ "FROM business "
				+ "WHERE city = ? ";
		List<Business> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				result.add(idMap.get(res.getString("business_id")));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	public List<Business> getVertici(String c, Map<String,Business> idMap){
		String sql = "SELECT business_id AS b "
				+ "FROM business b "
				+ "WHERE city = ? ";
		List<Business> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				result.add(idMap.get(res.getString("business_id")));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Coppia> getArchi(String c, Map<String, Business> idMap){
		String sql="SELECT b1.business_id AS b1, b2.business_id AS b2, b1.latitude AS lat1, b1.longitude AS lng1, b2.latitude AS lat2, b2.longitude AS lng2 "
				+ "FROM business b1, business b2 "
				+ "WHERE b1.city=b2.city AND b1.city= ?  AND b1.business_id > b2.business_id ";
		Connection conn = DBConnect.getConnection();
		List<Coppia> result = new ArrayList<>();

		try {
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				result.add(new Coppia(idMap.get(res.getObject("b1")), idMap.get(res.getObject("b2"))));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	*/
}
