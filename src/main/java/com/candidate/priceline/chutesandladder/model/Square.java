package com.candidate.priceline.chutesandladder.model;


import java.io.Serializable;

public class Square implements Serializable {
    private static final long serialVersionUID = -7179468055894987135L;
    private final int address;
    private boolean chuteOrLadder;

    private MoverType moverType;

    public Square(int address) {
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public boolean hasChuteOrLadder() {
        return chuteOrLadder;
    }

    public void setChuteOrLadder(boolean chuteOrLadder) {
        this.chuteOrLadder = chuteOrLadder;
    }

    public MoverType getMoverType() {
        return moverType;
    }

    public void setMoverType(MoverType moverType) {
        this.moverType = moverType;
    }

    @Override
    public String toString() {
        return "Square{" +
                "address=" + address +
                ", chuteOrLadder=" + chuteOrLadder +
                ", moverType=" + moverType +
                '}';
    }
}
