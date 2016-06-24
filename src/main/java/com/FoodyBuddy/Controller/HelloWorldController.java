package com.FoodyBuddy.Controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.FoodyBuddy.Dao.CountryDAO;
import com.FoodyBuddy.Model.Country;


@Controller  
public class HelloWorldController {  
    @RequestMapping("/hello")  
    public String helloWorld(ModelMap model) {  
        String message = "HELLO SPRING ";  
        System.out.println(message);
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.cfg.xml");
		
		CountryDAO CountryDAO = context.getBean(CountryDAO.class);
		
		Country Country = new Country();
		Country.setName("Pakisthan");
		
		CountryDAO.save(Country);
        model.addAttribute("mesg", message);
        return "hello";
    }
}  