package groupe4.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MarketService {

	
	
	

	public void addCard(Card card, Integer userId) {
		User user = this.getUser(userId);
		if (user != null) {
			user.addCard(card.getId());
			user.updateBalance(-card.getPrice());
			// for test
			List<Integer> cs = user.getUserCardList();
			this.saveUser(user);
		}
	}
	
	public void removeCard(Card card, Integer userId) {
		User user = this.getUser(userId);
		if (user != null) {
			user.removeCard(card.getId());
			user.updateBalance(+card.getPrice());
			this.saveUser(user);
		}
	}
}
