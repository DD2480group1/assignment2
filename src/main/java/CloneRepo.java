import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.nio.file.Paths;

public class CloneRepo {
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
}
