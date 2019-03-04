package com.riskgame.model;

/**
 * This class stores all the Map tag data.It stores Author name, Image name,
 * Scroll, Warn and Wrap options for the Map.
 * 
 * @author 
 * @author 
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
	 * @param Author name
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Gets Warn option for the map 
	 *
	 * @return Warn option as string 
	 */
	public String getWarn() {
		return warn;
	}

	/**
	 * Sets Warn option for the map 
	 *
	 * @param Warn option
	 */
	public void setWarn(String warn) {
		this.warn = warn;
	}

	/**
	 * Gets name of the Image 
	 *
	 * @return Image name as String
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * Sets name of the Image 
	 *
	 * @param Image name
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * Gets Wrap option for the map 
	 *
	 * @return Wrap option as string
	 */
	public String getWrap() {
		return wrap;
	}

	/**
	 * Sets Wrap option for the map 
	 *
	 * @param Wrap option
	 */
	public void setWrap(String wrap) {
		this.wrap = wrap;
	}

	/**
	 * Gets Scroll option for the map 
	 *
	 * @return Scroll option as string
	 */
	public String getScroll() {
		return scroll;
	}

	/**
	 * Sets Scroll option for the map 
	 *
	 * @param Scroll option
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
