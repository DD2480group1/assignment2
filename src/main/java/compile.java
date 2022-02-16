import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class compile {
	public static String compileProject() {
		String result = "";
		try {
			boolean isWindows = isWindows();
			Process process;
			if (isWindows) {
				String command = "cmd.exe /c cd repo/ & mvn compile & echo %errorlevel%";
				process = Runtime.getRuntime().exec(command);
			} else {
				String[] commands = new String[]{"sh", "-c","cd repo; mvn compile; echo $?"};
				process = Runtime.getRuntime().exec(commands);
			}

			result = new BufferedReader(new InputStreamReader(process.getInputStream()))
					.lines().collect(Collectors.joining("\n"));

			System.out.println(result);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String testRepo(){
		String result = "";
		try {
			boolean isWindows = isWindows();
			Process process;
			if (isWindows) {
				String command = "cmd.exe /c cd repo/ & mvn test -q & echo %errorlevel%";
				process = Runtime.getRuntime().exec(command);
			} else {
				String[] commands = new String[]{"sh", "-c","cd repo; mvn test -q; echo $?"};
				process = Runtime.getRuntime().exec(commands);
			}

			result = new BufferedReader(new InputStreamReader(process.getInputStream()))
					.lines().collect(Collectors.joining("\n"));

			System.out.println(result);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().startsWith("windows");
	}
	public static void main(String[] args){
		compileProject();
		testRepo();
	}
}
