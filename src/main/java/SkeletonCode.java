import com.sendermail.SendMail;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Skeleton of a ContinuousIntegrationServer which acts as webhook
 * See the Jetty documentation for API documentation of those classes.
 */
class SkeletonCode extends AbstractHandler {
    /**
     * Function to test read our test.json file (see that everything works)
     * @param path
     * @return
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


    // used to start the CI server in command line
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        server.setHandler(new SkeletonCode());
        server.start();
        server.join();
    }

    @Override
    public void handle(String s, Request request, jakarta.servlet.http.HttpServletRequest httpServletRequest, jakarta.servlet.http.HttpServletResponse httpServletResponse) throws IOException, jakarta.servlet.ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);

        System.out.println(s);

        // here you do all the continuous integration tasks
        // for example
        // 1st clone your repository
        // 2nd compile the code


        BufferedReader reader = new BufferedReader(httpServletRequest.getReader());
        String payload = reader.lines().collect(Collectors.joining());
        reader.close();

        JsonObject obj = JsonUtil.getJson(payload);
        String commitMsg = JsonUtil.getCommitMsg(obj);
        String ref = JsonUtil.getCommitRef(obj);
        System.out.println(commitMsg);
        String url = JsonUtil.getRepoUrl(obj);
        String email = JsonUtil.getHeadCommitEmail(obj);

        boolean cloneSucceeded = CloneRepo.cloneRepo(url, ref);
        if (cloneSucceeded) {
            System.out.println("Successfully cloned repo from" + url);
        } else {
            System.out.println("Failed to clone repo from" + url);
        }

        String compileMessage = compile.compileProject();

        String testMessage = compile.testRepo();

        SendMail mail = new SendMail();
        mail.sendMail(compileMessage, testMessage, ref, email);

        httpServletResponse.getWriter().println("CI job done");
    }
}





