package dk.sdu.mmmi.cbse.scoreclient;

import dk.sdu.mmmi.cbse.common.services.IScoringService;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ScoreClient implements IScoringService {
    private RestTemplate restTemplate = new RestTemplate();
    String pointsURL = "http://localhost:8080/score";

    @Override
    public int getScore() {
        try {
            String response = restTemplate.getForObject(pointsURL, String.class);
            return Integer.parseInt(response);
        } catch (RestClientException e) {
            return 0;
        }
    }

    @Override
    public void sendScore(int point) {
        try {
            String url = pointsURL + "/" + String.valueOf(point);
            restTemplate.put(url, null);
        } catch (RestClientException e) {
            System.err.println("Error sending the score: " + e.getMessage());
        }
    }
}