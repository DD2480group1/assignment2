import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.*;

import org.eclipse.jgit.api.Git;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jgit.api.errors.GitAPIException;

import javax.json.*;

/**
 * Skeleton of a ContinuousIntegrationServer which acts as webhook
 * See the Jetty documentation for API documentation of those classes.
 */
class SkeletonCode extends AbstractHandler {
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        System.out.println(target);

        // here you do all the continuous integration tasks
        // for example
        // 1st clone your repository
        // 2nd compile the code


        BufferedReader reader = new BufferedReader(request.getReader());
        String payload = reader.lines().collect(Collectors.joining());
        reader.close();

        System.out.println(payload);

        response.getWriter().println("CI job done");
    }

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
        scanner.close(); // Put this call in a finally block

        return contents;
    }


    public static boolean cloneRepo() {
        String url = "https://github.com/DD2480group1/assignment2";
        String path = "./repo/";

        try {
            Git.cloneRepository().setURI(url).setDirectory(Paths.get(path).toFile()).call();
            return true;
        } catch (GitAPIException e) {
            e.printStackTrace();
            return false;
        }
    }

    // used to start the CI server in command line
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new SkeletonCode());
        //System.out.println(readFile("./src/main/java/test.json"));
        server.start();
        server.join();
    }
}





