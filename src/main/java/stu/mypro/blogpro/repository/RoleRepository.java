package stu.mypro.blogpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.mypro.blogpro.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
       Role findByName(String name);

}
