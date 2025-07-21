package pe.com.order.flow.activities;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class OrderActivitiesImpl implements OrderActivities {
	private static final Random RANDOM = new Random();

	@Override
	public void validateOrder(Integer orderId) {
		// Logic to validate the order
		log.info("Validating order: {} ", orderId);
	}

	@Override
	public void chargeCustomer(Integer orderId) {
		// Logic to charge the customer
		log.info("Charging customer for order:  {} ", orderId);
	}

	@Override
	public void sendConfirmationEmail(Integer orderId) {
		// Logic to send confirmation email
		if (RANDOM.nextBoolean()) {
			log.info("❌ Simulating error for orderId {} ", orderId);
			throw new RuntimeException("Simulated fault");
		}

		log.info("Sending confirmation email for order: {} ", orderId);
	}
}
