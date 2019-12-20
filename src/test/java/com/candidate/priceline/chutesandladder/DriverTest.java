package com.candidate.priceline.chutesandladder;

import com.candidate.priceline.chutesandladder.exception.InvalidInputException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DriverTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private String[][] inputs;

    @Before
    public void setUp() throws Exception {
        inputs = new String[][]{{}, {"John1"}, {"John1", "John2"}, {"John1", "John2", "John3"}, {"John1", "John2", "John3", "John4"}, {"John1", "John2", "John3", "John4", "John5"}, {"John1", "John2", "John3", "John4", "John5", "John6"}, {"John1", "John2", "", "John4"}};
    }



    /**
     * Given
     *      array of playernames  is null
     * When
     *      Driver.argProcessor is called with it
     * Then
     *      should throw InvalidInputException with message "No Arguments are provided"
     */
    @Test
    public void test_a_argProcessor() throws InvalidInputException {
        thrown.expect(InvalidInputException.class);
        thrown.expectMessage("No Arguments are provided");
        Driver driver = new Driver();
        driver.argProcessor(null);
    }

    /**
     * Given
     *      array of playernames is empty
     * When
     *      Driver.argProcessor is called with it
     * Then
     *      should throw InvalidInputException with message "Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names."
     */
    @Test
    public void test_a_1_argProcessor() throws InvalidInputException {
        thrown.expect(InvalidInputException.class);
        thrown.expectMessage("Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names.");
        Driver driver = new Driver();
        driver.argProcessor(inputs[0]);
    }

    /**
     * Given
     *      number of playernames as input is 1
     * When
     *      Driver.argProcessor is called with it
     * Then
     *      Should throw invalidinputexception with message "Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names."
     */
    @Test
    public void test_b_invalidnumber_of_args_to_argProcessor() throws InvalidInputException {
        thrown.expect(InvalidInputException.class);
        thrown.expectMessage("Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names.");
        Driver driver = new Driver();
        driver.argProcessor(inputs[1]);
    }


    /**
     * Given
     *      number of playernames as input is 2
     * When
     *      Driver.argProcessor is called with it
     * Then
     *      Should not throw invalidinputexception
     */
    @Test
    public void test_c_invalidnumber_of_args_to_argProcessor() throws InvalidInputException {
        Driver driver = new Driver();
        driver.argProcessor(inputs[2]);
    }

    /**
     * Given
     *      number of playernames as input is 3
     * When
     *      Driver.argProcessor is called with it
     * Then
     *      should not throw invalidinputexception
     */
    @Test
    public void test_e_invalidnumber_of_args_to_argProcessor() throws InvalidInputException {
        Driver driver = new Driver();
        driver.argProcessor(inputs[3]);
    }

    /**
     * Given
     *      number of playernames as input is 4
     * When
     *      Driver.argProcessor is called with it
     * Then
     *      should not throw invalidinputexception
     */
    @Test
    public void test_f_invalidnumber_of_args_to_argProcessor() throws InvalidInputException {
        Driver driver = new Driver();
        driver.argProcessor(inputs[4]);
    }


    /**
     * Given
     *      number of playernames as input is 5
     * When
     *      Driver.argProcessor is called with it
     * Then
     *      should throw invalidinputexception with message "Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names."
     */
    @Test
    public void test_g_invalidnumber_of_args_to_argProcessor() throws InvalidInputException {
        thrown.expect(InvalidInputException.class);
        thrown.expectMessage("Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names.");
        Driver driver = new Driver();
        driver.argProcessor(inputs[5]);
    }

    /**
     * Given
     *      number of playernames as input is 5
     * When
     *      Driver.argProcessor is called with it
     * Then
     *     should throw invalidinputexception with message "Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names."
     */
    @Test
    public void test_h_invalidnumber_of_args_to_argProcessor() throws InvalidInputException {
        thrown.expect(InvalidInputException.class);
        thrown.expectMessage("Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names.");
        Driver driver = new Driver();
        driver.argProcessor(inputs[6]);
    }

    /**
     * Given
     *      one of playernames is empty as input
     * When
     *      Driver.argProcessor is called with it
     * Then
     *      should throw invalidinputexception with message "One of the player names is empty. Please provide valid player names."
     */
    @Test
    public void test_i_invalidnumber_of_args_to_argProcessor() throws InvalidInputException {
        thrown.expect(InvalidInputException.class);
        thrown.expectMessage("One of the player names is empty. Please provide valid player names.");
        Driver driver = new Driver();
        driver.argProcessor(inputs[7]);
    }
}