package com.hellokoding.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    
    /*
     * Hashfunktion 
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /*
     * Enthaelt die Information wie User authentifiziert werden müssen.
     * Spring Security wird durch den WebSecurityConfigureAdapter informiert
     * der eine default Configuration in dieser Methode.
     * 
     * Durch das adden von "children" zu http.authorizedRequests() können weitere
     * bedingungen neben der, dass der User authentifiziert sein muss, hinzugeführt 
     * werden. Wie hier zu sehen eben: .antMatchers() etc.
     * 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                	// Children 
                	// Jeder kann auf die URL zugreifen
                    .antMatchers("/resources/**", "/registration").permitAll()
                    // Fordert nur das der User authentifiziert ist
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                	// Pfad zur Loginpage
                    .loginPage("/login")
                    // Erlaubt jedem User für jede URL sich anzumelden 
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
    
    /*
     * Custom Authentifikation -> userDetailsService()
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}