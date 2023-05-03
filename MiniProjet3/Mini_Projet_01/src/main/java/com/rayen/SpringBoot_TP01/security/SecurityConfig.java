package com.rayen.SpringBoot_TP01.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .username("user")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/newFilm").hasRole("ADMIN")
                                .requestMatchers("/addRegion").hasRole("ADMIN")
                                .requestMatchers("/saveClient").hasRole("ADMIN")
                                .requestMatchers("/saveRegion").hasRole("ADMIN")
                                .requestMatchers("/updateClient").hasRole("ADMIN")
                                .requestMatchers("/updateRegion").hasRole("ADMIN")
                                .requestMatchers("/clientsTemplate/delete/**").hasRole("ADMIN")
                                .requestMatchers("/regionsTemplate/delete/**").hasRole("ADMIN")

                                .anyRequest().authenticated()

                ).formLogin(
                        form -> form
                                .loginPage("/login") // set login page to /login
                                .loginProcessingUrl("/login") // set the URL to submit the login form
                                .defaultSuccessUrl("/films", true) // set the default URL to redirect after successful login
                                .permitAll()

                ).logout(
                        logout -> logout
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/login")
                ).exceptionHandling(
                        e -> e.accessDeniedHandler((request, response, accessDeniedException) -> {
                            String redirectUrl = "/accessDenied";
                            response.sendRedirect(redirectUrl);
                        })
                );
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}