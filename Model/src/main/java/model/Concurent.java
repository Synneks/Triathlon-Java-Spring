package model;

import java.io.Serializable;

public class Concurent implements Serializable {
    private String nume;
    private Integer alergat,inot,ciclism,total;

    public Concurent(String nume, Integer alergat, Integer inot, Integer ciclism, Integer total) {
        this.nume = nume;
        this.alergat = alergat;
        this.inot = inot;
        this.ciclism = ciclism;
        this.total = total;
    }

    public Concurent(String nume, Integer total) {
        this.nume = nume;
        this.total = total;
    }

    public Integer getAlergat() {
        return alergat;
    }

    public void setAlergat(Integer alergat) {
        this.alergat = alergat;
    }

    public Integer getInot() {
        return inot;
    }

    public void setInot(Integer inot) {
        this.inot = inot;
    }

    public Integer getCiclism() {
        return ciclism;
    }

    public void setCiclism(Integer ciclism) {
        this.ciclism = ciclism;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Concurent{" +
                "nume='" + nume + '\'' +
                ", total=" + total +
                '}';
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
