package com.riskgame.common;

public class Territory {
	String territoryName;

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((territoryName == null) ? 0 : territoryName.hashCode());
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
		Territory other = (Territory) obj;
		if (territoryName == null) {
			if (other.territoryName != null)
				return false;
		} else if (!territoryName.equals(other.territoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Territory [territoryName=" + territoryName + "]";
	}
	
}