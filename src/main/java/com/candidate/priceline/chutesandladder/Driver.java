package com.candidate.priceline.chutesandladder;

import com.candidate.priceline.chutesandladder.exception.InvalidInputException;
import com.candidate.priceline.chutesandladder.model.Request;
import com.candidate.priceline.chutesandladder.service.GameService;
import com.candidate.priceline.chutesandladder.facade.RequestFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Profile({"!test"})
@Component
public class Driver implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(Driver.class.getName());

    @Autowired
    private RequestFacade requestFacade;

    @Autowired
    private GameService gameService;

    private String winner;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting the application");
        argProcessor(args);
        Request request = requestFacade.createRequest(args);
        logger.info("Created Request is {}", request);
        String winnerPlayerName = gameService.startGame(request);
        this.setWinner(winnerPlayerName);
        System.out.printf("The winner is %s!", winnerPlayerName);
        logger.info("Exiting the application.");
    }

    /**
     * Process the arguments passed to driver i.e. player names.
     *
     * @param args
     * @return args(Array of string)
     * @throws InvalidInputException
     */
    protected void argProcessor(String... args) throws InvalidInputException {
        logger.traceEntry("argProcessor");
        if (args == null) {
            logger.error("Arguments provided to application is null.");
            throw new InvalidInputException("No Arguments are provided");
        }
        if (args.length < 2 || args.length > 4) {
            logger.error("Number of args is less than 2 or greater than 4");
            throw new InvalidInputException("Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names.");
        }
        if (!Arrays.stream(args)
                .noneMatch(player -> player == null || player.trim().isEmpty())) {
            logger.error("One of the player name is empty");
            throw new InvalidInputException("One of the player names is empty. Please provide valid player names.");
        }
        logger.traceExit("argProcessor");
    }

    public RequestFacade getRequestFacade() {
        return requestFacade;
    }

    public void setRequestFacade(RequestFacade requestFacade) {
        this.requestFacade = requestFacade;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
