package com.likelion.bliond.base;

import com.likelion.bliond.domain.event.entity.EventMember;
import com.likelion.bliond.domain.member.dto.MemberDto;
import com.likelion.bliond.domain.poll.dto.PollChoiceDto;
import com.likelion.bliond.domain.poll.dto.PollDto;
import com.likelion.bliond.domain.poll.entity.Poll;
import com.likelion.bliond.domain.poll.entity.PollChoice;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PollChoiceConverter extends AbstractConverter<List<PollChoice>, List<PollChoiceDto>> {

    @Override
    protected List<PollChoiceDto> convert(List<PollChoice> pollChoices) {
        return pollChoices.stream().map(pollChoice ->
                PollChoiceDto.builder()
                    .id(pollChoice.getId())
                    .name(pollChoice.getName())
                    .createdDate(pollChoice.getCreatedDate())
                    .modifiedDate(pollChoice.getModifiedDate())
                    .build())
                .collect(Collectors.toList());

    }
}
