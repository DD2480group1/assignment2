import com.sendermail.SendMail;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A continuous integration server which listens for requests sent from GitHub
 * webhooks.
 */
class SkeletonCode extends AbstractHandler {
    /**
     * Function to test read our test.json file (see that everything works)
     * @param path the path to the file to read from
     * @return the contents of the file as a String
     */
    public static String readFile(String path) {
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        String contents = scanner.useDelimiter("\\A").next();
        scanner.close();

        return contents;
    }
    
    /**
     * Starts the continuous integration server and listens to port 8081.
     * @throws Exception may throw an exception upon starting the server.
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        server.setHandler(new SkeletonCode());
        server.start();
        server.join();
    }

    /**
     * Handles requests sent to the server
     * @param s the target of the request.
     * @param request the original unwrapped request object
     * @param httpServletRequest The request either as the Request object or a wrapper of that request
     * @param httpServletResponse The response as the Response object or a wrapper of that request
     * @throws IOException if unable to handle the request or response processing
     * @throws jakarta.servlet.ServletException if unable to handle the request or response due to underlying servlet issue
     */
    @Override
    public void handle(String s, Request request, jakarta.servlet.http.HttpServletRequest httpServletRequest, jakarta.servlet.http.HttpServletResponse httpServletResponse) throws IOException, jakarta.servlet.ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);

        BufferedReader reader = new BufferedReader(httpServletRequest.getReader());
        String payload = reader.lines().collect(Collectors.joining());
        reader.close();

        //Get data from the POST message
        JsonObject obj = JsonUtil.getJson(payload);
        String commitMsg = JsonUtil.getCommitMsg(obj);
        String ref = JsonUtil.getCommitRef(obj);
        System.out.println(commitMsg);
        String url = JsonUtil.getRepoUrl(obj);
        String email = JsonUtil.getHeadCommitEmail(obj);

        //Clones the repo
        boolean cloneSucceeded = CloneRepo.cloneRepo(url, ref);
        if (cloneSucceeded) {
            System.out.println("Successfully cloned repo from" + url);
        } else {
            System.out.println("Failed to clone repo from" + url);
        }

        //Runs mvn build and stores output in compileMessage
        String compileMessage = compile.compileProject();

        //Runs mvn test -q and stores output in testMessage
        String testMessage = compile.testRepo();

        //Send email to the person who pushed to the repo
        SendMail mail = new SendMail();
        mail.sendMail(compileMessage, testMessage, ref, email);

        httpServletResponse.getWriter().println("CI job done");
    }
}





