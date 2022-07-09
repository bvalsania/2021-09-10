package it.polito.tdp.yelp.model;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Coppia {
	
	private Business b1;
	private Business b2;
	private double lat1;
	private double lng1;
	private double lat2;
	private double lng2;
	private double peso;
	private LatLng l1;
	private LatLng l2;
	


	public Coppia(Business b1, Business b2) {
		super();
		this.b1 = b1;
		this.b2 = b2;
		
		
	}

	public Business getB1() {
		return b1;
	}

	public void setB1(Business b1) {
		this.b1 = b1;
	}

	public Business getB2() {
		return b2;
	}

	public void setB2(Business b2) {
		this.b2 = b2;
	}

	public double getLat1() {
		return lat1;
	}

	public void setLat1(double lat1) {
		this.lat1 = lat1;
	}

	public double getLng1() {
		return lng1;
	}

	public void setLng1(double lng1) {
		this.lng1 = lng1;
	}

	public double getLat2() {
		return lat2;
	}

	public void setLat2(double lat2) {
		this.lat2 = lat2;
	}

	public double getLng2() {
		return lng2;
	}

	public void setLng2(double lng2) {
		this.lng2 = lng2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public LatLng getL1() {
		return l1;
	}

	public void setL1(LatLng l1) {
		this.l1 = l1;
	}

	public LatLng getL2() {
		return l2;
	}

	public void setL2(LatLng l2) {
		this.l2 = l2;
	}

	@Override
	public String toString() {
		return "Coppia [b1=" + b1 + ",\n b2=" + b2 + ", peso=" + peso ;
	}
	
	public void calcolaPeso() {
		l1 = new LatLng(b1.getLatitude(),b1.getLongitude());
		l2 = new LatLng(b2.getLatitude(),b2.getLongitude());
		peso = LatLngTool.distance(l1, l2, LengthUnit.KILOMETER);
	}

}
