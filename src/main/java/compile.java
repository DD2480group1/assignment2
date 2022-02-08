import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class compile {
	public static void main(String[] args) {
		try {
			boolean isWindows = isWindows();
			Process process;
			String[] commands;
			if (isWindows) {
				String command = "cmd.exe /c echo 1";
				process = Runtime.getRuntime().exec(command);
			} else {
				commands = new String[]{"sh", "-c", "echo 1"};
				process = Runtime.getRuntime().exec(commands);
			}

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

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().startsWith("windows");
	}

}
