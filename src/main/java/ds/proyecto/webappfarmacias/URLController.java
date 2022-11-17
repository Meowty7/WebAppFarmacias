package ds.proyecto.webappfarmacias;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

@Controller
public class URLController {
   @RequestMapping("login")
   String login(){
       return "login";
   }

   @RequestMapping("/home")
   String welcome(){
       return "index";
   }

   @RequestMapping("/medicamentos")
   String tablaMedicamentos(){
        return "medicamento";
   }

   @RequestMapping("/farmacias")
    String tablaFarmacias(){
       return "farmacia";
   }

   @RequestMapping("/logout")
    String logout(){
       return "logout";
   }

}
