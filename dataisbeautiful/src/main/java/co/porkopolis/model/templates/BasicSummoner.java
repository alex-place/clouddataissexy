package co.porkopolis.model.templates;

public class BasicSummoner {
	
	public int id;
	
	public String name;
	
	public int profileIconId;
	
	public int summonerLevel;
	
	public long revisionDate;
	
	public BasicSummoner() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfileIconId() {
		return profileIconId;
	}

	public void setProfileIconId(int profileIconId) {
		this.profileIconId = profileIconId;
	}

	public int getSummonerLevel() {
		return summonerLevel;
	}

	public void setSummonerLevel(int summonerLevel) {
		this.summonerLevel = summonerLevel;
	}

	public long getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(long revisionDate) {
		this.revisionDate = revisionDate;
	}
	
	

}
