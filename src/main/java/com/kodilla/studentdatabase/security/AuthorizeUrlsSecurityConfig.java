package com.kodilla.studentdatabase.security;

import com.kodilla.studentdatabase.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AuthorizeUrlsSecurityConfig extends VaadinWebSecurity {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        setLoginView(http, LoginView.class);

        http.formLogin(formLogin ->
                formLogin
                        .defaultSuccessUrl("/main", true)
        );
    }

    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder.encode("student"))
                .roles("STUDENT")
                .build();
        UserDetails teacher = User.builder()
                .username("teacher")
                .password(passwordEncoder.encode("teacher"))
                .roles("TEACHER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("STUDENT", "TEACHER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(student, teacher, admin);
    }
}