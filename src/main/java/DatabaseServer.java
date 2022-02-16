import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

class DatabaseServer extends AbstractHandler {
    @Override
    public void handle(String s, Request request, jakarta.servlet.http.HttpServletRequest httpServletRequest, jakarta.servlet.http.HttpServletResponse httpServletResponse) throws IOException, jakarta.servlet.ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);

        String[] path = s.split("/");
        String repo = path[1];
        String commitId = (path.length > 2) ? path[2] : null;

        // When accessing localhost:8082 from the browser it will also ask for favicon.ico.
        if (repo.equals("favicon.ico")) {
            httpServletResponse.getWriter().println();
            return;
        }

        if (commitId == null) {
            List<tableEntry> rows = Database.getRows(repo);
            httpServletResponse.getWriter().println(JsonUtil.encodeQueryResults(rows));
        } else {
            Optional<tableEntry> row = Database.getRow(repo, commitId);
            String res;
            if (row.isPresent()) {
                res = JsonUtil.encodeRow(row.get()).toString();
            } else {
                res = "No result found";
            }

            httpServletResponse.getWriter().println(res);
        }


        // httpServletResponse.getWriter().println(getResponse(repo, commitId));
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





