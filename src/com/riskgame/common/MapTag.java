package com.riskgame.common;

public class MapTag {
	String image;
	String wrap;
	String scroll;
	String author;
	String warn;

	public String getimage() {
		return image;
	}

	public void setimage(String image) {
		this.image = image;
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

	@Override
	public String toString() {
		return "Map [image=" + image + ", wrap=" + wrap + ", scroll=" + scroll + ", author=" + author
				+ ", warn=" + warn + "]";
	}

	public MapTag(String image, String wrap, String scroll, String author, String warn) {
		super();
		this.image = image;
		this.wrap = wrap;
		this.scroll = scroll;
		this.author = author;
		this.warn = warn;
	}

	public void setScroll(String scroll) {
		this.scroll = scroll;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

}
