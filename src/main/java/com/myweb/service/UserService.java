package com.myweb.service;

import com.myweb.dto.LoginDTO;
import com.myweb.dto.UserCreateDTO;
import com.myweb.dto.UserDTO;
import com.myweb.entity.User;
import com.myweb.mapper.UserMapper;
import com.myweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserMapper userMapper;


    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User getUserById(Long userId){
        return  userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));
    }
    public List<UserDTO> getAllUsersDTO(){
        List<User> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user : listUser){
            userDTOList.add(userMapper.toUserDto(user));
        }
        return userDTOList;
    }

    public UserDTO getUserDTOById(Long userId){
        return userMapper.toUserDto(getUserById(userId));
    }


    public User createUser(UserCreateDTO userCreateDTO){
        validateUserCreateDTO(userCreateDTO);

        User user = userMapper.toUser(userCreateDTO);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        return userRepository.save(user);

    }



    public UserDTO updateUser(Long userId, UserDTO userDto){
        User user = getUserById(userId);

        UserDTO preUserDTO = userMapper.toUserDto(user);
        boolean hasChange = !preUserDTO.equals(userDto);

        if (hasChange) {
            validateUserDTO(userDto);
        }

        userMapper.updateUser(userDto, user);
        userRepository.save(user);

        return userMapper.toUserDto(user);
    }

    public void deleteUser(Long userId){
        if(userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
        }
        else{
            throw new RuntimeException("Can not find user with id: "+ userId);
        }
    }

    public void validateUserCreateDTO(UserCreateDTO userCreateDTO){
        if(userRepository.existsByUsername(userCreateDTO.getUsername())){
            throw new RuntimeException("Username existed!");
        }
        if(userRepository.existsByEmail(userCreateDTO.getEmail())){
            throw new RuntimeException("Email existed!");
        }
        if(userRepository.existsByPhoneNumber(userCreateDTO.getPhoneNumber())){
            throw new RuntimeException("Phone number existed!");
        }

        //validate dob
        LocalDate dob = userCreateDTO.getDob();
        Period period = Period.between(dob, LocalDate.now());
        if(period.getYears() < 18){
            throw new RuntimeException("You must be 18 or older to use our service");
        }
    }

    public void validateUserDTO(UserDTO userDTO){

        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new RuntimeException("Email existed!");
        }
        if(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())){
            throw new RuntimeException("Phone number existed!");
        }

        //validate dob
        LocalDate dob = userDTO.getDob();
        Period period = Period.between(dob, LocalDate.now());
        if(period.getYears() < 18){
            throw new RuntimeException("You must be 18 or older to use our service");
        }
    }

}
