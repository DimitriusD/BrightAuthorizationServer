package com.mt.brightauthorization.service;


import com.mt.brightauthorization.dto.UserRequestDTO;
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

    public Users save(UserRequestDTO userRequestDTO){

        if(Objects.isNull(userRequestDTO) || Objects.isNull(userRequestDTO.getPhone())){
            throw new IllegalArgumentException();
        }

        Users user = userRepository.findByPhone(userRequestDTO.getPhone());

        if(Objects.nonNull(user)){
            throw new RuntimeException("User by phone:" + userRequestDTO.getPhone() + "is exist");
        }

        user = modelMapper.map(userRequestDTO, Users.class);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);

        return user;
    }


    public Users update(UserRequestDTO updateProfile){
        return userRepository.findById(updateProfile.getId())
                .map(profile -> {
                    profile.setAge(updateProfile.getAge());
                    profile.setSurname(updateProfile.getSurname());
                    profile.setUsername(updateProfile.getUsername());
                    return userRepository.save(profile);
                })
                .orElseGet(() ->
                        userRepository.save(modelMapper.map(updateProfile, Users.class))
                );
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public Users getById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public Users findByPhone(String phone) throws UsernameNotFoundException {
        return userRepository.findByPhone(phone);
    }

    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Users users = userRepository.findByPhone(phone);

        if(Objects.isNull(users)){
            throw new UsernameNotFoundException("User by phone: " + phone + "was not found in the database");
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
