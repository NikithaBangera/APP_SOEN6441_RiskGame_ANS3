package com.riskgame.common;

import java.util.HashMap;

public class Continent {
	String name;
	int control_value;
	HashMap<String, Integer> continents = new HashMap<String, Integer>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getControl_value() {
		return control_value;
	}

	public void setControl_value(int control_value) {
		this.control_value = control_value;
	}

	public HashMap<String, Integer> getContinents() {
		return continents;
	}

	public void setContinents(HashMap<String, Integer> continents) {
		this.continents = continents;
	}

	@Override
	public String toString() {
		return "Continent [name=" + name + ", control_value=" + control_value + ", continents=" + continents + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((continents == null) ? 0 : continents.hashCode());
		result = prime * result + control_value;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Continent other = (Continent) obj;
		if (continents == null) {
			if (other.continents != null)
				return false;
		} else if (!continents.equals(other.continents))
			return false;
		if (control_value != other.control_value)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Continent(String name, int control_value, HashMap<String, Integer> continents) {
		super();
		this.name = name;
		this.control_value = control_value;
		this.continents = continents;
	}

}
