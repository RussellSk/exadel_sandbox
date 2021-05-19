package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.CandidateAvailabilityTimeDAO;
import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.entity.CandidateAvailabilityTimeEntity;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.mapper.ModelMap;
import com.exadel.team2.sandbox.service.CandidateAvailabilityTimeService;
import com.exadel.team2.sandbox.web.canidate_availability_time.CreateCandidateAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.canidate_availability_time.ResponseCandidateAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.canidate_availability_time.UpdateCandidateAvailabilityTime;
import com.exadel.team2.sandbox.web.employee_availability_time.TimeId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidateAvailabilityTimeServiceImpl extends GeneralServiceImpl<CandidateAvailabilityTimeEntity,
        ResponseCandidateAvailabilityTimeDto, CreateCandidateAvailabilityTimeDto,
        UpdateCandidateAvailabilityTime> implements CandidateAvailabilityTimeService {

    private final CandidateDAO candidateDAO;
    private final CandidateAvailabilityTimeDAO candidateAvailabilityTimeDAO;

    @Override
    public ResponseCandidateAvailabilityTimeDto getByCandidateId(Long id) {
        List<CandidateAvailabilityTimeEntity> availabilityEntities =
                candidateAvailabilityTimeDAO.findAllByCandidateId(id);

        ResponseCandidateAvailabilityTimeDto responseDto = new ResponseCandidateAvailabilityTimeDto();

        if (availabilityEntities == null) {
            return responseDto;
        }

        responseDto.setCandidateId(id);
        availabilityEntities.forEach(dateTime -> responseDto.getAvailabilityTimeSlots().add(new TimeId(dateTime.getId(), dateTime.getDateTime())));

        return responseDto;
    }

    @Override
    public void deleteAllByCandidateId(Long id) {
        candidateAvailabilityTimeDAO.deleteAllByCandidateId(id);
    }

    @Override
    public ResponseCandidateAvailabilityTimeDto save(CreateCandidateAvailabilityTimeDto createDTO) {
        CandidateEntity candidateEntity = candidateDAO.findById(createDTO.getCandidateId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found"));

        ResponseCandidateAvailabilityTimeDto responseDto = new ResponseCandidateAvailabilityTimeDto();
        responseDto.setCandidateId(createDTO.getCandidateId());

        createDTO.getAvailabilityTimeSlots().forEach(dateTime -> {
            CandidateAvailabilityTimeEntity availabilityEntity = new CandidateAvailabilityTimeEntity();
            availabilityEntity.setId(null);
            availabilityEntity.setCandidate(candidateEntity);
            availabilityEntity.setDateTime(dateTime);
            availabilityEntity.setCreatedAt(LocalDateTime.now());
            availabilityEntity.setUpdatedAt(LocalDateTime.now());
            Long id = candidateAvailabilityTimeDAO.save(availabilityEntity).getId();
            responseDto.getAvailabilityTimeSlots().add(new TimeId(id, dateTime));
        });

        return responseDto;
    }
}
