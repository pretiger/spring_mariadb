package com.sample.spring.domain.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Cart {
	private int cart_id;
	private int product_id;
	private String product_name;
	private String userid;
	private int amount;
	private int price;
	private int money;
}
