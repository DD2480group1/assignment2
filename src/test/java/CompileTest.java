import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * This CompileTest class tests that the repo can be cloned,
 * that the repo can be compiled and that the tests can run.
 *
 * @author  Isabel Redtzer
 */
public class CompileTest {
    private static compile compile = new compile();
    private static boolean cloneSucceeded = CloneRepo.cloneRepo("https://github.com/DD2480group1/assignment2/", "refs/heads/main");

    /**
     * The cloneTest function will assert that the cloning of the repo is successful.
     */
    @Test
    public void cloneTest(){
        assertTrue(cloneSucceeded);
    }

    /**
     * The compileTest method gets the error message from the log when compiling the project
     * and asserts that the value of the errorCode is 0.
     * The log is built from running 'mvn compile'
     */
    @Test
    public void compileTest(){
        String result = compile.compileProject();
        String[] lines = result.split("\n");
        int errorCode = Integer.parseInt(lines[lines.length-1]);
        String errorString = String.valueOf(errorCode);
        String errorMessage = "The compilation ended with a error code: " + errorString;
        assertEquals(errorMessage, 0, errorCode);
    }
}
