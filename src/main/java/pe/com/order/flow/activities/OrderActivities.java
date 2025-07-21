package pe.com.order.flow.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrderActivities {

	@ActivityMethod
	void validateOrder(Integer orderId);

	@ActivityMethod
	void chargeCustomer(Integer orderId);

	@ActivityMethod
	void sendConfirmationEmail(Integer orderId);
}
