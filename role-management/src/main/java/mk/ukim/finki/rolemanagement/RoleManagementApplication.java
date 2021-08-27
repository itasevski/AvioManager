package mk.ukim.finki.rolemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RoleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleManagementApplication.class, args);
    }

}
