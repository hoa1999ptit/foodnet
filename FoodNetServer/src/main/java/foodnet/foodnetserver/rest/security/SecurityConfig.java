/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.security;

import foodnet.foodnetserver.rest.services.UseriDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Kushtrim Hajrizi
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UseriDetailsService userDetailsService;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/register/**", "/search").permitAll()
                .antMatchers("/login/**", "/users/**", "/businesses/**", 
                        "/foodnet/**", "/feedback", "/raports/**").authenticated()
                .and()
                .httpBasic();
    }
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MyPasswordEncoder());
    }
    
    //Temporary
    private class MyPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence cs) {
            return new BCryptPasswordEncoder().encode(cs.toString());
        }

        @Override
        public boolean matches(CharSequence cs, String string) {
            return true;
        }
        
    }
}
