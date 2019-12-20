package com.candidate.priceline.chutesandladder.service;

import com.candidate.priceline.chutesandladder.model.MoverType;
import com.candidate.priceline.chutesandladder.model.Player;
import com.candidate.priceline.chutesandladder.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameService {
    private static final Logger logger = LogManager.getLogger(GameService.class.getName());

    @Autowired
    private Spinner spinner;

    public GameService(Spinner spinner) {
        this.spinner = spinner;
    }

    public GameService() {
    }

    /**
     * Methdd starts the game.
     *
     * @return String : name of the winner
     */
    public String startGame(Request request) {
        logger.traceEntry("startGame");
        Map<Integer, Integer> chuteAndLadderConfig = request.getGame().getBoard().getChutesAndLadderMap();
        Player player = null;
        int turn = 0;
        List<Player> players = request.getGame().getPlayers();
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                player = players.get(i);
                int currentPosition = player.getCurrentPosition();
                int spin = spinner.spin();
                turn++;
                int newPosition = currentPosition + spin;
                int effectiveNewPosition = newPosition;
                if (newPosition <= 100) {
                    if (request.getGame().getBoard().getSquares()[currentPosition + spin].hasChuteOrLadder()) {
                        effectiveNewPosition = chuteAndLadderConfig.get(currentPosition + spin);
                        player.setCurrentPosition(effectiveNewPosition);
                        player.getSquarePositions().add(effectiveNewPosition);
                        System.out.println(printOutput(turn, player.getName(), currentPosition, newPosition, request.getGame().getBoard().getSquares()[currentPosition + spin].getMoverType(), effectiveNewPosition));

                    } else {
                        player.setCurrentPosition(effectiveNewPosition);
                        player.getSquarePositions().add(effectiveNewPosition);
                        System.out.println(printOutput(turn, player.getName(), currentPosition, effectiveNewPosition));
                    }
                    if (effectiveNewPosition == 100)
                        return player.getName();
                }
            }
        }
    }

    /**
     * This is output format
     *
     * @param turn            total number of turns in the game
     * @param playerName      Name of the player
     * @param currentPosition currentPosition of the player pawn on the board
     * @param nextPosition    effective nextPosition of the player after spinner is run on the board
     */
    protected String printOutput(int turn, String playerName, int currentPosition, int nextPosition) {
        return String.format("%d: %s: %d --> %d", turn, playerName, currentPosition, nextPosition);
    }

    /**
     * @param turn
     * @param playerName
     * @param currentPosition
     * @param nextPosition
     * @param chuteOfLadder
     * @param effectiveNewPosition
     */
    protected String printOutput(int turn, String playerName, int currentPosition, int nextPosition, MoverType chuteOfLadder, int effectiveNewPosition) {
        return String.format("%d: %s: %d --> %d --%s--> %d", turn, playerName, currentPosition, nextPosition, chuteOfLadder, effectiveNewPosition);

    }


    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

}
