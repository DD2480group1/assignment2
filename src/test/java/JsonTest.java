import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.JsonObject;
import static org.junit.Assert.assertTrue;

/**
 * This JsonTest class tests that the Json-reader works and is reading the correct data.
 * The data is read from a static file with the correct format.
 *  @author  Isabel Redtzer
 * */
public class JsonTest {
    static JsonUtil json;
    static JsonObject jsonObject;

    /**
     * setUp() accesses data from the static test file in src/main/java/test.json
     * */
    @BeforeClass
    public static void setUp(){
        SkeletonCode skeletonCode = new SkeletonCode();
        String test = skeletonCode.readFile("./src/main/java/test.json");
        json = new JsonUtil();
        jsonObject = json.getJson(test);
    }

    /**
     * repoURLTest() tests that the repoURL is fetched correctly.
     * */
    @Test
    public void repoURLTest(){
        String expected = "https://github.com/DD2480group1/assignment2";
        String actual = json.getRepoUrl(jsonObject);
        String errorMessage = expected + " is not the same as " + actual;
        assertTrue(errorMessage, expected.equals(actual));
    }

    /**
     * emailTest() tests that the email of the comitter is fetched correctly.
     * */
    @Test
    public void emailTest(){
        String expected = "rubbestads@gmail.com";
        String actual = json.getHeadCommitEmail(jsonObject);
        String errorMessage = expected + " is not the same as " + actual;
        assertTrue(errorMessage, expected.equals(actual));
    }

    /**
     * headCommitIdTest() tests that the commit-ID is fetched correctly.
     * */
    @Test
    public void headCommitIdTest(){
        String expected = "07402d78bf18007353352cbb21a6f7813470caf9";
        String actual = json.getHeadCommitId(jsonObject);
        String errorMessage = expected + " is not the same as " + actual;
        assertTrue(errorMessage, expected.equals(actual));
    }

    /**
     * commitMsgTest() tests that the commit message is fetched correctly.
     * */
    @Test
    public void commitMsgTest(){
        String expected = "test5";
        String actual = json.getCommitMsg(jsonObject);
        String errorMessage = expected + " is not the same as " + actual;
        assertTrue(errorMessage, expected.equals(actual));
    }
}
