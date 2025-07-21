package pe.com.order.producer;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class WorkflowClientProducer {

	private final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
	private final WorkflowClient client = WorkflowClient.newInstance(this.service);

	@Produces
	@ApplicationScoped
	public WorkflowClient workflowClient() {
		return this.client;
	}

}
