package com.finabil.controller;

import com.finabil.model.User;
import com.finabil.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    private final UserService userService;


    // constructor autowiring; UserSevice class provide the singleton bean, springboot make an object of it
    // If we don't construct the object here, it will remain null
    //Here we can use Field Injection instead, This is
    //@Autowired
    //private final UserService userService;
    // This does the same thing but not recommended
    public MainController(UserService userService) {

        this.userService = userService;
    }


    // @RequestMapping ("/Home")
    // This is a handler method for processing HTTP requests, springboot injected dependency "Modelmap" here
//    public String getHomePage(ModelMap modelMap){
//        modelMap.put("name", "finabil");
//        return "HomePage";
//    }

    // ModelAndView way is preferred
    @RequestMapping ("/Home")
    public ModelAndView getHomePage(ModelAndView modelAndView){
       modelAndView.setViewName("HomePage"); // setting the html page with the directory /Home
       modelAndView.addObject("name", "finabil");
       return modelAndView;
    }


//    @PostMapping ("/submit")
//    public ModelAndView submitUserData (ModelAndView modelAndView, RedirectAttributes redirectAttributes, @RequestParam String fullName,
//                                        @RequestParam Integer age, @RequestParam String email){
//        // somehow save the data
////        modelAndView.addObject("fullName", fullName);
////        modelAndView.addObject("age", age);
////        modelAndView.addObject("email", email);
//
//        redirectAttributes.addFlashAttribute("success", "Saved Successfully");
//        modelAndView.setViewName("redirect:/Home");
//
//        return modelAndView;
//
//    }

    // when the submit button will be pressed, this api will be hit
    @PostMapping ("/submit")
    public ModelAndView submitUserData (ModelAndView modelAndView, RedirectAttributes redirectAttributes,
                                        @ModelAttribute User user){
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("success", "Saved Successfully");
        // after every submission the page will remain in homepage, that's why redirecting
        modelAndView.setViewName("redirect:/Home");

        return modelAndView;

    }

    @GetMapping ("/user-details")
    public ModelAndView viewUserData(ModelAndView modelAndView){
        modelAndView.setViewName("UserDetails");

        // somehow load the data
        List<User> userList = userService.getAllUsers();
        //System.out.println(userList);
        if(userList.size() == 0){
            System.out.println("YES");
        }
        modelAndView.addObject("userList", userList);
        return modelAndView;

    }


}
