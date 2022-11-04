package com.example.BookManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.BookManagement.User.User;
import com.example.BookManagement.User.UserDAO;

public class CustomerUserDetailService implements UserDetailsService {
	
	private UserDAO userDAO = new UserDAO();
	
	@Override
	public UserDetails loadUserByUsername(String UserName) throws UsernameNotFoundException {
		List<User> listUser = userDAO.selectUser(UserName);
		if(listUser.size()>0) {
			User user = listUser.get(0);
			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
			grantList.add(authority);
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassWord(), grantList);
			return userDetails;
		}else {
			new UsernameNotFoundException("Login fail!");
		}
		return null;
	}

}
