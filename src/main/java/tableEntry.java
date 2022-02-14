import java.sql.Timestamp;

public class tableEntry {

	String commitId;
	String branch;
	Timestamp timeStamp;
	String description;

	public tableEntry(String commitId, String branch, Timestamp timestamp, String description) {
		this.commitId = commitId;
		this.branch = branch;
		this.timeStamp = timestamp;
		this.description = description;
	}

	@Override
	public String toString() {
		return  String.format("commitId: %s, branch: %s, timeStamp: %s, description: %s",
				commitId, branch, timeStamp, description);
	}
}
