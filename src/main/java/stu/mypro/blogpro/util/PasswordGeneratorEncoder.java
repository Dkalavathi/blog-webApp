package stu.mypro.blogpro.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {

public static void main(String[] args){
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //System.out.println(passwordEncoder.encode("daniel"));
    //System.out.println(passwordEncoder.encode("richard"));
    System.out.println(passwordEncoder.encode("admin"));
    System.out.println(passwordEncoder.encode("kavi"));
  //  $2a$10$RmXvugeeNsZUADpRx1TvjeM60KEGUH3MMtbYXA2H7TuRewAy.aQqm ----admin
   //   $2a$10$KjFwfJPNZKZwshlK2GzUROEGF9xEnmzO5Qx6CofZfIlzDZZde3qVa---kavi

    //$2a$10$Wq0mlaRNjqm1k1cdW5NuO.TYD2.98sRPSb4daeOQedKY5nV2.fW16--daniel
    //$2a$10$Z16UarBaqHzNg8uy.hIpkORfkmteU8eJRuT.oQY6kJX/qvmBW1awC-richard
    //System.out.println(passwordEncoder.encode("administrator"));
  }
}



