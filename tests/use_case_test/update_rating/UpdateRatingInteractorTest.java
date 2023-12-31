package use_case_test.update_rating;
import org.junit.After;
import org.junit.Before;
import use_case.update_rating.*;
import org.junit.Test;
import data_access.UserRatingAccessObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class UpdateRatingInteractorTest {
    private final String testFilePath = "./testUserRating.csv";
    private List<String> originalFileContent;
    @Before
    public void setUp() throws IOException {
        // Save the original file content
        originalFileContent = Files.readAllLines(Paths.get(testFilePath));
    }

    //Tests if a rating is properly updated. A userRating is first initialized to have a rating of 3.
    //The interactor is called and updates the database, the new rating should be updated to 3.
    @Test
    public void testUpadateRating() {
        String imdb = "tt0345950";
        String username = "Alex";
        int r = 5;
        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject(testFilePath);
        ratingAccessObject.updateRating(username, imdb, r);
        UpdateRatingOutputBoundary presenter = new UpdateRatingOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateRatingOutputData rating) {

                int x = ratingAccessObject.getUserRating(username, imdb).getRating();
                assertEquals(x, rating.getNewRating());
            }
        };

        UpdateRatingInputData inputData = new UpdateRatingInputData(3, username, imdb);
        UpdateRatingInputBoundary interactor = new UpdateRatingInteractor(ratingAccessObject, presenter);
        interactor.execute(inputData); // Will send output data to presenter to be checked
    }

    @After
    public void tearDown() throws IOException {
        // Restore the original file content
        Files.write(Paths.get(testFilePath), originalFileContent);
    }

}
