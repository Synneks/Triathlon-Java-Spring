package services;

import model.GetConcurenti;
import model.UpdatePoints;
import model.User;


public interface IServer {
    User login(User user, IObserver client) throws MyException;

    void updatePoints(UpdatePoints updatePoints) throws MyException;

     GetConcurenti getConcurenti() throws MyException;

    void logout(User user, IObserver client) throws MyException;
}
