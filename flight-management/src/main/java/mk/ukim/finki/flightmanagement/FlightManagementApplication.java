package mk.ukim.finki.flightmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
@ServletComponentScan
public class FlightManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightManagementApplication.class, args);
    }

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

}
