package com.likelion.bliond.security;

import static com.likelion.bliond.member.Role.ROLE_USER;

import com.likelion.bliond.member.AuthType;
import com.likelion.bliond.member.Member;
import com.likelion.bliond.member.MemberRepository;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2Service extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        Map<String, Object> attributes = oAuth2User.getAttributes();

        Member member;
        if ("KAKAO".equals(registrationId)) {
            Long id = (Long) attributes.get("id");
            String username = "KAKAO_%d".formatted(id);

            boolean isExits = memberRepository.findByUsername(username).isPresent();


            if (isExits) {
                member = memberRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
            } else {
                member = memberRepository.save(
                    Member.builder()
                        .authType(AuthType.KAKAO)
                        .username("KAKAO_%d".formatted(id))
                        .role(ROLE_USER)
                        .authKey(id.toString())
                        .build()
                );
            }
        } else {
            throw new OAuth2AuthenticationException("지원하지 않는 OAuth2 로그인입니다.");
        }

        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().toString()));

        return new MemberContext(member.getUsername(), member.getId(), authorities);
    }
}

