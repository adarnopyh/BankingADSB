package org.banking.pet.config;

import org.banking.pet.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register.html","/login.html","/register","/api/register","/css/**","/app.js").permitAll()
                        .requestMatchers("/api/users").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(f -> f
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(l -> l.logoutUrl("/logout").logoutSuccessUrl("/login.html"))
        ;
        return http.build();
    }

    @Bean PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService uds(UserRepository users) {
        return username -> {
            var u = users.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Not found"));
            var auths = u.getRoles().stream()
                    .map(r -> new SimpleGrantedAuthority("ROLE_"+r.getName()))
                    .toList();
            return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getPassword(),auths);
        };
    }
}