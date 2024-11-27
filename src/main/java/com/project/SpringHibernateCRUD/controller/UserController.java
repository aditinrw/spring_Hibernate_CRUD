package com.project.SpringHibernateCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.SpringHibernateCRUD.entity.User;
import com.project.SpringHibernateCRUD.service.UserService;

@RestController
@RequestMapping("/home")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@GetMapping("/get/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateUser(@RequestBody User user, @PathVariable Long id) {
		return userService.updateUser(user, id);
	}
	
	@GetMapping("/getall")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

}
