package com.vanilla.config;

import com.vanilla.serviceimpl.UserSecurityServiceImpl;
import com.vanilla.utilities.Securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
        
       
        
        @Autowired
      private   UserSecurityServiceImpl userSecurityService ;

        private BCryptPasswordEncoder passwordEncoder() {
		return Securities.passwordEncoder();
	}

    public static  final String[] PUBLIC_MATCHERS ={
            "/js/**",

            "/css/**",
            "/index.html", 
            "/",
            "/register"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home.html")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                        .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS)
                .permitAll()
                .anyRequest()
                .authenticated();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService( userSecurityService).passwordEncoder(passwordEncoder());
    }
   
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.inMemoryAuthentication().withUser("top").password("{noop}kalu").roles("user").
//                 and()  .withUser("cor").password("{noop}kalu").roles("user");
//         auth.inMemoryAuthentication().withUser("abu").password("{noop}kalu").roles("user");
//     }
}