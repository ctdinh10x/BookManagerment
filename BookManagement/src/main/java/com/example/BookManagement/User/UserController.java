package com.example.BookManagement.User;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.BookManagement.Book;

@Controller
public class UserController {

	private UserDAO userDAO = new UserDAO();
	
	@PostMapping("/user/save")
	public String addUser(User user) throws SQLException {
		userDAO.insertUser(user);
		return "redirect:/home";
	}
}
