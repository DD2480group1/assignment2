import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * The class allows for cloning a repo so that the codebase can be built and
 * tested for the continuous integration.
 */
public class CloneRepo {
    /**
     * This function clones the code from the repo into a folder at /repo
     * @param url the url of the repo
     * @param branch the branch to clone from
     * @return whether the repo cloned successfully
     */
    public static boolean cloneRepo(String url, String branch){
        String path = "./repo/";
        try {
            // If folder already exists remove it.
            FileUtils.delete(new File(path), FileUtils.RECURSIVE);
        } catch (IOException e) {}
        try {
            Git.cloneRepository().setURI(url).setBranchesToClone(Arrays.asList(branch)).setBranch(branch).setDirectory(Paths.get(path).toFile()).call();
            return true;
        } catch (GitAPIException e) {
            e.printStackTrace();
            return false;
        }
    }
}
