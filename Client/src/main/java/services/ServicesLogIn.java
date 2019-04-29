package services;

import model.User;
import repository.RepoLogIn;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServicesLogIn {
    private RepoLogIn repoLogIn;
    private Logger logger;

    public ServicesLogIn(Properties serverProps) {
        logger = LogManager.getLogger(RepoLogIn.class.getName());
        repoLogIn = new RepoLogIn(serverProps);
    }

    public User getOne(User user) {
        User userSearched = repoLogIn.getOne(user);
        if (userSearched != null)
            logger.traceEntry("User " + user.getUsername() + " found.");
        else
            logger.traceEntry("User " + user.getUsername() + " not found.");
        return userSearched;
    }
}
