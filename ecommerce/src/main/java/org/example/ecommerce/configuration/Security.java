package org.example.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                    .withUser("Cleiton")
                    .password(passwordEncoder().encode("123"))
                    .roles("USER")
                .and()
                    .withUser("Charles")
                    .password(passwordEncoder().encode("123"))
                    .roles("ADMIN");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/customers/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/orders/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/products/**")
                        .hasRole("ADMIN")
                .and()
                .httpBasic();
    }
}
