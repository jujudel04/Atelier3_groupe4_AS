package groupe4.pageweb.controller;

import org.springframework.stereotype.Controller;

@Controller
public class PageWebController {

	
	public void affichePages(String nom,Integer authenticateid){
		return "redirect:"+nom + authenticateid;
		
	}
}
