package com.likelion.bliond.base;

import com.likelion.bliond.domain.event.entity.EventMember;
import com.likelion.bliond.domain.member.dto.MemberDto;
import com.likelion.bliond.domain.member.entity.Member;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.AbstractConverter;

public class EventMemberSetConverter extends AbstractConverter<Set<EventMember>, List<MemberDto>> {

    @Override
    protected List<MemberDto> convert(Set<EventMember> eventMembers) {
        return eventMembers.stream()
            .map(eventMember -> {
                Member member = eventMember.getMember();
                return MemberDto.builder()
                    .id(member.getId())
                    .authType(member.getAuthType())
                    .authKey(member.getAuthKey())
                    .username(member.getUsername())
                    .nickname(member.getNickname())
                    .role(member.getRole())
                    .accessToken(member.getAccessToken())
                    .eventNickname(eventMember.getEventNickname())
                    .build();
            })
            .collect(Collectors.toList());
    }
}
