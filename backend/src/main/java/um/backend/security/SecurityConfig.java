package um.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.url}")
    private String appUrl;

    @Bean
    public SecurityFilterChain customSecurity(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(a -> a
                        .requestMatchers(HttpMethod.GET, "/api/users/me").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/todo").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/todo").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/todo/*").hasAuthority("ADMIN")
                        .anyRequest().permitAll())

                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .oauth2Login(o -> o.defaultSuccessUrl(appUrl))
                .logout(l -> l.logoutSuccessUrl(appUrl))
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

        return http.build();
    }
}
