package com.candidate.priceline.chutesandladder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = -722844561015016428L;
    private long id;
    private String name;
    private int turnOrder;
    private List<Integer> squarePositions = new ArrayList<>();
    private int currentPosition;

    public Player(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    public List<Integer> getSquarePositions() {
        return squarePositions;
    }

    public void setSquarePositions(List<Integer> squarePositions) {
        this.squarePositions = squarePositions;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", turnOrder=" + turnOrder +
                ", squarePositions=" + squarePositions +
                ", currentPosition=" + currentPosition +
                '}';
    }
}
