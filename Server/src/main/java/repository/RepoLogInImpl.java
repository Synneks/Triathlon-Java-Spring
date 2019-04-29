package repository;

import model.UpdatePoints;
import model.User;

import java.util.ArrayList;

public abstract class RepoLogInImpl implements Repo {
    @Override
    public User getOne(User user) throws Exception {
        throw new Exception("Method not implemented");
    }

    @Override
    public ArrayList<?> getConcurenti() throws Exception {
        throw new Exception("Method not implemented");
    }

    @Override
    public void updatePoints(UpdatePoints updatePoints) throws Exception {
        throw new Exception("Method not implemented");
    }
}
