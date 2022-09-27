package antifraud.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // @formatter:off
        http.httpBasic()
                    .authenticationEntryPoint(restAuthenticationEntryPoint()) // Handles auth error
                .and()
                    .csrf().disable()
                    .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() // manage access
                        .mvcMatchers(HttpMethod.GET, "/api/auth/list").authenticated()
                        .mvcMatchers(HttpMethod.DELETE, "/api/auth/user/*").authenticated()
                        .mvcMatchers(HttpMethod.POST, "/api/antifraud/transaction").authenticated()
                        .mvcMatchers(HttpMethod.POST, "/api/auth/user").permitAll()
                        .mvcMatchers("/actuator/shutdown").permitAll() // needs to run test
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // no session
        // @formatter:on

        return http.build();
    }

    // What does it do????
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (request, response, authException) -> response.sendError(
                HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
    }
}