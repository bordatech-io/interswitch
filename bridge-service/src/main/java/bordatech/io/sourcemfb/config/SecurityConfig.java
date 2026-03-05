package bordatech.io.sourcemfb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/CardFusion/**")).authenticated() // Secure /CardFusion routes
                        .anyRequest().permitAll()                                                   // Allow all other routes
                )
                .httpBasic(httpBasic -> {}); // Enable HTTP Basic Authentication
        return http.build();
    }
}



