package org.example;

import java.io.Serializable;

public class Dummy implements Serializable {

    private String dummyName;
    private int dummyVersion;

    public Dummy() {
    }

    public Dummy(String dummyName, int dummyVersion) {
        this.dummyName = dummyName;
        this.dummyVersion = dummyVersion;
    }

    public String getDummyName() {
        return dummyName;
    }

    public void setDummyName(String dummyName) {
        this.dummyName = dummyName;
    }

    public int getDummyVersion() {
        return dummyVersion;
    }

    public void setDummyVersion(int dummyVersion) {
        this.dummyVersion = dummyVersion;
    }
}
