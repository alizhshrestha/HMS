package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.request.UserRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.model.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    //save admin
    UserResponseDto createUser(UserRequestDto userDto);

    //update admin
    UserResponseDto updateUser(UserRequestDto userDto, Integer userId);

    //get admin
    UserResponseDto getUserById(Integer userId);

    //get all admins
    List<UserResponseDto> getAllUsers();

    //delete admins
    void deleteUser(Integer userId);

}
