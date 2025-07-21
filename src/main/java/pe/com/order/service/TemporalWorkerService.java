package pe.com.order.service;

import io.quarkus.runtime.Startup;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pe.com.order.flow.OrderWorkflowImpl;
import pe.com.order.flow.activities.OrderActivitiesImpl;

@Slf4j
@Singleton
@Startup
public class TemporalWorkerService {
	private WorkflowServiceStubs service;
	private WorkerFactory factory;

	@PostConstruct
	void start() {
		log.info("🟢 Starting Temporal Worker...");

		this.service = WorkflowServiceStubs.newLocalServiceStubs();
		final WorkflowClient client = WorkflowClient.newInstance(this.service);

		this.factory = WorkerFactory.newInstance(client);
		final Worker worker = this.factory.newWorker("OrderTaskQueue");

		worker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);
		worker.registerActivitiesImplementations(new OrderActivitiesImpl());

		this.factory.start();

		log.info("Temporary Worker started and listening on OrderTaskQueue");
	}

	@PreDestroy
	void shutdown() {
		if (this.factory != null) {
			this.factory.shutdown();
		}
		if (this.service != null) {
			this.service.shutdown();
		}
		log.info("Temporal Worker stopped");
	}
}
