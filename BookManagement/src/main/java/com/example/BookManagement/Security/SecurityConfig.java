package com.example.BookManagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.BookManagement.service.CustomerUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/home","/css/**","/css/themify-icons/**","/upload/**","/book/SignUp","/user/save").permitAll()
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/book/Login").permitAll()
		.defaultSuccessUrl("/book").loginProcessingUrl("/j_spring_security_check");
		
		http.rememberMe();
		
		http.logout().logoutUrl("/logout")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
		.clearAuthentication(true)
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID", "remember-me")
		.logoutSuccessUrl("/book/Login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new CustomerUserDetailService()).passwordEncoder(passwordEncoder());
	}

}
