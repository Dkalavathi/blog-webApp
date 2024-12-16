package stu.mypro.blogpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.mypro.blogpro.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

   User findByEmail(String email);

   Optional<User> findByUsernameOrEmail(String username, String email);

   Optional<User> findByUsername(String username);

   Boolean existsByUsername(String username);

   Boolean existsByEmail(String email);

}
