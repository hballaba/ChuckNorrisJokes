package com.hballaba.chucknorris.config;

import com.hballaba.chucknorris.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
     */



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/jokes/**")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }

//    in-memory
 /*   @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user1")
                .password("{bcrypt}$2a$12$7Xk5y6VVR.GhTgR6oRtMt.EXWI9QB3uCh2FmsYcTuMrnwI5z9P/u6")
                //user1
                .roles("USER")
                .build();

        System.out.println("User " + user);
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$s5zpt47lWou/BgX5.6FXmOAFXCTwS9zjbsg4bfrEiNFh9.pnksLOm")
                //admin
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
*/

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {

      // need for create some users
       /*
        UserDetails user = User.builder()
                .username("user1")
                .password("{bcrypt}$2a$12$7Xk5y6VVR.GhTgR6oRtMt.EXWI9QB3uCh2FmsYcTuMrnwI5z9P/u6")
                //user1
                .roles("USER")
                .build();

//        System.out.println("User " + user);
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$s5zpt47lWou/BgX5.6FXmOAFXCTwS9zjbsg4bfrEiNFh9.pnksLOm")
                //admin
                .roles("ADMIN")
                .build();
*/
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
  //      jdbcUserDetailsManager.createUser(user);
    //    jdbcUserDetailsManager.createUser(admin);
        return jdbcUserDetailsManager;

    }


/*
    DaoAuthenticationProvider

     //проверяет есть такой юзер если есть ложит в SpringSecurityContext
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }


*/
//   Для преобразования пароля через крипту
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }

 */
}

/*
for jdbcAuthorities
create table users (
    username varchar(255) not null primary key, password varchar(255) not null, enabled boolean not null
);

create table authorities (
    username varchar(255) not null,
    authority varchar(255) not null,
    foreign key (username) references users (username), unique (username, authority)
);
 */