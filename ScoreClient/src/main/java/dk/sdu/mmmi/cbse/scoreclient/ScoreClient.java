package dk.sdu.mmmi.cbse.scoreclient;

import dk.sdu.mmmi.cbse.common.services.IScoringService;
import org.springframework.web.client.RestTemplate;

public class ScoreClient implements IScoringService {
    private RestTemplate restTemplate = new RestTemplate();
    String pointsURL = "http://localhost:8080/score";

    @Override
    public int getScore() {
        String response = restTemplate.getForObject(pointsURL, String.class);
        return Integer.parseInt(response);
    }

    @Override
    public void sendScore(int point) {
        String url = pointsURL + "/" + String.valueOf(point);
        restTemplate.put(url, null);
    }
}