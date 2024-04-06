package com.example.sdn.old;

public class Creture {
    private String etA;
    private String etB;
    private String etC;
    private String etD;

    public Creture() {
    }

    public Creture(String O, String S , String T, String F) {
        this.etA = O;
        this.etB = S;
        this.etC = T;
        this.etD = F;
    }

    public String getEtA() {
        return etA;
    }

    public void setEtA(String etA) {
        this.etA = etA;
    }

    public String getEtB() {
        return etB;
    }

    public void setEtB(String etB) {
        this.etB = etB;
    }

    public String getEtC() {
        return etC;
    }

    public void setEtC(String etC) {
        this.etC = etC;
    }

    public String getEtD() {
        return etD;
    }

    public void setEtD(String etD) {
        this.etD = etD;
    }

    @Override
    public String toString() {
        return "Creture{" +
                "etA='" + etA + '\'' +
                ", etB='" + etB + '\'' +
                ", etC='" + etC + '\'' +
                ", etD='" + etD + '\'' +
                '}';
    }
}
