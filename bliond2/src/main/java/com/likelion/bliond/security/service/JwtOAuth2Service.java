package com.likelion.bliond.security.service;

import static com.likelion.bliond.domain.member.entity.Role.ROLE_USER;

import com.likelion.bliond.domain.member.entity.AuthType;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.security.context.MemberContext;
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
public class JwtOAuth2Service extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
            .getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();

        Member member;
        if ("KAKAO".equals(registrationId)) {
            Long id = (Long) attributes.get("id");
            String username = "KAKAO_%d".formatted(id);
            Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
            String nickname = (String) profile.get("nickname");

            boolean isExits = memberRepository
                .findByUsername(username).isPresent();


            if (isExits) {
                member = memberRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
            } else {
                member = memberRepository.save(
                    Member.builder()
                        .authType(AuthType.KAKAO)
                        .username("KAKAO_%d".formatted(id))
                        .nickname(nickname)
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

        return new MemberContext(member.getUsername(), member.getNickname(), member.getId(), authorities, attributes, userNameAttributeName);
    }
}

