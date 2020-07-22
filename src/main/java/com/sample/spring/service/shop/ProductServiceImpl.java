package com.sample.spring.service.shop;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.spring.domain.shop.Cart;
import com.sample.spring.domain.shop.Product;
import com.sample.spring.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;
	
	@Override
	public List<Product> list() {
		return productMapper.list();
	}

	@Override
	public Product view(int product_id) {
		return productMapper.view(product_id);
	}

	@Override
	public void insert(Cart dto) {
		productMapper.insert(dto);
	}

	@Override
	public List<Cart> listCart(String userid) {
		return productMapper.listCart(userid);
	}

	@Override
	public int sum(String userid) {
		return productMapper.sum(userid);
	}

	@Override
	public void deleteCart(int cart_id) {
		productMapper.deleteCart(cart_id);
	}

	@Override
	public void updateCart(int[] cart_id, int[] amount) {
		for(int i=0; i < cart_id.length; i++) {
			Map<String, Object> map=new HashMap<>();
			map.put("cart_id", cart_id[i]);
			map.put("amount", amount[i]);
			if(amount[i] == 0) {
				productMapper.deleteCart(cart_id[i]);
			} else {
				productMapper.updateCart(map);
			}
		}
	}

	@Override
	public void deleteAllCart(String userid) {
		productMapper.deleteAllCart(userid);
	}

	@Override
	public void insertProduct(Product dto) {
		HttpServletRequest request=null;
		if(!dto.getFile().isEmpty()) {
			String filename=dto.getFile().getOriginalFilename();
			try {
				dto.getFile().transferTo(new File("E:\\work\\spring_mariaDB\\src\\main\\webapp\\WEB-INF\\views\\images\\"+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		}
		productMapper.insertProduct(dto);
	}

	@Override
	public void updateProduct(Product dto) {
		if(!dto.getFile().isEmpty()) {
			String filename=dto.getFile().getOriginalFilename();
			try {
				dto.getFile().transferTo(new File("E:\\work\\spring_mariaDB\\src\\main\\webapp\\WEB-INF\\views\\images\\"+filename));
				String filename2=productMapper.chkPicture_url(dto.getProduct_id());
				if(filename2 != null) {
					new File("E:\\work\\spring_mariaDB\\src\\main\\webapp\\WEB-INF\\views\\images\\"+filename2).delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		}
		productMapper.updateProduct(dto);
	}

	@Override
	public void deleteProduct(int product_id) {
		String filename=productMapper.chkPicture_url(product_id);
		if(filename != null) {
			new File("E:\\work\\spring_mariaDB\\src\\main\\webapp\\WEB-INF\\views\\images\\"+filename).delete();
		}
		productMapper.deleteProduct(product_id);
	}
	
}
