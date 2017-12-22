package tma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tma.model.SmartPhone;
import tma.service.SmartPhoneService;

@Controller
public class AppControllerSmartPhone {
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mysite. ");
        return "welcome";
    }
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin";
    }
	@RequestMapping(value = "/dba", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "dba";
    }
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "user";
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
       return "redirect:/login?logout";
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

	//---------------------------end security---------------------//
	

	@Autowired
	SmartPhoneService smartPhoneService;
	
	@RequestMapping(value = { "/listAll" }, method = RequestMethod.GET)
	public @ResponseBody List<SmartPhone> listAllSmartPhone(ModelMap model) {
		return smartPhoneService.getAllSmartPhone();
	}
	
	@RequestMapping(value = { "/list/{idSmartPhone}" }, method = RequestMethod.GET)
    @ResponseBody
	public SmartPhone listSmartPhoneById(@PathVariable String idSmartPhone) {
		 return smartPhoneService.getSmartPhoneById(idSmartPhone);
	}
	
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	@ResponseBody
	public String addSmartPhone(@RequestBody SmartPhone jsonString) {
		smartPhoneService.sendSmartPhone(jsonString);
 		return "add done!";
	}
	
	@RequestMapping(value = { "/update/{idSmartPhone}" }, method = RequestMethod.PUT)
	@ResponseBody
	public String updateOrderById(@RequestBody SmartPhone jsonString, @PathVariable String idSmartPhone) {	
		
		SmartPhone currentSmartPhone = smartPhoneService.getSmartPhoneById(idSmartPhone);
		currentSmartPhone.setnameSmartPhone(jsonString.getnameSmartPhone());
		currentSmartPhone.setpriceSmartPhone(jsonString.getpriceSmartPhone());
		
		smartPhoneService.updateSmartPhone(currentSmartPhone);
 		return "update done!";
	}
	
	@RequestMapping(value = { "/delete/{idSmartPhone}" }, method = RequestMethod.DELETE)
    @ResponseBody
	public String deleteSmartPhoneById(@PathVariable String idSmartPhone) {
		
		smartPhoneService.deleteById(idSmartPhone);
		return "deleteid";
	}
	
	@RequestMapping(value = { "/deleteAll" }, method = RequestMethod.DELETE)
    @ResponseBody
	public String deleteAllSmartPhone() {
		
		smartPhoneService.deleteAllSmartPhones();
		return "deleteAll";
	}
}
