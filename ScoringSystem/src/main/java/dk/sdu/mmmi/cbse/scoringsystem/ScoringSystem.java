package dk.sdu.mmmi.cbse.scoringsystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystem {
	private int score = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoringSystem.class, args);
	}

	@GetMapping("/score")
	public String totalScore(@RequestParam(value = "point", defaultValue = "0") int point) {
		score += point;
		return String.format("Your total score is %s!", score);
	}
}
