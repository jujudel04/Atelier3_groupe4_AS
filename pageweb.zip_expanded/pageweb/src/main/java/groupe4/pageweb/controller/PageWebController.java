package groupe4.pageweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PageWebController {

	@RequestMapping(method = RequestMethod.GET, value = "/card/showall/{authenticateid}")
	public String showAllCards(Model model, @PathVariable Integer authenticateid) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8080/User" + "/1",
				UserDto.class);
		if (response.isAuthenticate(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			model.addAttribute("name", response.getName());
			model.addAttribute("balance", response.getAccountBalance());
			return "cardlist";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	public void addCard(Integer userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> user = restTemplate.getForEntity("http://localhost:8080/User", UserDto.class);
		ResponseEntity<CardDto> card = restTemplate.getForEntity("http://localhost:8081/Card", CardDto.class);
		if (user != null) {
			user.addCard(card.getId());
			user.updateBalance(-card.getPrice());
			// for test
			List<Integer> cs = user.getUserCardList();
			this.saveUser(user);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/buy/{id}/{authenticateid}")
	public String buyCard(Model model, @PathVariable Integer id, @PathVariable Integer authenticateid) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8080/User" + "/1",
				UserDto.class);
		if (response.isAuthenticate(authenticateid)) {
			response.addAttribute("authenticateid", authenticateid);
			ResponseEntity<CardDto> card = restTemplate.getForEntity("http://localhost:8080Card" + "/1", CardDto.class);
			response.addCard(card, authenticateid);
			return "redirect:/user/show/" + authenticateid;
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/sellshowall/{authenticateid}")
	public String sellshowAllCards(Model model, @PathVariable Integer authenticateid) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8080/User" + "/1",
				UserDto.class);
		if (response.isAuthenticate(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			model.addAttribute("name", response.getName());
			model.addAttribute("balance", response.getAccountBalance());
			return "sellcardlist";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/sell/{id}/{authenticateid}")
	public String sellCard(Model model, @PathVariable Integer id, @PathVariable Integer authenticateid) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8080/User" + "/1",UserDto.class);
		if (response.isAuthenticate(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			response.addAttribute("authenticateid", authenticateid);
			ResponseEntity<CardDto> card = restTemplate.getForEntity("http://localhost:8080Card" + "/1", CardDto.class);
			UserDto.removeCard(card, authenticateid);
			return "redirect:/user/show/" + authenticateid;
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

}
