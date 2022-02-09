import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.nio.file.Paths;
import org.eclipse.jgit.api.Git;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 * Skeleton of a ContinuousIntegrationServer which acts as webhook
 * See the Jetty documentation for API documentation of those classes.
 */
class SkeletonCode extends AbstractHandler {
	public void handle(String target,
					   Request baseRequest,
					   HttpServletRequest request,
					   HttpServletResponse response)
			throws IOException, ServletException
	{
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);

		System.out.println(target);

		// here you do all the continuous integration tasks
		// for example
		// 1st clone your repository
		// 2nd compile the code

		response.getWriter().println("CI job done");
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
	public static void main(String[] args) throws Exception
	{
		Server server = new Server(8080);
		server.setHandler(new SkeletonCode());
		server.start();
		server.join();
	}
}
