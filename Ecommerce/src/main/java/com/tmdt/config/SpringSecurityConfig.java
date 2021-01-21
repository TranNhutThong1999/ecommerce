package com.tmdt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tmdt.security.CustomSuccessHandler;
import com.tmdt.security.CustomUserDetail;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustomUserDetail customUserDetail;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public CustomSuccessHandler customSuccessHandler() {
		return new CustomSuccessHandler();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetail).passwordEncoder(passwordEncoder());
	}
	public void configure(HttpSecurity http) throws Exception {
		http
	      .csrf().disable()
	      .authorizeRequests().antMatchers("/*").permitAll()
	     // .antMatchers("/home", "/login","/register", "/logout","/static/**","/bootstrap/**","/admin/**").permitAll()
	     // .anyRequest().authenticated()
	      .and()
	      .formLogin()
	      .loginProcessingUrl("/j_spring_security_login")
	      .loginPage("/login")
	      .usernameParameter("username").passwordParameter("password")
		  .failureUrl("/login?message=login_FAIL")
		  .defaultSuccessUrl("/home?message=login_SUCCESS", true);
		  //.and().exceptionHandling().accessDeniedPage("/403");
	      
	}
	@Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
        .antMatchers( "/static/**");
      //, "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**","/bootstrap/**"
    }
	
}
