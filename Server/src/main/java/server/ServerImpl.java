package server;

import model.Concurent;
import model.GetConcurenti;
import model.UpdatePoints;
import model.User;
import repository.RepoConcurs;
import repository.RepoLogIn;
import services.IObserver;
import services.IServer;
import services.MyException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerImpl implements IServer {

    private RepoLogIn repoLogIn;
    private RepoConcurs repoConcurs;
    private Map<String, IObserver> loggedClients;

    public ServerImpl(RepoLogIn repoLogIn, RepoConcurs repoConcurs) {
        this.repoLogIn = repoLogIn;
        this.repoConcurs = repoConcurs;
        loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized User login(User user, IObserver client) throws MyException {
        User userR = repoLogIn.getOne(user);
        if (userR != null) {
            if (loggedClients.get(user.getUsername()) != null)
                throw new MyException("User already logged in.");
            loggedClients.put(user.getUsername(), client);
            return userR;
        } else
            throw new MyException("Authentication failed.");
    }

    @Override
    public synchronized void updatePoints(UpdatePoints updatePoints) {
        repoConcurs.updatePoints(updatePoints);
        notifyUpdated();
    }

    private void notifyUpdated() {
        System.out.println("Notifying logged in clients...");
        int defaultThreadsNo = 5;
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        for (Map.Entry<String, IObserver> entry : loggedClients.entrySet()) {
            IObserver client = entry.getValue();
            System.out.println("Notifying " + entry.getKey() + "...");
            executor.execute(() -> {
                try {
                    client.updatedPoints();
                } catch (MyException | RemoteException e) {
                    System.out.println("Error notifying friend " + e);
                }
            });
        }
    }

    @Override
    public synchronized GetConcurenti getConcurenti() {
        ArrayList<Concurent> concurentiRepo = repoConcurs.getConcurenti();
        return new GetConcurenti(concurentiRepo);
    }

    @Override
    public synchronized void logout(User user, IObserver client) throws MyException {
        IObserver localClient = loggedClients.remove(user.getUsername());
        if (localClient == null)
            throw new MyException("User " + user.getUsername() + " is not logged in.");
    }
}
