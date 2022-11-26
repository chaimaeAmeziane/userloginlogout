package com.example.userloginlogout.controller;
import com.example.userloginlogout.models.CustomerUserDetails;
import com.example.userloginlogout.models.User;
import com.example.userloginlogout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userrepo;
    @GetMapping("")
    public String viewHomePage()
    {
        return "index";
    }
    @GetMapping("/register")
    public String showSignupForm(Model model)
    {
        model.addAttribute("user",new User()); // In the form parameters in the registration html page, we'll find the object
        // field which refers to the user passed in the attributes of the model user.
        return "register";
    }
    @PostMapping("/process_register")
    public String process_register(User user) // We added user in parameters to map it with the user in the add Attribute method
    {
        BCryptPasswordEncoder bcoder = new BCryptPasswordEncoder();
        String pass = user.getPassword();
        String newpass = bcoder.encode(pass);
        user.setPassword(newpass);
            userrepo.save(user);
            return "register_success";
    }
    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }
    /*
    @GetMapping("list_users")
    public String list_of_users(Model model)
    {
        List<User> list_users = userrepo.findAll();
        model.addAttribute("listusers",list_users);
        return "list_users";
    }
    */

    @GetMapping("userspace")
    public String userspace(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Integer name =((CustomerUserDetails)principal).getId();
        model.addAttribute("current_user",((CustomerUserDetails)principal));
        return "userspace";
    }
}
