package org.solowev.taskmanager.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.auth.domain.Role;
import org.solowev.taskmanager.auth.domain.User;
import org.solowev.taskmanager.auth.dto.request.UserRequestDto;
import org.solowev.taskmanager.auth.dto.response.TokenResponseDto;
import org.solowev.taskmanager.auth.dto.response.UserResponseDto;
import org.solowev.taskmanager.auth.exceptions.NotFoundException;
import org.solowev.taskmanager.auth.mapper.UserRequestMapper;
import org.solowev.taskmanager.auth.mapper.UserResponseMapper;
import org.solowev.taskmanager.auth.repository.RoleRepository;
import org.solowev.taskmanager.auth.repository.UserRepository;
import org.solowev.taskmanager.auth.service.HelperService;
import org.solowev.taskmanager.auth.service.TokenService;
import org.solowev.taskmanager.auth.service.UserService;
import org.solowev.taskmanager.auth.utils.enums.AccountType;
import org.solowev.taskmanager.auth.utils.enums.RoleEnum;
import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserResponseMapper userResponseMapper;

    private final UserRequestMapper userRequestMapper;

    private final RoleRepository roleRepository;

    private final HelperService helperService;

    private final TokenService tokenService;

    @Override
    public UserResponseDto registration(UserRequestDto userDto) {
        if (userDto.getEmail() == null) {
            throw new TaskManagerException(ErrorCode.BAD_REQUEST, "User email should be filled");
        }

        if (userRepository.existsByUsernameOrEmail(userDto.getUsername(), userDto.getEmail())) {
            throw new TaskManagerException(ErrorCode.RESOURCE_EXISTS_EXCEPTION,
                    "User email or username already exists");
        }

        Role role = roleRepository.findByRoleName(RoleEnum.ROLE_USER);

        User user = userRequestMapper.toEntity(userDto);
        user.setRoles(List.of(role));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAccountType(userDto.getAccountType() == null ? AccountType.PERSONAL : userDto.getAccountType());
        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = userResponseMapper.toDto(savedUser);

        TokenResponseDto tokenResponse = tokenService.createTokens(user);
        userResponseDto.setTokens(tokenResponse);

        return userResponseDto;
    }

    @Override
    public UserResponseDto auth(UserRequestDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new NotFoundException("Couldn't find user with username " + userDto.getUsername()));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        TokenResponseDto tokenResponseDto = tokenService.createTokens(user);

        UserResponseDto userResponseDto = userResponseMapper.toDto(user);
        userResponseDto.setTokens(tokenResponseDto);

        return userResponseDto;
    }

    @Override
    public UserResponseDto getUser() {
        Long userId = helperService.getCurrentUser().getId();

        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        return userResponseMapper.toDto(user);
    }
}
