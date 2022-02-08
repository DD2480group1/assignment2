import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class compile {

    public static void main(String[] args) {
        try {
            String[] commands = {"sh", "-c", "echo 1"};
            Process process = Runtime.getRuntime().exec(commands);
            String result = new BufferedReader(new InputStreamReader(process.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
            String error = new BufferedReader(new InputStreamReader(process.getErrorStream()))
                    .lines().collect(Collectors.joining("\n"));
            //System.out.println(result);
            //System.out.println(error);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
