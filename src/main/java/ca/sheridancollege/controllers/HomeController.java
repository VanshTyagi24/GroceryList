package ca.sheridancollege.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Grocery;
import ca.sheridancollege.database.DatabaseAccess;

@Controller
public class HomeController {
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String goHome() {
		return "index.html";
	}
	
	@GetMapping("/admin")
	public String goHomeAdmin() {
		return "/admin/index.html";
	}
	@GetMapping("/guest")
	public String goHomeGuest() {
		return "/guest/index.html";
	}
//	@GetMapping("/MemberCheckout")
//	public String co() {
//		return "/member/checkout.html";
//	}

	
	@GetMapping("/member")
	public String goHomeUser() {
		return "/member/index.html";
	}
	@GetMapping("/create")
	public String goUpdate() {
		return "/create/addGrocery.html";
	}
	@GetMapping("/edit")
	public String goAdd() {
		return "/edit/edit.html";
	}
	@GetMapping("/delete")
	public String goDel() {
		return "/delete/delete.html";
	}
	
	@GetMapping("/login")
	public String goLoginPage() {
		return "login.html";
	}

	
	@GetMapping("/access-denied")
	public String goAccessDenied() {
		return "/error/access-denied.html";
	}
	
	@GetMapping("/register")
	public String goRegistration() {
		return "registration.html";
	}
	
	@PostMapping("/register")
	public String processRegistration(@RequestParam String name, @RequestParam String password) {
		da.addUser(name,password);
		long userId = da.findUserAccount(name).getUserId();
//		da.addRole(userId, 1);
		da.addRole(userId, 2);
//		da.addRole(userId, 2);
		return "login.html";
	}
	
	

}
