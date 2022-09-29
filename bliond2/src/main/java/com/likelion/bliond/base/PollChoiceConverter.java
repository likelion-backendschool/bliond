package com.likelion.bliond.base;

import com.likelion.bliond.domain.poll.dto.PollChoiceDto;
import com.likelion.bliond.domain.poll.entity.PollChoice;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.AbstractConverter;

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
