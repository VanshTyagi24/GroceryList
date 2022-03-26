package ca.sheridancollege.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Cart;
import ca.sheridancollege.beans.Grocery;
import ca.sheridancollege.beans.User;

@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	public User findUserAccount(String userName) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where userName=:userName";
		parameters.addValue("userName", userName);
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));//finding users that match
		if (users.size()>0)
			return users.get(0);
		else
			return null;
	}

	
	public List<String> getRolesById(long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId "
				+ "and userId=:userId";
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public void addUser(String userName, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into SEC_User " 
                + "(userName, encryptedPassword, ENABLED)" 
                + " values (:userName, :encryptedPassword, 1)";
		parameters.addValue("userName",userName).addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, parameters);
	}
	
	public void addRole(long userId, long roleId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "insert into user_role (userId, roleId)" 
                + "values (:userId, :roleId);";
        parameters.addValue("userId", userId);
        parameters.addValue("roleId", roleId);
        jdbc.update(query, parameters);    
    }
	
	public void addGroc(String grocName, String price, String category) {
		//String query = "INSERT into easy_drinks VALUES('Fred Drink',"+
	   // "'coke zero',3,'sugar water',5,'serve cold')";
		//Hashmap
		double pricee=Double.parseDouble(price);
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		//we are storing items in Map - > Key, Value Pairs
		String query ="INSERT INTO groceries (grocName,price,category) VALUES "+
		"(:name,:price,:cat)";
		parameters.addValue("name", grocName);
		parameters.addValue("price", pricee);
		parameters.addValue("cat",category);
		
		
		jdbc.update(query, parameters);
	
}
	
	public ArrayList<Grocery> getDrinks(){
		String q = "Select * from groceries where category='drinks'";
		ArrayList<Grocery> drinks =  new ArrayList<Grocery>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Grocery d = new Grocery();
			d.setGrocId((Integer)row.get("grocid"));
			d.setGrocName((String)(row.get("grocname")));
			//d.setPrice((Double)(row.get("price")));
			d.setCategory((String)(row.get("category")));
			d.setPrice(((BigDecimal)(row.get("price"))).doubleValue());
			drinks.add(d);
		}
            return drinks;


	}
	public ArrayList<Grocery> getFruits(){
		String q = "Select * from groceries where category='fruits'";
		ArrayList<Grocery> fruits =  new ArrayList<Grocery>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Grocery d = new Grocery();
			d.setGrocId((Integer)row.get("grocid"));
			d.setGrocName((String)(row.get("grocname")));
			//d.setPrice((Double)(row.get("price")));
			d.setCategory((String)(row.get("category")));
			d.setPrice(((BigDecimal)(row.get("price"))).doubleValue());
			fruits.add(d);
		}
            return fruits;


	}
	public ArrayList<Grocery> getSnacks(){
		String q = "Select * from groceries where category='snacks'";
		ArrayList<Grocery> fruits =  new ArrayList<Grocery>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Grocery d = new Grocery();
			d.setGrocId((Integer)row.get("grocid"));
			d.setGrocName((String)(row.get("grocname")));
			//d.setPrice((Double)(row.get("price")));
			d.setCategory((String)(row.get("category")));
			d.setPrice(((BigDecimal)(row.get("price"))).doubleValue());
			fruits.add(d);
		}
            return fruits;


	}
	public ArrayList<Grocery> getMeat(){
		String q = "Select * from groceries where category='meat'";
		ArrayList<Grocery> meats =  new ArrayList<Grocery>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Grocery d = new Grocery();
			d.setGrocId((Integer)row.get("grocid"));
			d.setGrocName((String)(row.get("grocname")));
			//d.setPrice((Double)(row.get("price")));
			d.setCategory((String)(row.get("category")));
			d.setPrice(((BigDecimal)(row.get("price"))).doubleValue());
			meats.add(d);
		}
            return meats;


	}
	public Grocery getGrocerybyId(int id){
		String q = "Select * from groceries where grocId =:id";
		ArrayList<Grocery> grocery =  new ArrayList<Grocery>();
		
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		parameters.addValue("id", id);
		List<Map<String,Object>> rows=jdbc.queryForList(q, parameters);
		
		for(Map<String,Object>row:rows) {
			Grocery d = new Grocery();
			Cart c = new Cart();
			d.setGrocId((Integer)row.get("grocid"));
			d.setGrocName((String)(row.get("grocname")));
			//d.setPrice((Double)(row.get("price")));
			d.setCategory((String)(row.get("category")));
			d.setPrice(((BigDecimal)(row.get("price"))).doubleValue());
			grocery.add(d);
//			c.setItem((String)(row.get("grocname")));
//			c.setAmount(((BigDecimal)(row.get("price"))).doubleValue());
//			TheDatabase.cartList.add(c);
		}
		
		if(grocery.size()>0)
			{System.out.println(grocery.get(0).toString());
			return grocery.get(0);}
		
		return null;

		 
		
		
	}
	public Cart loadCart(int id){
		String q = "Select * from groceries where grocId =:id";
		//ArrayList<Grocery> grocery =  new ArrayList<Grocery>();
		ArrayList<Cart> carts =  new ArrayList<Cart>();
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		parameters.addValue("id", id);
		List<Map<String,Object>> rows=jdbc.queryForList(q, parameters);
		Cart c = new Cart();
		for(Map<String,Object>row:rows) {
			//Grocery d = new Grocery();
			
			//d.setGrocId((Integer)row.get("grocid"));
			//d.setGrocName((String)(row.get("grocname")));
			//d.setPrice((Double)(row.get("price")));
			//d.setCategory((String)(row.get("category")));
			//d.setPrice(((BigDecimal)(row.get("price"))).doubleValue());
			//grocery.add(d);
		    c.setItem((String)(row.get("grocname")));
			c.setAmount(((BigDecimal)(row.get("price"))).doubleValue());
			//carts.add(c);
		}
		System.out.println(c.toString());
		return c;

	}
	public void editDrink(String grocId, String grocName, double price, String category) {
		
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
//		int ID = Integer.parseInt(grocId);
//		Double pricee = Double.parseDouble(price);
		String query ="Update groceries Set grocName=:name,price=:price,category=:cat WHERE grocId=:id ";
		parameters.addValue("id", grocId);
		parameters.addValue("name", grocName);
		parameters.addValue("price", price);
		parameters.addValue("cat",category);
		
		
		jdbc.update(query, parameters);
	
}
	public void deleteGrocery(String grocId, String grocName, double price, String category) {
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		
		String query ="Delete from groceries WHERE grocId =:id";
		parameters.addValue("id", grocId);
		jdbc.update(query, parameters);
		
		
	}
	
	
}


