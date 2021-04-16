package org.telusko.alien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Product {
	
	private int productId;
	private String productTitle;
	private String productDescription;
	private String productType;
	private String productCategory;
	
	
	public Product(int productId, String productTitle, String productDescription, String productType,
			String productCategory) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productType = productType;
		this.productCategory = productCategory;
	}

	public Product() {};

	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductTitle() {
		return productTitle;
	}


	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}


	public Connection connect2() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productservicedb", "root", "Asiyaamysql1");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String insertProduct(String productTitle,String productDescription,
			String productType,String productCategory) {
		String output = "";
		try {
			Connection con = connect2();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into products " + "(`productTitle`,`productDescription`,`productType`,`productCategory`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, productTitle);
			preparedStmt.setString(2, productDescription);
			preparedStmt.setString(3, productType);
			preparedStmt.setString(4, productCategory);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	
	public List<Product> getAllProducts(){
		List<Product> productList = new ArrayList<Product>();
		try {
			Connection con = connect2();
			// create a prepared statement
			String query = "select * from products";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductTitle(rs.getString("productTitle"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setProductType(rs.getString("productType"));
				product.setProductCategory(rs.getString("productCategory"));
				productList.add(product);
			}
			con.close();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return productList;
		
		
	}

//	@Override
//	public String toString() {
//		return "Product [productId=" + productId + ", productTitle=" + productTitle + ", productDescription="
//				+ productDescription + ", productType=" + productType + ", productCategory=" + productCategory + "]";
//	}
	

}
