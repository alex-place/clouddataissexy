package co.porkopolis.model;

public class RankSummary {

	private long id;
	private String name;
	private int wins;
	private int losses;
	private String division;
	private int leaguePoints;
	private String rank;

	public RankSummary() {
	}

	public RankSummary(long id, String name, int wins, int losses, String division, int leaguePoints, String rank) {
		this.id = id;
		this.name = name;
		this.wins = wins;
		this.losses = losses;
		this.division = division;
		this.leaguePoints = leaguePoints;
		this.rank = rank;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int loses) {
		this.losses = loses;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public int getLeaguePoints() {
		return leaguePoints;
	}

	public void setLeaguePoints(int leaguePoints) {
		this.leaguePoints = leaguePoints;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}
