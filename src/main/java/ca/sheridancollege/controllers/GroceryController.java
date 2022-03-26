package ca.sheridancollege.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Cart;
import ca.sheridancollege.beans.Grocery;
import ca.sheridancollege.database.DatabaseAccess;
import ca.sheridancollege.database.TheDatabase;

@Controller
public class GroceryController {
	@Autowired
	private DatabaseAccess ds;
	
	@PostMapping("/save")
	public String saveGrocery(Model model, @RequestParam String grocName, @RequestParam String price, @RequestParam String category) {
		
		ds.addGroc(grocName,price,category);
		return "groceries.html";
	}
	
	@GetMapping("/viewDrinks")
	public String viewDrink(Model model) {
		
		model.addAttribute("drinks",ds.getDrinks());
		System.out.println("Hello");
		return "viewDrinks.html";
	}
	@GetMapping("/viewFruits")
	public String viewFruits(Model model) {
		
		model.addAttribute("fruits",ds.getFruits());
		System.out.println("Hello");
		return "viewFruits.html";
	}
	@GetMapping("/viewSnacks")
	public String viewSnacks(Model model) {
		
		model.addAttribute("snacks",ds.getSnacks());
		System.out.println("Hello");
		return "viewSnacks.html";
	}
	@GetMapping("/viewMeat")
	public String viewMeats(Model model) {
		
		model.addAttribute("meats",ds.getMeat());
		System.out.println("Hello");
		return "viewMeats.html";
	}
	
	@GetMapping("/view")
	public String viewGroceries() {
		
		return "groceries.html";
	}
	
	@GetMapping("/modify")
	public String modifyDrink(Model model,@RequestParam String grocId, @RequestParam String grocName, @RequestParam double price, @RequestParam String category) {
		
		ds.editDrink(grocId,  grocName,  price,  category);
		
		return "groceries.html";
	}
	
//	@GetMapping("/edit/{id}")
//	public String editLink(Model model, @PathVariable int id) {
//		
//		Grocery g =ds.getGrocerybyId(id);
//		
//		model.addAttribute("grocId",g.getGrocId());
//		model.addAttribute("grocName",g.getGrocName());
//		model.addAttribute("price",g.getPrice());
//		model.addAttribute("category",g.getCategory());
//		g.toString();
//	
//		return "update/update.html";
//	}
	@GetMapping("/edit/{id}")
	public String editLink(Model model, @PathVariable int id) {
		
		Grocery g =ds.getGrocerybyId(id);
		
		model.addAttribute("grocery",g);
		
		//g.toString();
	
		return "edit/edit.html";
	}
	@GetMapping("/deleteGroc")
	public String delete(Model model,@RequestParam String grocId, @RequestParam String grocName, @RequestParam double price, @RequestParam String category) {
		
		ds.deleteGrocery(grocId,  grocName,  price,  category);
		
		return "groceries.html";
	}
	
	@GetMapping("/deleteItem/{item}")
	public String deleteItem(Model model, @PathVariable String item,@PathVariable double amount) {
		//TheDatabase.cartList.indexOf(model)
		int index=TheDatabase.cartList.indexOf(new Cart(item,amount));
		System.out.println(index);
		TheDatabase.cartList.remove(index);
		return "cart.html";
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String deleteGrocery(Model model, @PathVariable int id) {
		
		Grocery g =ds.getGrocerybyId(id);
		//ds.deleteGrocery(id);
		model.addAttribute("grocery",g);
		return "delete/delete.html";
	}
	@GetMapping("/loadCart/{id}")
	public String loadCart( @PathVariable int id) {
		
		Cart c = ds.loadCart(id);
		
		TheDatabase.cartList.add(c);
	
		return "groceries.html";
	}
	
	@GetMapping("/viewCart")
	public String viewCart(Model model) {
		model.addAttribute("cartList", TheDatabase.cartList);
		return "cart.html";
	}
	
	@GetMapping("/MemberCheckout")
	public String memberCheckout(Model model){
		double total=0;
		for(int i=0; i<TheDatabase.cartList.size();i++) {
			total+=TheDatabase.cartList.get(i).getAmount();
		}
		total = 0.9*total;
		Cart c = new Cart("Total",total);
		TheDatabase.cartList.add(c);
		model.addAttribute("cartList", TheDatabase.cartList);
		
		return "MemberCheckout/checkout.html";
	}
	@GetMapping("/checkout")
	public String checkout(Model model){
		double total=0;
		for(int i=0; i<TheDatabase.cartList.size();i++) {
			total+=TheDatabase.cartList.get(i).getAmount();
		}
		//total = 0.9*total;
		Cart c = new Cart("Total",total);
		TheDatabase.cartList.add(c);
		model.addAttribute("cartList", TheDatabase.cartList);
		
		return "guest/checkout.html";
	}
	@GetMapping("/editCart")
	public String editCart(Model model){
		TheDatabase.cartList.remove(TheDatabase.cartList.size()-1);
		model.addAttribute("cartList", TheDatabase.cartList);
		return "cart.html";
	}
	@GetMapping("/pay")
	public String pay() {
		TheDatabase.cartList.clear();
		return "thanks.html";
	}
	
	
}


