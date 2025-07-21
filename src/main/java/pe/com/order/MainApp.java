package pe.com.order;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@QuarkusMain
public class MainApp implements QuarkusApplication {

	public static void main(String... args) {
		Quarkus.run(MainApp.class, args);
	}

	@Override
	public int run(String... args) throws Exception {
		// Código que quieres ejecutar al iniciar
		log.info("Quarkus started with class main");

		// Mantener la aplicación corriendo
		Quarkus.waitForExit();
		return 0;
	}
}
