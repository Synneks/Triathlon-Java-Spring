package model;

import java.io.Serializable;
import java.util.ArrayList;

public class GetConcurenti implements Serializable {
    ArrayList<Concurent> concurenti;

    public GetConcurenti(ArrayList<Concurent> concurenti) {
        this.concurenti = concurenti;
    }

    public ArrayList<Concurent> getConcurenti() {
        return concurenti;
    }

    @Override
    public String toString() {
        return "GetConcurenti{" +
                "concurenti=" + concurenti +
                '}';
    }
}
