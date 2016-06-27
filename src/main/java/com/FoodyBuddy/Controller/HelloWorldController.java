package com.foodybuddy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foodybuddy.utils.SessionFactoryUtils;





@Controller  
public class HelloWorldController {  
    @RequestMapping("/hello")  
    public String helloWorld(ModelMap model) {  
        String message = "HELLO SPRING ";  
        System.out.println(message);
        System.out.println("Reached ");
		Session session = SessionFactoryUtils.getSessionFactory().openSession();
		System.out.println("Did session connect ? " + session.isConnected());
		session.close();

        
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.cfg.xml");
//		
//		OrDAO CountryDAO = context.getBean(CountryDAO.class);
//		
//		Country Country = new Country();
//		Country.setName("Pakisthan");
//		
//		CountryDAO.save(Country);
     //   model.addAttribute("mesg", message);
        return "hello";
    }
}  

	