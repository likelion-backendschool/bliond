package com.likelion.bliond.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class MemberContext extends User implements OAuth2User {
    private final Set<GrantedAuthority> authorities;

    @Getter
    private final Long id;


    public MemberContext(String username, Long id, Set<GrantedAuthority> authorities) {
        super(username, "", authorities);
        this.authorities = authorities;
        this.id = id;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
