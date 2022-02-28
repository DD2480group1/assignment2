import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CompileTest {
    private static compile compile = new compile();
    private static boolean cloneSucceeded = CloneRepo.cloneRepo("https://github.com/DD2480group1/assignment2/", "refs/heads/main");

    //Tests if the cloning of the repo works
    @Test
    public void cloneTest(){
        assertTrue(cloneSucceeded);
    }

    //Tests if the compile class returns an errorCode or not
    @Test
    public void compileTest(){
        String result = compile.compileProject();
        String[] lines = result.split("\n");
        int errorCode = Integer.parseInt(lines[lines.length-1]);
        String errorString = String.valueOf(errorCode);
        String errorMessage = "The compilation ended with a error code: " + errorString;
        assertEquals(errorMessage, 0, errorCode);
    }
    //Tests if the test class returns an errorCode or not
    @Test
    public void testsTest(){
        String test = compile.testRepo();
        String[] lines = test.split("\n");
        int errorCode = Integer.parseInt(lines[lines.length-1]);
        String errorString = String.valueOf(errorCode);
        String errorMessage = "The compilation ended with a error code: " + errorString;
        assertEquals(errorMessage, 0, errorCode);
    }
}
