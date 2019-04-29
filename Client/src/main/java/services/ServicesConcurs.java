package services;

import model.Concurent;
import model.UpdatePoints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.RepoConcurs;
import repository.RepoLogIn;

import java.util.ArrayList;
import java.util.Properties;


public class ServicesConcurs {
    private static RepoConcurs repoConcurs;
    private Logger logger;

    public ServicesConcurs(Properties serverProps) {
        logger = LogManager.getLogger(RepoLogIn.class.getName());
        repoConcurs = new RepoConcurs(serverProps);
    }

    public ArrayList<Concurent> getConcurenti() {
        return repoConcurs.getConcurenti();
    }

    public void updatePoints(UpdatePoints updatePoints) {
        logger.traceEntry("Competitor " + updatePoints.getCompName() + " has now " + updatePoints.getPoints());
        repoConcurs.updatePoints(updatePoints);
    }
}
