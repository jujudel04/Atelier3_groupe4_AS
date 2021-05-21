package groupe4.market.service;

import java.util.List;


import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;



@Service
public class MarketService {

	public void addCard(Integer userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> user = restTemplate.getForEntity("http://localhost:8080/User", UserDto.class);
		ResponseEntity<CardDto> card = restTemplate.getForEntity("http://localhost:8081/Card", CardDto.class);
		user = user.getUser(userId);
		if (user != null) {
			user.addCard(card.getId());
			user.updateBalance(-card.getPrice());
			// for test
			List<Integer> cs = user.getUserCardList();
			this.saveUser(user);
		}
	}

	public void removeCard(Integer userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> user = restTemplate.getForEntity("http://localhost:8080/User", UserDto.class);
		ResponseEntity<CardDto> card = restTemplate.getForEntity("http://localhost:8081/Card", CardDto.class);
		user = user.getUser(userId);
		if (user != null) {
			user.removeCard(card.getId());
			user.updateBalance(+card.getPrice());
			this.saveUser(user);
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

