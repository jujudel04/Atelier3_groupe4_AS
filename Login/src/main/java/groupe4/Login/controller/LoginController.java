package groupe4.Login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import groupe4.Login.Service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/login/signup")
	public String signup(Model model, @RequestParam("login") String login, @RequestParam("passwd") String passwd) {
		boolean authenticate = loginService.signup(login, passwd);
		if (authenticate) {
			return "redirect:/user/show/" + authenticateid;
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}
}