import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Tries to build the cloned project. This class assumes that the project to
 * be located in the root of this project and lie in a directory called "repo".
 * This class only supports building maven projects and no other type of projects.
 */
public class compile {
	/**
	 * Tries to compile the repo under the directory "repo/" located at the root
	 * of this project.
	 * @return the maven build messages followed by a new line with a number
	 * where 0 indicates success and any other number indicates failure. Or if
	 * an exception was thrown an empty string is returned.
	 */
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

	/**
	 * Tries to runt the tests in the repo under the directory "repo/" located at
	 * the root of this project.
	 * @return the maven test messages followed by a new line with a number
	 * where 0 indicates success and any other number indicates failure. Or if
	 * an exception was thrown an empty string is returned.
	 */
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
