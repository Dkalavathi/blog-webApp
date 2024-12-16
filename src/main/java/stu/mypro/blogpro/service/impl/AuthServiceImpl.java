package stu.mypro.blogpro.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import stu.mypro.blogpro.dto.LoginDto;
import stu.mypro.blogpro.dto.RegistrationDto;
import stu.mypro.blogpro.entity.Role;
import stu.mypro.blogpro.repository.UserRepository;
import stu.mypro.blogpro.service.AuthService;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    public AuthServiceImpl() {
    }

    private AuthenticationManager authenticationManager;

    //private RoleRepository roleRepository;

    private UserRepository userRepository;

   // private PasswordEncoder passwordEncoder;
   // private ProviderManager authentication;


    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }
    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }
  /*    @Override
    public String register(RegistrationDto registerDto) {

        // add check for username exists in database
      if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!.";
    }

    public void setAuthentication(ProviderManager authentication) {
        this.authentication = authentication;
    }*/
}
