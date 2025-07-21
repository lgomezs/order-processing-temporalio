package pe.com.order.flow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderWorkflow {

	@WorkflowMethod
	void processOrder(Integer orderId);
}
