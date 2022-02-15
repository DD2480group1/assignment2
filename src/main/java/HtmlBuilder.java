import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlBuilder {
    public static boolean buildFile(String repo) {
        File f = new File("./html/" + repo + ".html");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            List<tableEntry> rows =  Database.getRows(repo);

            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");

            writer.write("<table>\n");
            writer.write("<tr>\n");
                writer.write(String.format("<th>%s</th>\n", "commitId"));
                writer.write(String.format("<th>%s</th>\n", "branch"));
                writer.write(String.format("<th>%s</th>\n", "timeStamp"));
                writer.write(String.format("<th>%s</th>\n", "buildInfo"));
                writer.write(String.format("<th>%s</th>\n", "testInfo"));
            writer.write("</tr>\n");


            for (tableEntry row: rows) {
                writer.write("<tr>\n");
                    writer.write(String.format("<td>%s</td>\n", row.commitId));
                    writer.write(String.format("<td>%s</td>\n", row.branch));
                    writer.write(String.format("<td>%s</td>\n", row.timeStamp));
                    writer.write(String.format("<td>%s</td>\n", row.buildInfo));
                    writer.write(String.format("<td>%s</td>\n", row.testInfo));
                writer.write("</tr>\n");
            }

            writer.write("</table>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");

            writer.flush();
            writer.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
