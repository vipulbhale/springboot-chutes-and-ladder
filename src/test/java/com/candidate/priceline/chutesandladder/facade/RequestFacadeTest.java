package com.candidate.priceline.chutesandladder.facade;

import com.candidate.priceline.chutesandladder.model.Player;
import com.candidate.priceline.chutesandladder.model.Request;
import com.candidate.priceline.chutesandladder.service.Spinner;
import org.junit.After;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestFacadeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private Spinner spinner;

    @Autowired
    private RequestFacade requestFacade;

    private String[][] playerNames = new String[][]{{"ERIK", "PETER"}, {"ERIK", "PETER", "JOHN"}, {"ERIK", "PETER", "JOHN", "DAVID"}};


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        requestFacade.setSpinner(spinner);
    }

    @After
    public void tearDown() throws Exception {
        //forcefully changing the value as some how between different test classes its keeping the same context.
        requestFacade.setChutesAndLadderConfigLocation("chutes_ladders.json");
    }

    /**
     * Given
     *      Two players are playing i.e. number of inputs are 2 string representing names of 2 players
     * When
     *      Requestfacade.createPlayersUsingName is called
     * Then
     *      List<Player> is created based on the names provided to the requestFacade instance.
     *      make sure list size is equal to the array's size of player names which is input to this method.
     *      names of players in the list matches with array of strings.
     */
    @Test
    public void test_a_should_create_player_list_createPlayersUsingName() {
        Arrays.stream(playerNames).forEachOrdered(
                players -> {
                    List<String> playerNames = Arrays.asList(players);
                    List<Player> objPlayers = requestFacade.createPlayersUsingName(players);
                    assertThat("Size of List of Players is equal to size of array", objPlayers.size(), is(players.length));

                    objPlayers.stream().forEachOrdered(
                            objPlayer -> {
                                assertThat("Players names in the list and array should match. ", playerNames, hasItem(objPlayer.getName()));
                            }
                    );
                }
        );
    }

    /**
     * Given
     *          two players are playing i.e. number of inputs are 2 string representing names of 2 players
     * When
     *          RequestFacade.orderPlayersBasedOnTossSpin method invoked
     * Then
     *          should return list of Players that are ordered as per their spin's highest value.
     *          Here List is ordered by PETER, ERIK, DAVID, JOHN
     */
    @Test
    public void test_b_should_create_orderered_player_list_orderPlayersBasedOnTossSpin() {
        when(spinner.spin()).thenReturn(4).thenReturn(5).thenReturn(2).thenReturn(3);
        List<Player> players = requestFacade.createPlayersUsingName(playerNames[2]);
        List<Player> orderedPlayers = requestFacade.orderPlayersBasedOnTossSpin(players);
        IntStream.range(0, orderedPlayers.size()).forEachOrdered(
                i -> {
                    assertThat("Player are ordered as per spin", orderedPlayers.get(i).getTurnOrder(), is(i + 1));
                }
        );
        verify(spinner,times(4)).spin();
        assertThat("PlayerName to identify the orderedPlayer", orderedPlayers.get(0).getName(), is("PETER"));
        assertThat("PlayerName to identify the orderedPlayer", orderedPlayers.get(1).getName(), is("ERIK"));
        assertThat("PlayerName to identify the orderedPlayer", orderedPlayers.get(2).getName(), is("DAVID"));
        assertThat("PlayerName to identify the orderedPlayer", orderedPlayers.get(3).getName(), is("JOHN"));
    }

    /**
     * Given
     *      chutesandladderconfig i.e. chutes_ladders.json file exists
     * When
     *      RequestFacade.setupChutesAndladders method is invoked
     * Then
     *      should return a map of <Integer, Integer> type including all values present in the json file.
     */
    @Test
    public void test_c_should_create_chutesandladder_config_setupChutesAndladders() throws IOException {
        Map<Integer, Integer> expectedConfigChutesAndLadder = Stream.of(new Integer[][]{
                        {64, 60},
                        {1, 38},
                        {98, 78},
                        {36, 44},
                        {4, 14},
                        {71, 91},
                        {9, 31},
                        {47, 26},
                        {16, 6},
                        {80, 100},
                        {49, 11},
                        {51, 67},
                        {21, 42},
                        {87, 24},
                        {56, 53},
                        {28, 84},
                        {93, 73},
                        {62, 19},
                        {95, 75}
                }
        ).collect(Collectors.toMap(
                data -> data[0],
                data -> data[1]
        ));

        Map<Integer, Integer> configChutesAndLadder = requestFacade.setupChutesAndladders();
        assertThat("Size of maps should be same", configChutesAndLadder.size(), is(expectedConfigChutesAndLadder.size()));
        assertTrue("Comparing expected and actual chutes and ladder config", configChutesAndLadder.equals(expectedConfigChutesAndLadder));
    }

    /**
     * Given
     *      2 Players playing
     *      Chutes and Laders config is provided by chutes_ladders.json file
     *      number of squares is 100
     * When
     *      RequestFacade.createRequest method is called
     * Then
     *      Should return object of Type Request with following attributes verified
     *      1. boardSize of the game in Request object equal to provided value by square.total value in properties file
     *      2. Square size array size should be size provided + 1
     *      3. PlayerNames in game object equal to names provided.
     *      4. Chutes and Ladder configuration equal to provided by json file and number of chutes+ladder equal to size of the map and is same as number of json element.
     */
    @Test
    public void test_e_should_create_request_createRequest() throws IOException {
        Map<Integer, Integer> expectedConfigChutesAndLadder = Stream.of(new Integer[][]{
                        {64, 60},
                        {1, 38},
                        {98, 78},
                        {36, 44},
                        {4, 14},
                        {71, 91},
                        {9, 31},
                        {47, 26},
                        {16, 6},
                        {80, 100},
                        {49, 11},
                        {51, 67},
                        {21, 42},
                        {87, 24},
                        {56, 53},
                        {28, 84},
                        {93, 73},
                        {62, 19},
                        {95, 75}
                }
        ).collect(Collectors.toMap(
                data -> data[0],
                data -> data[1]
        ));


        Request requestActual1 = requestFacade.createRequest(playerNames[0]);
        List<String> actualPlayerNames = requestActual1.getGame().getPlayers().stream().map(player -> {
            return player.getName();
        }).collect(Collectors.toList());
        assertThat("Boardsize equal to square list/array", requestActual1.getGame().getBoard().getSquares().length, is(101));
        assertThat("Boardsize of game object should match with parameter", requestActual1.getGame().getBoard().getBoardSize(), is(100));
        assertThat("Player Size matches", requestActual1.getGame().getPlayers().size(), is(playerNames[0].length));

        actualPlayerNames.forEach(
                playerName -> {
                    assertThat("Players Name should match", Arrays.asList("ERIK", "PETER"), hasItem(playerName));
                }

        );
        assertThat("Size of maps should be same", requestActual1.getGame().getBoard().getChutesAndLadderMap().size(), is(expectedConfigChutesAndLadder.size()));
        assertTrue("Comparing expected and actual chutes and ladder config", requestActual1.getGame().getBoard().getChutesAndLadderMap().equals(expectedConfigChutesAndLadder));
    }

    /**
     * Given
     *      chutesandladderconfig i.e. dummy_chutes_ladders.json file **doesn't** exist.
     * When
     *      RequestFacade.setupChutesAndladders method invoked
     * Then
     *      Should return IOException with message "Unable to read the chutes and ladder config json at dummy_chutes_ladders.json"
     */
    @Test
    public void test_f_should_create_chutesandladder_config_setupChutesAndladders() throws IOException {
        thrown.expect(IOException.class);
        thrown.expectMessage("Unable to read the chutes and ladder config json at dummy_chutes_ladders.json");
        requestFacade.setChutesAndLadderConfigLocation("dummy_chutes_ladders.json");
        Map<Integer, Integer> configChutesAndLadder = requestFacade.setupChutesAndladders();
    }

    /**
     * Given
     *      2 Players playing
     *      Chutes and Laders config is provided by dummy_chutes_ladders.json file that doesn't exist
     *      number of squares is 100
     * When
     *      RequestFacade.createRequest method is called
     * Then
     *      Should throw IOException with message "Unable to read the chutes and ladder config json at dummy_chutes_ladders.json"
     */
    @Test
    public void test_f1_should_throw_exception_createRequest() throws IOException {
        thrown.expect(IOException.class);
        thrown.expectMessage("Unable to read the chutes and ladder config json at dummy_chutes_ladders.json");
        requestFacade.setChutesAndLadderConfigLocation("dummy_chutes_ladders.json");
        Request requestActual = requestFacade.createRequest( playerNames[0]);
    }


}