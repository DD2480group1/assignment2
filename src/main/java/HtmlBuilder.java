import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlBuilder {

    public static void main(String[] args) {
        buildFile("abc");
    }
    public static boolean buildFile(String repo) {
        File f = new File("./html/" + repo + ".html");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            List<tableEntry> rows =  Database.getRows(repo);

            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n\n");
            writer.write("<link rel=\"stylesheet\" href=\"style.css\">\n");
            writer.write("<head/>\n");
            writer.write("<body>\n");
            writer.write("<img src=\"g1.png\" alt=\"G1\" width=\"500\">");

            writer.write("<table class=\"table table-striped\" \n");
            writer.write("<tr>\n");
                writer.write(String.format("<th>%s</th>\n", "Commit ID"));
                writer.write(String.format("<th>%s</th>\n", "Branch"));
                writer.write(String.format("<th>%s</th>\n", "Timestamp"));
                writer.write(String.format("<th>%s</th>\n", "Build success"));
                writer.write(String.format("<th>%s</th>\n", "Test success"));
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
