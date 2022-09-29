package com.likelion.bliond.base;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.likelion.bliond.domain.event.dto.EventDto;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.poll.dto.PollDto;
import com.likelion.bliond.domain.poll.entity.Poll;
import com.likelion.bliond.web.event.EventCreateVo;
import com.likelion.bliond.web.event.EventVo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT).setSkipNullEnabled(true);
        mapper.typeMap(Event.class, EventDto.class).addMappings(m -> {
            m.map(Event::getMember, EventDto::setMemberDto);
            m.map(source -> source.getMember().getId(), EventDto::setMemberId);
            m.using(new EventMemberSetConverter()).map(Event::getEventMembers, EventDto::setParticipants);
        });
        mapper.typeMap(EventDto.class, EventCreateVo.class).addMappings(m -> {
            m.map(source -> source.getMemberDto().getId(), EventCreateVo::setMemberId);
            m.map(source -> source.getMemberDto().getUsername(), EventCreateVo::setUsername);
            m.map(source -> source.getMemberDto().getNickname(), EventCreateVo::setNickname);
        });
        mapper.typeMap(EventDto.class, EventVo.class).addMappings(m -> {
            m.map(source -> source.getMemberDto().getId(), EventVo::setMemberId);
            m.map(source -> source.getMemberDto().getUsername(), EventVo::setUsername);
            m.map(source -> source.getMemberDto().getNickname(), EventVo::setNickname);
            m.map(EventDto::getParticipants, EventVo::setParticipants);
        });
        mapper.typeMap(Poll.class, PollDto.class).addMappings(m -> {
            m.using(new PollChoiceConverter()).map(Poll::getPollChoices, PollDto::setPollChoiceDtos);

        });
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
