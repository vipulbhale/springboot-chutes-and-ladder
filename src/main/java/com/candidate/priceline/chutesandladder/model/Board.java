package com.candidate.priceline.chutesandladder.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;


public class Board implements Serializable {

    private static final Logger logger = LogManager.getLogger(Board.class.getName());
    private static final long serialVersionUID = 5679731584972339161L;

    private final int boardSize;
    private final Square[] squares;
    private final Map<Integer, Integer> chutesAndLadderMap;

    public Board(int boardSize, Map<Integer, Integer> chutesAndLadderMap) {
        this.boardSize = boardSize;
        this.squares = new Square[boardSize+1];
        this.chutesAndLadderMap = chutesAndLadderMap;
        logger.trace("Initialize and set address on every square");
        IntStream.range(0, boardSize+1).forEach(i -> {
            this.squares[i] = new Square(i);
        });
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Square[] getSquares() {
        return squares;
    }

    public Map<Integer, Integer> getChutesAndLadderMap() {
        return chutesAndLadderMap;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardSize=" + boardSize +
                ", squares=" + Arrays.toString(squares) +
                ", chutesAndLadderMap=" + chutesAndLadderMap +
                '}';
    }
}
