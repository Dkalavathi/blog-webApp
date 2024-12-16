package stu.mypro.blogpro.service;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import stu.mypro.blogpro.dto.RegistrationDto;
import stu.mypro.blogpro.entity.User;

@Service
public interface UserService {
   void saveUser(RegistrationDto registrationDto);

   User findByEmail(String email);

}
