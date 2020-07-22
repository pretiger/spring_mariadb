package com.sample.spring.controller.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.spring.domain.shop.Cart;
import com.sample.spring.domain.shop.Product;
import com.sample.spring.service.shop.ProductService;

@Controller
@RequestMapping("/shop/*")
public class ShopController {

	private static final Logger logger=LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	ProductService productService;
	
	@GetMapping("deleteProduct.do/{product_id}")
	public String deleteProduct(@PathVariable int product_id) {
		productService.deleteProduct(product_id);
		return "redirect:/shop/list.do";
	}
	
	@PostMapping("updateProduct.do")
	public String updateProduct(@ModelAttribute Product dto) {
		productService.updateProduct(dto);
		return "redirect:/shop/list.do";
	}
	
	@GetMapping("edit.do/{product_id}")
	public String edit(@PathVariable int product_id, Model model) {
		model.addAttribute("dto", productService.view(product_id));
		return "shop/edit";
	}
	
	@PostMapping("insertProduct.do")
	public String insertproduct(@ModelAttribute Product dto) {
		productService.insertProduct(dto);
		return "redirect:/shop/list.do";
	}
	
	@GetMapping("write.do")
	public String write() {
		return "shop/write";
	}
	
	@GetMapping("deleteAllCart.do")
	public String deleteAllCart(@RequestParam String userid) {
		productService.deleteAllCart(userid);
		return "redirect:/shop/listCart.do";
	}
	
	@PostMapping("updateCart.do")
	public String updateCart(@RequestParam int[] cart_id, @RequestParam int[] amount) {
		productService.updateCart(cart_id, amount);
		return "redirect:/shop/listCart.do";
	}
	
	@GetMapping("deleteCart.do/{cart_id}")
	public String deleteCart(@PathVariable int cart_id) {
		productService.deleteCart(cart_id);
		return "redirect:/shop/listCart.do";
	}
	
	@GetMapping("listCart.do")
	public String listcart(Model model, HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		int sum=productService.sum(userid);
		int fee= sum > 25000 ? 0 : 2500;
		int totSum=sum+fee;
		Map<String, Object> map=new HashMap<>();
		map.put("sum", sum);
		map.put("fee", fee);
		map.put("totSum", totSum);
		model.addAttribute("map", map);
		model.addAttribute("list", productService.listCart(userid));
		return "shop/cartList";
	}
	
	@PostMapping("insert.do")
	public String insert(@ModelAttribute Cart dto, HttpSession session) {
		dto.setUserid((String)session.getAttribute("userid"));
		productService.insert(dto);
		return "redirect:/shop/listCart.do";
	}
	
	@GetMapping("view.do/{product_id}")
	public String view(@PathVariable int product_id, Model model) {
		model.addAttribute("dto", productService.view(product_id));
		return "shop/view";
	}
	
	@GetMapping("list.do")
	public String list(Model model) {
		model.addAttribute("list", productService.list());
		return "shop/list";
	}
}
