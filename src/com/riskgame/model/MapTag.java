package com.riskgame.model;

public class MapTag {

	private String authorName;
	private String warn;
	private String imageName;
	private String wrap;
	private String scroll;

	public MapTag() {
		super();
	}

	public MapTag(String authorName, String warn, String imageName, String wrap, String scroll) {
		super();
		this.authorName = authorName;
		this.warn = warn;
		this.imageName = imageName;
		this.wrap = wrap;
		this.scroll = scroll;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getWrap() {
		return wrap;
	}

	public void setWrap(String wrap) {
		this.wrap = wrap;
	}

	public String getScroll() {
		return scroll;
	}

	public void setScroll(String scroll) {
		this.scroll = scroll;
	}

	@Override
	public String toString() {
		return "[Map]\nAuthor Name = " + authorName + "\nWarn = " + warn + "\nImage Name = " + imageName + "\nWrap = "
				+ wrap + "\nScroll = " + scroll + "\n";
	}

}