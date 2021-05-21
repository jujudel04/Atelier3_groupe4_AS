package groupe4.User.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import groupe4.User.Service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/signup")
	public boolean signup(Model model, @RequestParam("login") String login, @RequestParam("passwd") String passwd) {
		Integer authenticateid = userService.signup(login, passwd);
		return (authenticateid != null);
	}
}