package com.likelion.bliond.base;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.likelion.bliond.event.Event;
import com.likelion.bliond.event.EventDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
            .setMatchingStrategy(STRICT);
        mapper.typeMap(Event.class, EventDto.class)
            .addMappings(m -> m.map(source -> source.getMember().getId(), (destination, value) -> destination.setMemberId(
                (Long) value)));
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
