package com.demo.config;

import com.demo.service.UserInfoUserDetailsService;
import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    //bean to define user details----------authentication
    @Bean
    public UserDetailsService userDetailsService(){
    //public UserDetailsService userDetailsService( PasswordEncoder encoder){

        /*UserDetails admin= User.withUsername("pooja")
                //.password("admin123")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails user=User.withUsername("user")
                //.password("user123")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();
        return  new InMemoryUserDetailsManager(admin,user);
  */

        /* to get username and password from db*/
        return new UserInfoUserDetailsService();

    }


    // bean to encode password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //bean for------------- authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

       return httpSecurity.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/home/public","/users/addUser")
                .permitAll()
                .and().authorizeHttpRequests()
                .requestMatchers("/home/**")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .build();


    }


    //No AuthenticationProvider found for org.springframework.security.authentication.UsernamePasswordAuthenticationToken
    //bean for authenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
