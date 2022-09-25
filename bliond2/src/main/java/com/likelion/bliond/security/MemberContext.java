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
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;

    @Getter
    private final Long id;

    @Getter
    private final String nickname;


    public MemberContext(String username, String nickname, Long id, Set<GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
        super(username, "", authorities);
        this.authorities = authorities;
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.id = id;
        this.nickname = nickname;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        return this.getAttributes().get(this.nameAttributeKey).toString();
    }
}
