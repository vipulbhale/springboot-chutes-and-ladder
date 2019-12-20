package com.candidate.priceline.chutesandladder.model;

import java.io.Serializable;

public class ChuteAndLadder implements Serializable {
    private static final long serialVersionUID = 7555727367546742185L;

    private int id;
    private int startSquare;
    private int endSquare;
    private String type;

    public ChuteAndLadder() {
    }

    public ChuteAndLadder(int id, int startSquare, int endSquare, String type) {
        this.id = id;
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartSquare() {
        return startSquare;
    }

    public void setStartSquare(int startSquare) {
        this.startSquare = startSquare;
    }

    public int getEndSquare() {
        return endSquare;
    }

    public void setEndSquare(int endSquare) {
        this.endSquare = endSquare;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChuteAndLadder{" +
                "id=" + id +
                ", startSquare=" + startSquare +
                ", endSquare=" + endSquare +
                ", type='" + type + '\'' +
                '}';
    }
}
