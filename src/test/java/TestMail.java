import com.sendermail.SendMail;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestMail {
    SendMail mail = new SendMail();


    /*Tests if the email is sent succesfully by checking that the sent boolean is set to true */
    @Test
    public void testMail(){
        boolean sent = mail.sendMail("compile", "test", "Issue1", "isabel.redtzer@gmail.com");
        assertTrue("The email was not sent succesfully",sent);
    }

    /*Asserts that email can't be sent to invalid mail*/
    @Test
    public void testFail(){
        boolean sent = mail.sendMail("compile", "test", "Issue1", "isamail.com");
        assertFalse("Mail can be sent to invalid mails", sent);
    }

}
