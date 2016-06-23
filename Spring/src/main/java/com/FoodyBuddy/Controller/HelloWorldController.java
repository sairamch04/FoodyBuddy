package com.FoodyBuddy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller
//@RequestMapping("/hello")
//public class HelloController{
// 
//   @RequestMapping(method = RequestMethod.GET)
//   public String printHello(ModelMap model) {
//      model.addAttribute("message", "Hello Spring MVC Framework!");
//
//      return "hello";
//   }
//
//}
@Controller  
public class HelloWorldController {  
    @RequestMapping("/hello")  
    public String helloWorld(ModelMap model) {  
        String message = "HELLO SPRING MVC HOW R U <3";  
        System.out.println(message);
        model.addAttribute("mesg", message);
        return "hello";
    }
}  