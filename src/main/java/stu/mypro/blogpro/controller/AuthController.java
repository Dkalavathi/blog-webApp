package stu.mypro.blogpro.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import stu.mypro.blogpro.dto.RegistrationDto;
import stu.mypro.blogpro.entity.User;
import stu.mypro.blogpro.service.AuthService;
import stu.mypro.blogpro.service.UserService;

@Controller
public class AuthController {
    private UserService userService;
    private AuthService authService;

    public AuthController(UserService userService,AuthService authService) {
        this.authService = authService;  this.userService = userService;
    }

    //handler to handle the login page request
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

  //handler to handle user registration request
  @GetMapping("/register")
  public String showRegistrationForm(Model model){
    //this objet holds form data
     RegistrationDto user= new RegistrationDto();
     model.addAttribute("user",user);
     return "register";
  }

  //handler method to handle save user register using submit
   @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,
                           Model model){
        User existingUser= userService.findByEmail(user.getEmail());
        if(existingUser != null && existingUser.getEmail()!=null
        && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email",null,"There is already a user with same email-id ");
       }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
      userService.saveUser(user);
      return "redirect:/register?success";
  }
}