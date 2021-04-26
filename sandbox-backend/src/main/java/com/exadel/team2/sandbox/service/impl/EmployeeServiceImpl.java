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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl extends GeneralServiceImpl<EmployeeEntity,
        ResponseEmployeeDto, CreateEmployeeDto, UpdateEmployeeDto> implements EmployeeService {

    private final RoleDAO roleDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, RoleDAO roleDAO,
                               EmployeeMapper employeeMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.generalDAO = employeeDAO;
        this.roleDAO = roleDAO;
        this.generalMapper = employeeMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ResponseEmployeeDto save(CreateEmployeeDto createEmployeeDTO) {
        EmployeeEntity employeeEntity = generalMapper.convertDtoToEntity(createEmployeeDTO);
        RoleEntity roleEntity = roleDAO.findById(createEmployeeDTO.getRoleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));

        employeeEntity.setPassword(bCryptPasswordEncoder.encode(createEmployeeDTO.getPassword()));
        employeeEntity.setRole(roleEntity);
        employeeEntity.setCreatedAt(LocalDateTime.now());
        employeeEntity.setUpdatedAt(LocalDateTime.now());
        generalDAO.save(employeeEntity);
        return generalMapper.convertEntityToDto(employeeEntity);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeeEntity employeeEntity = ((EmployeeDAO)generalDAO).findByEmail(email);
        if (employeeEntity == null) {
            throw new UsernameNotFoundException("Incorrect login");
        }

        UserDetails userDetails = new User(employeeEntity.getEmail(), employeeEntity.getPassword(),
                true, true, true, true,
                getAuthorities(employeeEntity.getRole()));
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(RoleEntity role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        authorities.addAll(role.getPermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toList()));
        return authorities;
    }
}
