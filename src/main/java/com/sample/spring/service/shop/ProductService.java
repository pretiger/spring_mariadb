package com.sample.spring.service.shop;

import java.util.List;

import com.sample.spring.domain.shop.Cart;
import com.sample.spring.domain.shop.Product;

public interface ProductService {

	public void deleteProduct(int product_id);
	public void updateProduct(Product dto);
	public void insertProduct(Product dto);
	public void deleteAllCart(String userid);
	public void updateCart(int[] cart_id, int[] amount);
	public void deleteCart(int cart_id);
	public int sum(String userid);
	public List<Cart> listCart(String userid);
	public void insert(Cart dto);
	public Product view(int product_id);
	public List<Product> list();
}
