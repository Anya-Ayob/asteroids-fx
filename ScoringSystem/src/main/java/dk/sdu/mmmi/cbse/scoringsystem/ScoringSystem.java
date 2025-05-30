package dk.sdu.mmmi.cbse.scoringsystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ScoringSystem {
	private int totalScore = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoringSystem.class, args);
	}

	@GetMapping("/score")
	public int getScore() {
		return this.totalScore;
	}

	@PutMapping("/score/{point}")
	public void addScore(@PathVariable(value = "point") int point) {
		this.totalScore += point;
	}

	@GetMapping("/score/{point}")
	public void addScoreThroughBrowser(@PathVariable(value = "point") int point) {
		this.totalScore += point;
	}
}
