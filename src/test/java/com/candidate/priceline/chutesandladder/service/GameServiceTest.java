package com.candidate.priceline.chutesandladder.service;

import com.candidate.priceline.chutesandladder.facade.RequestFacade;
import com.candidate.priceline.chutesandladder.model.MoverType;
import com.candidate.priceline.chutesandladder.model.Request;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private Spinner spinner;

    @Autowired
    private RequestFacade requestFacade;

    @Autowired
    private GameService gameService;

    private String[][] playerNames = new String[][]{{"TOM", "DICK"}, {"TOM", "DICK", "HARRY"}, {"TOM", "DICK", "HARRY", "BOB"}};


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        requestFacade.setSpinner(spinner);
        gameService.setSpinner(spinner);
    }

    /**
     * Given
     *          Game Service is instance available
     * When
     *          GameService.printOutput method invoked without MoverType and EffectiveNewPosition
     * Then
     *          should return properly formatted test as "<turn>: <playerName>: <currentPosition> --> <newPosition/nextPosition>"
     */
    @Test
    public void test_a_printOutput() {
        String actualPrintText = gameService.printOutput(100, "ERIK", 20, 25);
        String actualPrintText1 = gameService.printOutput(1, "PETER", 10, 12);

        assertThat("Verify the printed text is formatted correctly", actualPrintText, is("100: ERIK: 20 --> 25"));
        assertThat("Verify the printed text is formatted correctly", actualPrintText1, is("1: PETER: 10 --> 12"));
        assertNotSame("Verify the printed text is formatted correctly", "1: PETER: 10 --> 13", actualPrintText1);
    }

    /**
     * Given
     *          Game Service is instance available
     * When
     *          GameService.printOutput method invoked with MoverType and EffectiveNewPosition
     * Then
     *          should return properly formatted test as "<turn>: <playerName>: <currentPosition> --> <newPosition/nextPosition> --<CHUTE/LADDER>--> <effectiveNewPosition>"
     */
    @Test
    public void test_b_printOutput() {
        String actualPrintText = gameService.printOutput(100, "ERIK", 83, 88, MoverType.LADDER, 100);
        String actualPrintText1 = gameService.printOutput(101, "PETER", 92, 95, MoverType.CHUTE, 75);

        assertThat("Verify the printed text is formatted correctly", actualPrintText, is("100: ERIK: 83 --> 88 --LADDER--> 100"));
        assertThat("Verify the printed text is formatted correctly", actualPrintText1, is("101: PETER: 92 --> 95 --CHUTE--> 75"));
    }


    /**
     * Given
     *          Game Service is instance available with request containing, given chutes ladder config, 2 players, boardsize = 100
     * When
     *          GameService.startGame method
     * Then
     *          should return TOM as winner
     */
    @Test
    public void test_c_1_with_two_players_startGame() throws IOException {
        when(spinner.spin()).thenReturn(4).thenReturn(5).thenReturn(5).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(4).thenReturn(5).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(1).thenReturn(3).thenReturn(2).thenReturn(1).thenReturn(5).thenReturn(5).thenReturn(5).thenReturn(4).thenReturn(1).thenReturn(4).thenReturn(3).thenReturn(3).thenReturn(2).thenReturn(3).thenReturn(6).thenReturn(5).thenReturn(2).thenReturn(1).thenReturn(3).thenReturn(2).thenReturn(5).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(5).thenReturn(5).thenReturn(5).thenReturn(4).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(1).thenReturn(6).thenReturn(5).thenReturn(2).thenReturn(1).thenReturn(6).thenReturn(2);
        Request requestActual = requestFacade.createRequest(playerNames[0]);
        String winnerPlayer = gameService.startGame(requestActual);
        verify(spinner,times(52)).spin();
        assertThat("startGame test to verify Tom is winner in 2 player game", winnerPlayer, is("TOM"));
    }

    /**
     * Given
     *          Game Service is instance available with request containing, given chutes ladder config, 3 players, boardsize = 100
     * When
     *          GameService.startGame method
     * Then
     *          should return TOM as winner
     */
    @Test
    public void test_c_2_with_three_players_startGame() throws IOException {
        when(spinner.spin()).thenReturn(5).thenReturn(4).thenReturn(6).thenReturn(3).thenReturn(4).thenReturn(5).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(6).thenReturn(2).thenReturn(6).thenReturn(5).thenReturn(1).thenReturn(5).thenReturn(1).thenReturn(5).thenReturn(3).thenReturn(2).thenReturn(2).thenReturn(2).thenReturn(5).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(3).thenReturn(4).thenReturn(4).thenReturn(6);
        Request requestActual = requestFacade.createRequest(playerNames[1]);
        String winnerPlayer = gameService.startGame(requestActual);
        verify(spinner,times(29)).spin();
        assertThat("startGame test to verify TOM is winner in 3 player game", winnerPlayer, is("TOM"));
    }

    /**
     * Given
     *          Game Service is instance available with request containing, given chutes ladder config, 4 players, boardsize = 100
     * When
     *          GameService.startGame method
     * Then
     *          should return HARRY as winner
     */
    @Test
    public void test_c_3_with_four_players_startGame() throws IOException {
        when(spinner.spin()).thenReturn(3).thenReturn(5).thenReturn(4).thenReturn(1).
                thenReturn(5).thenReturn(3).thenReturn(3).thenReturn(2).thenReturn(3).thenReturn(3).thenReturn(3).thenReturn(6).thenReturn(1).thenReturn(4).thenReturn(1).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(4).thenReturn(2).thenReturn(6).thenReturn(5).thenReturn(1).thenReturn(1).thenReturn(5).thenReturn(6).thenReturn(3).thenReturn(5).thenReturn(6).thenReturn(1).thenReturn(3).thenReturn(3).thenReturn(4).thenReturn(1).thenReturn(4).thenReturn(2).thenReturn(5).thenReturn(3).thenReturn(3).thenReturn(3).thenReturn(3).thenReturn(6).thenReturn(6).thenReturn(5).thenReturn(4).thenReturn(3).thenReturn(1).thenReturn(4).thenReturn(4).thenReturn(1).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(1).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(4).thenReturn(6).thenReturn(6).thenReturn(2).thenReturn(2).thenReturn(1).thenReturn(3).thenReturn(3).thenReturn(3).thenReturn(2).thenReturn(5).thenReturn(5).thenReturn(1).thenReturn(6).thenReturn(4).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(2).thenReturn(2).thenReturn(3).thenReturn(6).thenReturn(5).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(1).thenReturn(1).thenReturn(5).thenReturn(2).thenReturn(3).thenReturn(1).thenReturn(3).thenReturn(4).thenReturn(1).thenReturn(5).thenReturn(2).thenReturn(5).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(4).thenReturn(6).thenReturn(4).thenReturn(2).thenReturn(5).thenReturn(1);
        ;

        Request requestActual = requestFacade.createRequest(playerNames[2]);
        String winnerPlayer = gameService.startGame(requestActual);
        verify(spinner,times(106)).spin();
        assertThat("startGame test to verify HARRY is winner in 4 player game", winnerPlayer, is("HARRY"));
    }

    /**
     * When Two Players are playing
     *  Test the startGame method of GameService is going through all chutes and ladders
     *  Should return HARRY as Winner
     */
    @Test
    public void test_d_with_2_players_all_chutesAndLadders_run() throws Exception {
        when(spinner.spin()).thenReturn(5).thenReturn(3).thenReturn(1).thenReturn(4).thenReturn(6).thenReturn(2).thenReturn(6).thenReturn(3).thenReturn(1).thenReturn(5).thenReturn(4).thenReturn(3).thenReturn(3).thenReturn(5).thenReturn(1).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(6).thenReturn(4).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(6).thenReturn(4).thenReturn(3).thenReturn(6).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(2).thenReturn(2).thenReturn(6).thenReturn(3).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(5).thenReturn(1).thenReturn(6).thenReturn(1).thenReturn(3).thenReturn(1).thenReturn(6).thenReturn(1).thenReturn(6).thenReturn(1).thenReturn(6).thenReturn(1).
                thenReturn(2);
        Request requestActual = requestFacade.createRequest(playerNames[2]);
        String winnerPlayer = gameService.startGame(requestActual);
        verify(spinner,times(64)).spin();
        assertThat("startGame test to verify HARRY is winner in 4 player game", winnerPlayer, is("HARRY"));
    }


}