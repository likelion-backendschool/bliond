package com.likelion.bliond.base;

import com.likelion.bliond.domain.event.entity.EventMember;
import com.likelion.bliond.domain.member.entity.Member;
import java.util.List;
import java.util.Set;
import org.modelmapper.AbstractConverter;

public class EventMemberSetConverter extends AbstractConverter<Set<EventMember>, List<Member>> {

    @Override
    protected List<Member> convert(Set<EventMember> eventMembers) {
        return eventMembers.stream()
            .map(EventMember::getMember)
            .toList();
    }
}
