package com.sample.spring.mapper;

import java.util.List;
import java.util.Map;

import com.sample.spring.domain.shop.Cart;
import com.sample.spring.domain.shop.Product;

public interface ProductMapper {

	public void deleteProduct(int product_id);
	public String chkPicture_url(int product_id);
	public void updateProduct(Product dto);
	public void insertProduct(Product dto);
	public void deleteAllCart(String userid);
	public void updateCart(Map<String, Object> map);
	public void deleteCart(int cart_id);
	public int sum(String userid);
	public List<Cart> listCart(String userid);
	public void insert(Cart dto);
	public Product view(int product_id);
	public List<Product> list();
}
