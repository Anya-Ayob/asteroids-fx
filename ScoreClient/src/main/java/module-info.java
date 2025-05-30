import dk.sdu.mmmi.cbse.scoreclient.ScoreClient;
import dk.sdu.mmmi.cbse.common.services.IScoringService;


module ScoreClient {
    requires Common;
    requires spring.web;
    requires spring.core;
    provides IScoringService with ScoreClient;
}