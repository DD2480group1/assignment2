import com.sendermail.SendMail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * TestMail tests that mail can successfully be sent to an email address.
 *
 *  @author  Isabel Redtzer
 * */
public class TestMail {
    private static SendMail mail;

    /**
     * setUp() creates a new SendMail object.
     * */
    @BeforeClass
    public static void setUp(){
        mail = new SendMail();
    }


    /**
     * testMail() checks that the boolean returned from mail.sendMail() is true.
     * */
    @Test
    public void testMail(){
        boolean sent = mail.sendMail("compile \n adjk \n0", "test \n defj \n0", "Issue1", "isabel.redtzer@gmail.com");
        assertTrue("The email was not sent succesfully",sent);
    }

    /**
     * testFail checks that the boolean returned from mail.sendMail() is false
     * if the e-mail is invalid.
     * */
    @Test
    public void testFail(){
        boolean sent = mail.sendMail("compile \n adjk \n0", "compile \n adjk \n0", "Issue1", "isamail.com");
        assertFalse("Mail can be sent to invalid mails", sent);
    }

}
