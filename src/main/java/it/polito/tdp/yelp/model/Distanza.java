package it.polito.tdp.yelp.model;

import com.javadocmd.simplelatlng.LatLng;

public class Distanza {

	private Business b;
	private LatLng distanza;
	public Distanza(Business b, LatLng distanza) {
		super();
		this.b = b;
		this.distanza = distanza;
	}
	public Business getB() {
		return b;
	}
	public void setB(Business b) {
		this.b = b;
	}
	public LatLng getDistanza() {
		return distanza;
	}
	public void setDistanza(LatLng distanza) {
		this.distanza = distanza;
	}
	@Override
	public String toString() {
		return "Distanza [b=" + b + ", distanza=" + distanza + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
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
		Distanza other = (Distanza) obj;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}
	
	
}
