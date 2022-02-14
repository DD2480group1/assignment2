public class tableEntry {
	String commitId;
	String branch;
	String timeStamp;
	String description;

	public tableEntry(String commitId, String branch, String commitDate, String description) {
		this.commitId = commitId;
		this.branch = branch;
		this.timeStamp = commitDate;
		this.description = description;
	}
}
