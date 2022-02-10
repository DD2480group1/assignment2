import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class compile {
	public static int compileProject() {
		int errorCode = -1;
		try {
			boolean isWindows = isWindows();
			Process process;
			if (isWindows) {
				String command = "cmd.exe /c cd repo/ & mvn compile & echo %errorlevel%";
				process = Runtime.getRuntime().exec(command);
			} else {
				String[] commands = new String[]{"sh", "-c", "cd repo/","mvn compile; echo $?"};
				process = Runtime.getRuntime().exec(commands);
			}

			String result = new BufferedReader(new InputStreamReader(process.getInputStream()))
					.lines().collect(Collectors.joining("\n"));

			String[] lines = result.split("\n");
			errorCode = Integer.parseInt(lines[lines.length-1]);

			if (errorCode == 0) {
				System.out.println("Ran successfully");
			} else {
				System.out.println("Failed");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errorCode;
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().startsWith("windows");
	}
}
