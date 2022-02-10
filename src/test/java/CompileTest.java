import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CompileTest {
    compile compile = new compile();

    //Tests if the compile class returns an error or not
    @Test
    public void compileTest(){
        int errorCode = compile.compileProject();
        String errorString = String.valueOf(errorCode);
        String errorMessage = "The compilation ended with a error code: " + errorString;
        assertEquals(errorMessage, 0, errorCode);
    }
}
