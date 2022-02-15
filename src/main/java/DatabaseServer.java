import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

class DatabaseServer extends AbstractHandler {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8082);
        server.setHandler(new DatabaseServer());
        server.start();
        server.join();
    }

    @Override
    public void handle(String s, Request request, jakarta.servlet.http.HttpServletRequest httpServletRequest, jakarta.servlet.http.HttpServletResponse httpServletResponse) throws IOException, jakarta.servlet.ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
        System.out.println(s);

        String repo = s.split("/")[1];
        String commitId = request.getParameter("commitId");

        httpServletResponse.getWriter().println(getResponse(repo, commitId));
    }

    private static String getResponse(String repo, String commitId) {
        String html;
        if (commitId != null) {
            try {
                Optional<tableEntry> opt = Database.getRow(repo, commitId);
                tableEntry row = opt.get();
                html = String.valueOf(row);
            } catch (Exception e) {
                html = String.format("No such commit (%s) in repo %s", commitId, repo);
            }
        } else {
            HtmlBuilder.buildFile(repo);
            html = SkeletonCode.readFile("./html/" + repo + ".html");
        }
        return html;
    }
}





