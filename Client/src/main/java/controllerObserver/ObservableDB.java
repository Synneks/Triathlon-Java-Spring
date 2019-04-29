package controllerObserver;

public class ObservableDB implements Observable {
    Observer observer;

    @Override
    public void notifyCtrl() {
        observer.update();
    }
}
