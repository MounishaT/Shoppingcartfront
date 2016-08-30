package com.niit.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.UserDetailsDAO;
import com.niit.shoppingcart.model.UserDetails;

@Controller
public class UserController {

	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@Autowired
	UserDetails userDetails;
	@RequestMapping("/login")
	public ModelAndView login (@RequestParam(value="id") String id, @RequestParam(value="password") String password)
	{
	
		ModelAndView mv = new ModelAndView("home");
		String msg;
		userDetails=userDetailsDAO.isValidUser(id, password);
		if(userDetails==null)
		{
			msg="Invalid user....please try again";
			
		}
		else{
			if(userDetails.getRole().equals("ROLE_ADMIN"))
			{
				mv= new ModelAndView("adminhome");
			}
			session.setAttribute("welcomemsg",userDetails.getName());
			session.setAttribute("userID",userDetails.getID());
		}
		return mv;
		
	}

}
