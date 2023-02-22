package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.AdministratieDto;
import com.utm.msei.persistence.dto.UserDto;
import com.utm.msei.persistence.entity.UserEntity;
import com.utm.msei.persistence.mapper.UserMapper;
import com.utm.msei.persistence.repository.UserRepository;
import com.utm.msei.security.PasswordHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDto save(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Transactional
    public void delete(int idUser) {
        if (userRepository.existsById(idUser)) {
            userRepository.deleteById(idUser);
        } else {
            throw new IllegalStateException("no user");
        }
    }

    @Transactional
    public UserDto findByCredentials(String email, String password) {
        UserDto userDto = userMapper.toDto(userRepository.findByEmail(email));
        if (userDto != null) {
            String[] salt_hash = PasswordHandler.getSaltAndHashFromRecord(userDto.getPassword());
            if (PasswordHandler.validatePassword(password, salt_hash[0], salt_hash[1])) {
                return userDto;
            }
        }
        return null;
    }
}
