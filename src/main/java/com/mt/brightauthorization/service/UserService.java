package com.mt.brightauthorization.service;


import com.mt.brightauthorization.dto.UserDataDTO;
import com.mt.brightauthorization.entity.Users;
import com.mt.brightauthorization.repoitory.RoleRepository;
import com.mt.brightauthorization.repoitory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public Users save(UserDataDTO userDataDTO){

        if(Objects.isNull(userDataDTO) || Objects.isNull(userDataDTO.getPhone())){
            throw new IllegalArgumentException();
        }

        Users user = userRepository.findByPhone(userDataDTO.getPhone());

        if(Objects.nonNull(user)){
            throw new RuntimeException("User by phone:" + userDataDTO.getPhone() + "is exist");
        }

        user = modelMapper.map(userDataDTO, Users.class);
        user.setPassword(passwordEncoder.encode(userDataDTO.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);

        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Users users = userRepository.findByPhone(phone);

        if(Objects.isNull(users)){
            throw new UsernameNotFoundException("User not found by phone: " + phone);
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new User(users.getPhone(), users.getPassword(), authorities);
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
