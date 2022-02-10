import org.junit.Test;

import javax.json.JsonObject;
import static org.junit.Assert.assertTrue;

public class JsonTest {

    SkeletonCode skeletonCode = new SkeletonCode();
    String test = skeletonCode.readFile("./src/main/java/test.json");
    Json json = new Json();
    JsonObject jsonObject = json.getJson(test);

    //Tests if the RepoURL is retrieved correctly from test file
    @Test
    public void RepoURLTest(){
        String expected = "https://github.com/DD2480group1/assignment2";
        String actual = json.getRepoUrl(jsonObject);
        String errorMessage = expected + " is not the same as " + actual;
        assertTrue(errorMessage, expected.equals(actual));
    }

    //Tests if the email is retrieved correctly from test file
    @Test
    public void EmailTest(){
        String expected = "rubbestads@gmail.com";
        String actual = json.getHeadCommitEmail(jsonObject);
        String errorMessage = expected + " is not the same as " + actual;
        assertTrue(errorMessage, expected.equals(actual));
    }

    //Tests if the commit-ID is retrieved correctly from test file
    @Test
    public void HeadCommitIdTest(){
        String expected = "07402d78bf18007353352cbb21a6f7813470caf9";
        String actual = json.getHeadCommitId(jsonObject);
        String errorMessage = expected + " is not the same as " + actual;
        assertTrue(errorMessage, expected.equals(actual));
    }

    //Tests if the Commit Message is retrieved correctly from test file
    @Test
    public void CommitMsgTest(){
        String expected = "test5";
        String actual = json.getCommitMsg(jsonObject);
        String errorMessage = expected + " is not the same as " + actual;
        assertTrue(errorMessage, expected.equals(actual));
    }
}
