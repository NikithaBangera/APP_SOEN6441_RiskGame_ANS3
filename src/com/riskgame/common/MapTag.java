package com.riskgame.common;

import java.util.ArrayList;

public class MapTag {

	public ArrayList<String> mapTagData = new ArrayList<String>();

	public ArrayList<String> getMapTagData() {
		return mapTagData;
	}

	public void setMapTagData(ArrayList<String> mapTagData) {
		this.mapTagData = mapTagData;
	}

	@Override
	public String toString() {
		return "MapTag [mapTagData=" + mapTagData + "]";
	}
	
	
}
