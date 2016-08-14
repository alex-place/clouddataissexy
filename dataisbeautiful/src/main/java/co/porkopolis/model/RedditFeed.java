package co.porkopolis.model;

import java.util.List;

public class RedditFeed {

	private RedditFeedItem item1;
	private RedditFeedItem item2;
	private RedditFeedItem item3;
	private RedditFeedItem item4;
	private RedditFeedItem item5;

	public RedditFeed() {
	}

	public RedditFeed(List<RedditFeedItem> items) {
		item1 = items.get(0);
		item2 = items.get(1);
		item3 = items.get(2);
		item4 = items.get(3);
		item5 = items.get(4);
	}

	public RedditFeedItem getItem1() {
		return item1;
	}

	public void setItem1(RedditFeedItem item1) {
		this.item1 = item1;
	}

	public RedditFeedItem getItem2() {
		return item2;
	}

	public void setItem2(RedditFeedItem item2) {
		this.item2 = item2;
	}

	public RedditFeedItem getItem3() {
		return item3;
	}

	public void setItem3(RedditFeedItem item3) {
		this.item3 = item3;
	}

	public RedditFeedItem getItem4() {
		return item4;
	}

	public void setItem4(RedditFeedItem item4) {
		this.item4 = item4;
	}

	public RedditFeedItem getItem5() {
		return item5;
	}

	public void setItem5(RedditFeedItem item5) {
		this.item5 = item5;
	}

	public void add(RedditFeedItem item, int index) {
		switch (index) {
		case 0:
			item1 = item;
			break;
		case 1:
			item2 = item;
			break;
		case 2:
			item3 = item;
			break;
		case 3:
			item4 = item;
			break;
		case 4:
			item5 = item;
			break;
		default:
			throw new IndexOutOfBoundsException();
		}

	}

}
