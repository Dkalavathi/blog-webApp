package stu.mypro.blogpro.service.impl;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stu.mypro.blogpro.dto.RegistrationDto;
import stu.mypro.blogpro.entity.Role;
import stu.mypro.blogpro.entity.User;
import stu.mypro.blogpro.repository.RoleRepository;
import stu.mypro.blogpro.repository.UserRepository;
import stu.mypro.blogpro.service.UserService;

import java.util.Arrays;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        System.out.println(user);
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        System.out.println(registrationDto.getEmail());
        // use spring security to encrypt the password

        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        System.out.println("password:" +passwordEncoder.encode(registrationDto.getPassword()));
        //userRepository.findByUserName(registrationDto.getFirstName());
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //public User findByUserName(String userName){return userRepository.findByUserName(userName);}

}
