import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CompileTest {
    compile compile = new compile();
    boolean cloneSucceeded = CloneRepo.cloneRepo("https://github.com/DD2480group1/assignment2/", "refs/heads/main");

    //Tests if the cloning of the repo works
    @Test
    public void cloneTest(){
        String errorMessage = "The code could not be cloned";
        assertTrue(errorMessage, cloneSucceeded);
    }

    //Tests if the compile class returns an errorCode or not
    @Test
    public void compileTest(){
        int errorCode = compile.compileProject();
        String errorString = String.valueOf(errorCode);
        String errorMessage = "The compilation ended with a error code: " + errorString;
        assertEquals(errorMessage, 0, errorCode);
    }
}
