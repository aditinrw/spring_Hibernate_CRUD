package com.project.SpringHibernateCRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpringHibernateCRUD.dao.UserRepository;
import com.project.SpringHibernateCRUD.entity.User;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public String registerUser(User user) {
		return userRepository.insertData(user);
	}
	
	public User getUser(Long id) {
		return userRepository.getUser(id);
	}
	
	public List<User> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
	public String updateUser(User user,Long id) {
		return userRepository.updateUser(user, id);
	}
	
	public String deleteUser(Long id) {
		return userRepository.deleteData(id);
	}
	
}

