package stu.mypro.blogpro.service;

import org.springframework.stereotype.Service;
import stu.mypro.blogpro.dto.LoginDto;

@Service
public interface AuthService {
    String login(LoginDto loginDto);

   // String register(RegistrationDto registerDto);
}
