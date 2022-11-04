package com.example.BookManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.BookManagement.User.User;
import com.example.BookManagement.service.CustomerUserDetailService;

@Controller
public class BookController {
	
	private BookDAO bookDAO = new BookDAO();

	@GetMapping("/book")
	public String getBooks(Model model) throws IOException{
		User user = new User();
		List<Book> book = bookDAO.selectAllBooks();
		model.addAttribute("books",book);
		model.addAttribute("user", user);
		return "books";
	}
	
	@GetMapping("/home")
	public String getHome() {
		return "home";
	}
	
	@GetMapping("/book/Login")
	public String getLogin(Model model) {
		return "/Login/Login_index";
	}
	
	@GetMapping("/book/SignUp")
	public String getSignUp(Model model) {
		model.addAttribute("user",new User());
		return "/SignUp/SignUp_index";
	}
	
	@GetMapping("/book/{BookCode}")
	public String getBook(Model model, @PathVariable String BookCode) {
		model.addAttribute("BookCode",BookCode);
		Book book = bookDAO.selectBook(Integer.valueOf(BookCode));
		model.addAttribute("book", book);
		return "book-detail";
	}
	
	@GetMapping("/book/delete/{BookCode}")
	public String getDeleteBook(Model model, @PathVariable String BookCode) {
		model.addAttribute("BookCode",BookCode);
		Book book = bookDAO.selectBook(Integer.valueOf(BookCode));
		model.addAttribute("book", book);
		return "book-delete";
	}
	
	@DeleteMapping("/book/delete/SaveDelete/{BookCode}")
	public String deleBook(ModelMap model, @PathVariable String BookCode) throws SQLException {
		bookDAO.deleteBook(BookCode);
		return "redirect:/book"; 
	}
	
	@PostMapping("/book/save/{bookcode}")
	public String addBook(Book book,@PathVariable String bookcode) throws SQLException {
		bookDAO.insertBook(book);
		return "redirect:/book";
	}
	
	@PutMapping("/book/save/{bookcode}")
	public String updateBook(Book book,@PathVariable String bookcode) throws SQLException {
		bookDAO.updateBook(book);
		return "redirect:/book";
	}
}
