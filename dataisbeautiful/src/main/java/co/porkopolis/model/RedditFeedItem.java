package co.porkopolis.model;

public class RedditFeedItem {

	private int id;
	
	private String url;
	
	private String title;
	
	private String imgUrl;
	
	public RedditFeedItem() {
	}
	
	public RedditFeedItem(int id, String url, String title, String imgUrl) {
		this.id = id;
		this.url = url;
		this.title = title;
		this.imgUrl = imgUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
	
}
