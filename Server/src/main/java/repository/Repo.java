package repository;

import model.UpdatePoints;
import model.User;

import java.util.ArrayList;

public interface Repo {
    User getOne(User user) throws Exception;

    ArrayList<?> getConcurenti() throws Exception;

    void updatePoints(UpdatePoints updatePoints) throws Exception;
}
