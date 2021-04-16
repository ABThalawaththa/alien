package org.telusko.alien;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;





@Path("Products")
public class ProductService {
	
	Product product = new Product();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProductswe() {
		List<Product> productList = new ArrayList<>();
		Product product1 = new Product();
		product1.setProductId(1);
		Product product2 = new Product();
		product2.setProductId(2);
		product2.setProductDescription("new product");
		productList.add(product1);
		productList.add(product2);
		return productList;
	}
	
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_HTML)
//	public String returnWE() {
//		return "Fuck you";
//	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertProduct(@FormParam("productTitle") String productTitle, @FormParam("productDescription") String productDescription,
			@FormParam("productType") String productType, @FormParam("productCategory") String productCategory) {
		String output = product.insertProduct(productTitle, productDescription, productType, productCategory);
		return output;
	}


}
