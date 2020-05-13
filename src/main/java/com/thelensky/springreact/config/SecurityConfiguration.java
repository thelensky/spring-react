package com.thelensky.springreact.config;

import com.thelensky.springreact.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private UserDetailsServiceImpl userDetailsService;
  @SuppressWarnings("unused")
  private DataSource dataSource;

  public SecurityConfiguration(UserDetailsServiceImpl userDetailsService,
                               DataSource dataSource) {
    this.dataSource = dataSource;
    this.userDetailsService = userDetailsService;
  }

  // in memory
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();

  }

  // in db
  // @Bean
  // public PersistentTokenRepository persistentTokenRepository() {
  // JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
  // db.setDataSource(this.dataSource);
  // return db;
  // }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    return new InMemoryTokenRepositoryImpl();
  }

  @Autowired
  public void configureGlobal(
      AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/api/record",
                     "/delete")
        .hasAnyAuthority("ADMIN")
        .antMatchers("/",

                     "/build/bundle.js",
                     "/login_check",
                     "/logout",
                     "/h2/**")
        .permitAll()

        .and()
        .formLogin()
        .loginProcessingUrl("/login_process")
        .and()
        .logout()
        .logoutUrl("/logout")
        .deleteCookies("JSESSIONID")
        .and()
        .headers()
        .frameOptions()
        .disable();

    http.authorizeRequests()
        .and()
        .rememberMe()
        .tokenRepository(this.persistentTokenRepository())
        .tokenValiditySeconds(1 * 24 * 60 * 60);
  }
}
