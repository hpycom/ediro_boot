package com.ediro.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * @author hpycom
 * @Date 2018-06-16

 */

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	ediroUsersService _ediroUsersService;
	
	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("# security config..");
        
        http.authorizeRequests().antMatchers("/guest/**").permitAll();
        http.authorizeRequests().antMatchers("/bookStore/**").hasRole("BOOKSTORE");
        http.authorizeRequests().antMatchers("/publisher/**").hasRole("PUBLISHER");
        http.formLogin().loginPage("/login");
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        
        http.logout().invalidateHttpSession(true);
        
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true); 
        
       
      //  http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login")).and().authorizeRequests()
	//	.antMatchers("/manager/**").hasRole("MANAGER")
	//	.antMatchers("/admin/**").hasRole("ADMIN")
	//	.and().formLogin().successHandler(successHandler)
	//	.loginPage("/login").and().logout().permitAll();
        
        http.rememberMe().key("ediro").userDetailsService(_ediroUsersService).tokenRepository(getJDBCRepository()).tokenValiditySeconds(60*60*24*14);
     
    }

	private PersistentTokenRepository getJDBCRepository () {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }
	    /*public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        log.info("## build auth global");
	        
	      //  auth.inMemoryAuthentication().withUser("manager").password("{noop}1111").roles("MANAGER");
	        String query1 = "SELECT memberid username, CONCAT('{noop}',member_pwd) password, true enabled FROM tbl_members where memberid = ?";
	        String query2 = "SELECT member_role uid, role_name role FROM tbl_member_roles WHERE member_role = ?";

	        auth.jdbcAuthentication()
	                .dataSource(dataSource)
	                .usersByUsernameQuery(query1)
	                .rolePrefix("ROLE_")
	                .authoritiesByUsernameQuery(query2);
	    }*/
}

