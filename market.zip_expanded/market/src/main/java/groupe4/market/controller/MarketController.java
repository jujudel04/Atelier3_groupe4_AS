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
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8080/User" + "/1", UserDto.class);
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

	@RequestMapping(method = RequestMethod.GET, value = "/card/buy/{id}/{authenticateid}")
	public String buyCard(Model model,@PathVariable Integer id, @PathVariable Integer authenticateid) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8080/User" + "/1", UserDto.class);
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
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8080/User" + "/1", UserDto.class);
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
	public String sellCard(Model model,@PathVariable Integer id, @PathVariable Integer authenticateid) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8080/User" + "/1", UserDto.class);
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

	public static class UserDto {
		private Integer userID;
		private String card;
		private Integer accountBalance;

		public UserDto() {
		}

		public Integer getUserID() {
			return userID;
		}

		public void setUserID(Integer userID) {
			this.userID = userID;
		}

		public String getCards() {
			return card;
		}

		public void setCards(String cards) {
			this.card = cards;
		}

		public Integer getAccountBalance() {
			return accountBalance;
		}

		public void setAccountBalance(Integer accountBalance) {
			this.accountBalance = accountBalance;
		}
	}

	public static class CardDto {
		private Integer id;
		private Integer price;

		public CardDto() {

		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

	}

}
