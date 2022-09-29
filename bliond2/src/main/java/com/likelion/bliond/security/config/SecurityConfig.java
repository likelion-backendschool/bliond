package com.likelion.bliond.security.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.likelion.bliond.security.filter.JwtAuthFilter;
import com.likelion.bliond.security.handler.OAuth2SuccessHandler;
import com.likelion.bliond.security.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.likelion.bliond.security.service.JwtOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtOAuth2Service JwtOAuth2Service;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtAuthFilter jwtAuthFilter;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .headers().frameOptions().sameOrigin()
            .and()
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/event/**").permitAll()
                        .antMatchers("/login").permitAll()
                        .antMatchers("/**").authenticated()
            )
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagement ->
                sessionManagement
                    .sessionCreationPolicy(STATELESS)
            )
            .oauth2Login(oauth2Login ->
                oauth2Login
                    .loginPage("/login")
                    .authorizationEndpoint(authorizationEndpoint ->
                        authorizationEndpoint
                            .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                    )
                    .userInfoEndpoint(userInfoEndpoint ->
                        userInfoEndpoint.userService(JwtOAuth2Service)
                    )
                    .successHandler(oAuth2SuccessHandler)
                    .permitAll()
            )
            .addFilterBefore(jwtAuthFilter, LogoutFilter.class);
        return http.build();
    }
}
