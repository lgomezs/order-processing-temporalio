package pe.com.order.controller;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.openapi.quarkus.openapi_yml.api.OrderApi;
import pe.com.order.flow.OrderWorkflow;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource implements OrderApi {

	@Inject
	WorkflowClient workflowClient;

	@Override
	public Response findProcessOrder(Integer orderId) {
		return null;
	}

	@Override
	public Response processOrder(Integer orderId) {
		final OrderWorkflow workflow = this.workflowClient.newWorkflowStub(OrderWorkflow.class,
				WorkflowOptions.newBuilder().setTaskQueue("OrderTaskQueue").build());

		workflow.processOrder(orderId);

		return Response.ok("Order " + orderId + " sent for processing").build();
	}
}
