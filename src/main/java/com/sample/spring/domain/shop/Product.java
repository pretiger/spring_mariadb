package com.sample.spring.domain.shop;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Product {
	private int product_id;
	private String product_name;
	private int price;
	private String description;
	private String picture_url;
	private MultipartFile file;
}
