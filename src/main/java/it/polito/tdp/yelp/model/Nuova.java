package it.polito.tdp.yelp.model;

public class Nuova {

	private String b;
	private Double peso;
	public Nuova(String b, Double peso) {
		super();
		this.b = b;
		this.peso = peso;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Nuova [b=" + b + ", peso=" + peso + "]";
	}
	
}
