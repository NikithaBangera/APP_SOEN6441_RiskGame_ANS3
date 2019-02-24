package com.riskgame.shared;

public class Map {
	String imageLocation;
	String wrap;
	String scroll;
	String author;
	String warn;

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
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
		return "Map [imageLocation=" + imageLocation + ", wrap=" + wrap + ", scroll=" + scroll + ", author=" + author
				+ ", warn=" + warn + "]";
	}

	public Map(String imageLocation, String wrap, String scroll, String author, String warn) {
		super();
		this.imageLocation = imageLocation;
		this.wrap = wrap;
		this.scroll = scroll;
		this.author = author;
		this.warn = warn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((imageLocation == null) ? 0 : imageLocation.hashCode());
		result = prime * result + ((scroll == null) ? 0 : scroll.hashCode());
		result = prime * result + ((warn == null) ? 0 : warn.hashCode());
		result = prime * result + ((wrap == null) ? 0 : wrap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Map other = (Map) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (imageLocation == null) {
			if (other.imageLocation != null)
				return false;
		} else if (!imageLocation.equals(other.imageLocation))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (warn == null) {
			if (other.warn != null)
				return false;
		} else if (!warn.equals(other.warn))
			return false;
		if (wrap == null) {
			if (other.wrap != null)
				return false;
		} else if (!wrap.equals(other.wrap))
			return false;
		return true;
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
