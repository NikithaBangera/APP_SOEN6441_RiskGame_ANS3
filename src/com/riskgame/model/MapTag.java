package com.riskgame.model;

/**
 * This class stores all the Map tag data.It stores Author name, Image name,
 * Scroll, Warn and Wrap options for the Map.
 * 
 * @author Sumeetha
 * @author Shiva
 *
 */
public class MapTag {

	/**Name of the author for the map.*/
	private String authorName;
	
	/** Warn option for the map. */
	private String warn;
	
	/** Stores the name of the Image. */
	private String imageName;
	
	/** Wrap option for the map.*/
	private String wrap;
	
	/** Scroll option for the map.*/
	private String scroll;

	/**
	 * MapTag class constructor
	 */
	public MapTag() {
		super();
	}

	/**
	 * MapTag class constructor
	 * 
	 * @param Author name
	 * @param Warn
	 * @param Image name
	 * @param Wrap
	 * @param Scroll
	 *            
	 */
	public MapTag(String authorName, String warn, String imageName, String wrap, String scroll) {
		super();
		this.authorName = authorName;
		this.warn = warn;
		this.imageName = imageName;
		this.wrap = wrap;
		this.scroll = scroll;
	}

	/**
	 * Gets name of an Author 
	 *
	 * @return Author name
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Sets Author name for the map 
	 *
	 * @param authorName
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Gets Warn option for the map 
	 *
	 * @return Warn option
	 */
	public String getWarn() {
		return warn;
	}

	/**
	 * Sets Warn option for the map 
	 *
	 * @param warn
	 */
	public void setWarn(String warn) {
		this.warn = warn;
	}

	/**
	 * Gets name of the image 
	 *
	 * @return Image name
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * Sets name of the image 
	 *
	 * @param imageName
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * Gets Wrap option for the map
	 *
	 * @return Wrap option
	 */
	public String getWrap() {
		return wrap;
	}

	/**
	 * Sets Wrap option for the map
	 *
	 * @param wrap
	 */
	public void setWrap(String wrap) {
		this.wrap = wrap;
	}

	/**
	 * Gets scroll option of the map
	 *
	 * @return Scroll option
	 */
	public String getScroll() {
		return scroll;
	}

	/**
	 * Sets scroll option of the map 
	 *
	 * @param scroll
	 */
	public void setScroll(String scroll) {
		this.scroll = scroll;
	}

	@Override
	public String toString() {
		return "[Map]\nAuthor Name = " + authorName + "\nWarn = " + warn + "\nImage Name = " + imageName + "\nWrap = "
				+ wrap + "\nScroll = " + scroll + "\n";
	}
}