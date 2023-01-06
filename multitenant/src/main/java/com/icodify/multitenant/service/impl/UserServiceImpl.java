package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.request.UserRequestDto;
import com.icodify.multitenant.model.dto.response.UserResponseDto;
import com.icodify.multitenant.model.entities.User;
import com.icodify.multitenant.repository.UserRepository;
import com.icodify.multitenant.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userDto) {

        User user = User.builder()
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .image(userDto.getImage())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .status(userDto.getStatus())
                .isVerified(userDto.getIsVerified())
                .rememberToken(userDto.getRememberToken())
                .lastActive(userDto.getLastActive())
                .deletedAt(userDto.getDeletedAt())
                .build();

        User savedUser = userRepository.save(user);


        return this.modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userDto, Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setImage(userDto.getImage());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(userDto.getStatus());
        user.setIsVerified(userDto.getIsVerified());
        user.setRememberToken(userDto.getRememberToken());
        user.setLastActive(userDto.getLastActive());
        user.setDeletedAt(userDto.getDeletedAt());

        User updatedUser = userRepository.save(user);

        return this.modelMapper.map(updatedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> this.modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
