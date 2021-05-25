package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.mapper.EmployeeMapper;
import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import com.exadel.team2.sandbox.web.employee_availability_time.ResponseCrossedTimeSlots;
import com.exadel.team2.sandbox.web.employee_availability_time.TimeId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class EmployeeServiceImpl extends GeneralServiceImpl<EmployeeEntity,
        ResponseEmployeeDto, CreateEmployeeDto, UpdateEmployeeDto> implements EmployeeService {

    private final RoleDAO roleDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CandidateAvailabilityTimeServiceImpl candidateTimeService;
    private final EmployeeAvailabilityTimeServiceImpl employeeTimeService;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, RoleDAO roleDAO, EmployeeMapper employeeMapper,
                               CandidateAvailabilityTimeServiceImpl candidateTimeService,
                               EmployeeAvailabilityTimeServiceImpl employeeTimeService,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.generalDAO = employeeDAO;
        this.roleDAO = roleDAO;
        this.generalMapper = employeeMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.candidateTimeService = candidateTimeService;
        this.employeeTimeService = employeeTimeService;
    }

    @Override
    public ResponseEmployeeDto save(CreateEmployeeDto createEmployeeDTO) {
        EmployeeEntity employeeEntity = generalMapper.convertDtoToEntity(createEmployeeDTO);
        RoleEntity roleEntity = roleDAO.findById(createEmployeeDTO.getRoleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));

        employeeEntity.setId(null);
        employeeEntity.setPassword(bCryptPasswordEncoder.encode(createEmployeeDTO.getPassword()));
        employeeEntity.setRole(roleEntity);
        employeeEntity.setCreatedAt(LocalDateTime.now());
        employeeEntity.setUpdatedAt(LocalDateTime.now());
        return generalMapper.convertEntityToDto(generalDAO.save(employeeEntity));
    }


    @Override
    public ResponseEmployeeDto update(Long id, UpdateEmployeeDto updateEmployeeDto) {
        EmployeeEntity employeeEntity = generalDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));

        if (updateEmployeeDto.getRoleId() != null) {
            RoleEntity roleEntity = roleDAO.findById(updateEmployeeDto.getRoleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));
            employeeEntity.setRole(roleEntity);
        }

        if (updateEmployeeDto.getPassword() != null) {
            employeeEntity.setPassword(bCryptPasswordEncoder.encode(updateEmployeeDto.getPassword()));
        }

        if (updateEmployeeDto.getLastName() != null) {
            employeeEntity.setLastName(updateEmployeeDto.getLastName());
        }

        if (updateEmployeeDto.getFirstName() != null) {
            employeeEntity.setFirstName(updateEmployeeDto.getFirstName());
        }

        if (updateEmployeeDto.getPrimaryTechnology() != null) {
            employeeEntity.setPrimaryTechnology(updateEmployeeDto.getPrimaryTechnology());
        }

        if (updateEmployeeDto.getType() != null) {
            employeeEntity.setType(updateEmployeeDto.getType());
        }

        if (updateEmployeeDto.getPhone() != null) {
            employeeEntity.setPhone(updateEmployeeDto.getPhone());
        }

        if (updateEmployeeDto.getEmail() != null) {
            employeeEntity.setEmail(updateEmployeeDto.getEmail());
        }

        if (updateEmployeeDto.getSkype() != null) {
            employeeEntity.setSkype(updateEmployeeDto.getSkype());
        }

        if (updateEmployeeDto.getLocationCountry() != null) {
            employeeEntity.setLocationCity(updateEmployeeDto.getLocationCity());
        }

        if (updateEmployeeDto.getLocationCountry() != null) {
            employeeEntity.setLocationCountry(updateEmployeeDto.getLocationCountry());
        }

        if (updateEmployeeDto.getTimezone() != null) {
            employeeEntity.setTimezone(updateEmployeeDto.getTimezone());
        }

        employeeEntity.setUpdatedAt(LocalDateTime.now());
        return generalMapper.convertEntityToDto(generalDAO.save(employeeEntity));
    }

    @Override
    public void delete(Long id) {
        generalDAO.deleteById(id);
    }



    private LocalDateTime findLatestData(List<LocalDateTime> listTime) {
        Collections.sort(listTime);
        return listTime.get(listTime.size() - 1);
    }

    private List<LocalDateTime> getEmployeeListTime(Long employeeId) {
        return employeeTimeService.getByEmployeeId(employeeId).getAvailableTimeSlots().stream()
                .map(time -> time.getDateTime()).collect(Collectors.toList());
    }

    private List<TimeId> getCandidateListTime(Long candidateId) {
        return candidateTimeService.getByCandidateId(candidateId).getAvailabilityTimeSlots();
    }

    private TimeId findSuitableTime(Long employeeId, TimeId candidateTime) {
        List<LocalDateTime> employeeTime = getEmployeeListTime(employeeId);

        if (employeeTime.contains(candidateTime)) {
            log.info(candidateTime + "");
            return candidateTime;
        }

        log.info("null");

        return null;
    }

    @Override
    public ResponseCrossedTimeSlots getCrossedTime(Long employeeId, Long candidateId) {
        log.info(getCandidateListTime(candidateId) + "");
        return ResponseCrossedTimeSlots.builder().suitableTimeSlots(getCandidateListTime(candidateId).stream()
                .map(time -> findSuitableTime(employeeId, time))
                .filter(time -> time != null)
                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ResponseCrossedTimeSlots getCandidateTime(Long employeeId, Long candidateId) {
        List<LocalDateTime> employeeTime = employeeTimeService.getByEmployeeId(employeeId).getAvailableTimeSlots().stream()
                .map(time -> time.getDateTime()).collect(Collectors.toList());

        LocalDateTime latestEmployeeTime = findLatestData(employeeTime);

        List<TimeId> suitableTime = new LinkedList<>();

        for (TimeId timeDto : candidateTimeService.getByCandidateId(candidateId).getAvailabilityTimeSlots()) {
            int compareValue = latestEmployeeTime.compareTo(timeDto.getDateTime());

            if (compareValue == 0 || compareValue == 1) {
                suitableTime.add(timeDto);
            }
        }

        return ResponseCrossedTimeSlots.builder()
                .suitableTimeSlots(suitableTime)
                .candidateId(candidateId)
                .employeeId(employeeId)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeeEntity employeeEntity = ((EmployeeDAO)generalDAO).findByEmail(email);
        if (employeeEntity == null) {
            throw new UsernameNotFoundException("Incorrect login");
        }

        return new User(employeeEntity.getEmail(), employeeEntity.getPassword(),
                true, true, true, true,
                getAuthorities(employeeEntity.getRole()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(RoleEntity role) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        authorities.addAll(role.getPermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toSet()));

        return authorities;
    }
}
