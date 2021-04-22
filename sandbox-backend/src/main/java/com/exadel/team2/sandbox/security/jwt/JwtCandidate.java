package com.exadel.team2.sandbox.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
public class JwtCandidate implements UserDetails {

    private final Long id;
    private final Long rsmId;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
    private final String skype;
    private final String englishLevel;
    private final String mainSkill;
    private final String otherSkills;
    private final String institution;
    private final String faculty;
    private final String speciality;
    private final LocalDate graduationDate;
    private final String country;
    private final String city;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
