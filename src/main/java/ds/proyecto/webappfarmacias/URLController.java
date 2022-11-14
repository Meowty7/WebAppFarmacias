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

   @Bean
    public ServletRegistrationBean<HttpServlet> logout(){
       ServletRegistrationBean<HttpServlet> logoutServlet = new ServletRegistrationBean<>();
       logoutServlet.setServlet(new Logout());
       logoutServlet.addUrlMappings("/logout");
       logoutServlet.setLoadOnStartup(1);
       return logoutServlet;
   }

}
