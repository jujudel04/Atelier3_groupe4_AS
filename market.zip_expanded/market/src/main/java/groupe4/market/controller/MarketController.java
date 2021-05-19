package groupe4.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import groupe4.market.service.MarketService;
@Controller
public class MarketController {

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/card/showall/{authenticateid}")
	public String showAllCards(Model model, @PathVariable Integer authenticateid) {
		if (UserService.isAuthenticate(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			User user = userService.getUser(authenticateid);
			model.addAttribute("name", user.getName());
			model.addAttribute("balance", user.getAccountBalance());
			return "cardlist";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/buy/{id}/{authenticateid}")
	public String buyCard(Model model, @PathVariable Integer id, @PathVariable Integer authenticateid) {
		if (UserService.isAuthenticate(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			Card card = cardService.getCard(id);
			userService.addCard(card, authenticateid);
			return "redirect:/user/show/" + authenticateid;
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/sellshowall/{authenticateid}")
	public String sellshowAllCards(Model model, @PathVariable Integer authenticateid) {
		if (UserService.isAuthenticate(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			User user = userService.getUser(authenticateid);
			model.addAttribute("name", user.getName());
			model.addAttribute("balance", user.getAccountBalance());
			return "sellcardlist";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/sell/{id}/{authenticateid}")
	public String sellCard(Model model, @PathVariable Integer id, @PathVariable Integer authenticateid) {
		if (UserService.isAuthenticate(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			Card card = cardService.getCard(id);
			userService.removeCard(card, authenticateid);
			return "redirect:/user/show/" + authenticateid;
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/play/{authenticateid}")
	public String listUserCards(Model model, @PathVariable Integer authenticateid) {
		if (UserService.isAuthenticate(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			User user = userService.getUser(authenticateid);
			model.addAttribute("name", user.getName());
			model.addAttribute("balance", user.getAccountBalance());

			return "room";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}
}
