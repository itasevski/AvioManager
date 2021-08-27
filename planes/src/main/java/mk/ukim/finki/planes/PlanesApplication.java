package mk.ukim.finki.planes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PlanesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanesApplication.class, args);
    }

}
