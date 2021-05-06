package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;
import com.exadel.team2.sandbox.web.interview_feedback.ResponseInterviewFeedbackDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class InterviewFeedbackMapper implements Mapper<InterviewFeedbackEntity, ResponseInterviewFeedbackDto> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseInterviewFeedbackDto convertEntityToDto(InterviewFeedbackEntity entity) {
        return modelMapper.map(entity, ResponseInterviewFeedbackDto.class);
    }

    @Override
    public InterviewFeedbackEntity convertDtoToEntity(Object dto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(dto, InterviewFeedbackEntity.class);
    }


}
