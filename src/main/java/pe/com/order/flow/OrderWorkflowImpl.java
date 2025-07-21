package pe.com.order.flow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import pe.com.order.flow.activities.OrderActivities;

import java.time.Duration;

public class OrderWorkflowImpl implements OrderWorkflow {

	// Espere 1 segundo antes del primer reintento
	// Doble el tiempo de espera en cada intento (1s → 2s → 4s...).
	// Solo lo intente 3 veces en total.
	private final OrderActivities activities = Workflow.newActivityStub(OrderActivities.class, ActivityOptions
			.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(10)).setRetryOptions(RetryOptions.newBuilder()
					.setInitialInterval(Duration.ofSeconds(1)).setMaximumAttempts(3).setBackoffCoefficient(2.0).build())
			.build());

	@Override
	public void processOrder(Integer orderId) {
		this.activities.validateOrder(orderId);
		this.activities.chargeCustomer(orderId);
		this.activities.sendConfirmationEmail(orderId);
	}
}
