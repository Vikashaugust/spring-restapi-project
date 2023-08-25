package pd.workshop.springrestapiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"pd.workshop.springrestapiproject"})
@SpringBootApplication
public class SpringRestApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiProjectApplication.class, args);
	}

}
