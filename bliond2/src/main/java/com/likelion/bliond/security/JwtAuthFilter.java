package com.likelion.bliond.security;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);


        if (tokenService.verifyToken(token)) {
            String username = tokenService.getUsername(token);
            Long id = Long.valueOf(tokenService.getId(token));

            String authorities = tokenService.getAuthorities(token);
            Set<GrantedAuthority> grantedAuthorities = new LinkedHashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(authorities));

            MemberContext memberContext = new MemberContext(username, id, grantedAuthorities, null, null);

            UsernamePasswordAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                    memberContext,
                    null,
                    memberContext.getAuthorities()
                );
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);
    }
}
