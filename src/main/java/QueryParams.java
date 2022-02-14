import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

/**
 * A class to carry the optional parameters to query in the database.
 */
public class QueryParams {
	public Optional<String> commitId;
	public Optional<String> branch;
	public Optional<Timestamp> before;
	public Optional<Timestamp> after;

	public QueryParams(QueryParamsBuilder builder) {
		commitId = builder.commitId;
		branch = builder.branch;
		before = builder.before;
		after = builder.after;
	}

	public String getQueryString() {
		if (isEmpty()) {
			return "";
		}
		ArrayList<String> conditions = new ArrayList<>();
		ArrayList<Object> replacement = new ArrayList<>();
		commitId.ifPresent(s -> { conditions.add(" commitId = ?"); replacement.add(s);});
		branch.ifPresent(s -> { conditions.add(" branch = ?"); replacement.add(s);});
		before.ifPresent(timestamp -> { conditions.add(" timeStamp <= ?"); replacement.add(timestamp);});
		after.ifPresent(timestamp -> { conditions.add(" timeStamp >= ?"); replacement.add(timestamp);});

		String s = " WHERE ";
		for (int i = 0; i < conditions.size(); i++) {
			if (i != 0) {
				s += " AND ";
			}
			s += conditions.get(i);
		}



		return s;
	}

	private boolean isEmpty() {
		return !(commitId.isPresent() || branch.isPresent() || before.isPresent() || after.isPresent());
	}


	/**
	 * Helper class to enable passing only the desired parameters to QueryParams
	 * without needing to write a large number of constructors for it.
	 *
	 * Sample code for generating a QueryParams object for querying for commits
	 * in the branch "abc":
	 * 		QueryParams queryParams = new QueryParams.QueryParamsBuilder().branch("abc").build();
	 */
	public static class QueryParamsBuilder {
		private Optional<String> commitId;
		private Optional<String> branch;
		private Optional<Timestamp> before;
		private Optional<Timestamp> after;

		public QueryParamsBuilder() {
			commitId = Optional.empty();
			branch = Optional.empty();
			before = Optional.empty();
			after = Optional.empty();
		}

		public QueryParamsBuilder commitId(String commitId) {
			this.commitId = Optional.ofNullable(commitId);
			return this;
		}

		public QueryParamsBuilder branch(String branch) {
			this.branch = Optional.ofNullable(branch);
			return this;
		}

		public QueryParamsBuilder before(Timestamp before) {
			this.before = Optional.ofNullable(before);
			return this;
		}

		public QueryParamsBuilder after(Timestamp after) {
			this.after = Optional.ofNullable(after);
			return this;
		}

		public QueryParams build() {
			return new QueryParams(this);
		}
	}
}
