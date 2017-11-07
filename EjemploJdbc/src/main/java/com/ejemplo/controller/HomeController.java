package com.ejemplo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejemplo.model.Employee;
import com.ejemplo.model.User;
import com.ejemplo.sevice.EmployeeService;
import com.ejemplo.sevice.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired	
	EmployeeService  employeeserv;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);		
		model.addAttribute("serverTime", formattedDate );
		
		
		List<Employee>   listado = employeeserv.findByNameEmployee("sebastian");			
		logger.info("el numero "+ listado.size());
		
		//el acceso para la seguridad se efectua a travez de DATA JPA  haciendo uso de la interfaz JpaRepository		
		//pero el acceso para obtener los datos lo hare a traves de un acceso con hibernate.  pero toda la configuracion lo hare con java configuration
		//para esto la confuguracion de la aplicacion tenemos que agregar el 	
		
		return "home";
	}
	
	
	
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }
	

 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
 
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        //return "redirect:/login?logout";
        return "redirect:/";
    }
	
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
	//Acceso a los de role admin User Dba
	
	@RequestMapping(value="/gestion/{pagGestion}", method=RequestMethod.GET)
	public String administacion(@PathVariable("pagGestion") String pagGestion, ModelMap model){		
		
		List<User> listadoUsuarios = userService.findAllUsuarios();	
		
		model.addAttribute("user", getPrincipal());		
		model.addAttribute("users", listadoUsuarios);
		
	
			return "gestion";
		
	
		
	}
	
	
	@RequestMapping(value="/{UsuarioId}", method=RequestMethod.GET)
	public String accesoUsuario(@PathVariable("UsuarioId") Integer UsuarioId, ModelMap model){
		
		User user = userService.findUsuarioById(UsuarioId);		
		model.addAttribute("usuario", user);		
		
		
		return "detalleUsuario";
	}
	
	
	
	//Acceso a los de rol dba
	
	

	/*private Object getPrincipal() {
		// TODO Auto-generated method stub
		String usuario = null;
		
	       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	       
	       if( principal instanceof UserDetails){
	    	   
	    	   usuario =  ((UserDetails) principal).getUsuario();
	    	   
	       }else{
	    	   usuario = principal.toString();
	       }
	
		
	        return usuario;
	}*/
	
}
