package tma.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//import tma.service.MessageCallRepository;
//import tma.service.UserRepositoryDao;
// 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
//	 	@Autowired
//	    UserRepositoryDao userRepositoryDao;
 
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN","USER","DBA");
        auth.inMemoryAuthentication().withUser("dba").password("123").roles("DBA","ADMIN");
    }
    
//    @Bean
//    public UserRepositoryDao userRepositoryDao() {
//        return new userRepositoryDao();
//    }
////    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //UserDetailsService userDetailsService = mongoUserDetails();
//    	auth.use
//        auth.userDetailsService(userDetailsService);
//    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  
      http.authorizeRequests()
        .antMatchers("/", "/home").permitAll() 
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/admin/").access("hasRole('ADMIN')")
        .antMatchers("/user/**").access("hasRole('USER')")
        .antMatchers("/dba/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .and().formLogin().loginPage("/login")
        .usernameParameter("ssoId").passwordParameter("password")
        .and().csrf().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true);
    }
}