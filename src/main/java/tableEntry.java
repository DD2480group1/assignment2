import java.sql.Timestamp;

public class tableEntry {

	String commitId;
	String branch;
	Timestamp timeStamp;
	String buildInfo;
	String testInfo;

	public tableEntry(String commitId, String branch, Timestamp timestamp, String buildInfo, String testInfo) {
		this.commitId = commitId;
		this.branch = branch;
		this.timeStamp = timestamp;
		this.buildInfo = buildInfo;
		this.testInfo = testInfo;
	}

	@Override
	public String toString() {
		return  String.format("commitId: %s, branch: %s, timeStamp: %s, buildInfo: %s, : testInfo: %s",
				commitId, branch, timeStamp, buildInfo, testInfo);
	}
}
