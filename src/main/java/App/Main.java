package App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ui.UI;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(UI.class, args);
    }
}
