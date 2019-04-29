package ui;

import controllerObserver.Observable;
import controllerObserver.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Concurent;
import model.GetConcurenti;
import model.UpdatePoints;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.RepoLogIn;
import services.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Controller extends UnicastRemoteObject implements IObserver, Observable {
    private Logger logger;
    private IServer server;
    private User user;
    public Observer obs;

    public Controller(IServer server) throws RemoteException {
        logger = LogManager.getLogger(RepoLogIn.class.getName());
        this.server = server;
    }

    void logIn(String username, String password, Controller ctrl) {
        User userL = new User(username, password);
        try {
            userL = server.login(userL, this);
            logger.traceEntry("Ref " + username + " has logged in");
            this.user = userL;
            Concurs concurs = new Concurs(user.getNume() + " " + user.getPrenume(), ctrl);
            obs = concurs;
        } catch (MyException e) {
            System.out.println("Log In Error " + e);
        }
    }

    public void logOut() {
        try {
            server.logout(user, this);
        } catch (MyException e) {
            System.out.println("Log Out Error " + e);
        }
    }

    ObservableList<Concurent> getConcurenti() throws MyException {
        GetConcurenti concurenti = server.getConcurenti();
        ObservableList<Concurent> concurents = FXCollections.observableArrayList(concurenti.getConcurenti());
        return concurents;
    }

    void updatePoints(String nume, int points, String refName) throws MyException {
        UpdatePoints updatePoints = new UpdatePoints(nume, points, getChallenge(refName));
        server.updatePoints(updatePoints);
    }

    @Override
    public void updatedPoints() {
        System.out.println("Points updated");
        notifyCtrl();
    }

    @Override
    public void notifyCtrl() {
        obs.update();
    }

    /**
     * Each referee is responsible for one challenge
     *
     * @param refName = name of referee
     * @return challenge name
     */
    private String getChallenge(String refName) {
        switch (refName) {
            case "Popescu Ion":
                return "alergat";
            case "Blandiana Ana":
                return "inot";
            case "Popescu Dan":
                return "ciclism";
        }
        return "";
    }
}
