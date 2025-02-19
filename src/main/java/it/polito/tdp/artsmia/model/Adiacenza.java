package it.polito.tdp.artsmia.model;

public class Adiacenza implements Comparable<Adiacenza>{
	
	private Integer a1;
	private Integer a2;
	private Double peso;
	public Adiacenza(Integer a1, Integer a2, Double peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	}
	public Integer getA1() {
		return a1;
	}
	public void setA1(Integer a1) {
		this.a1 = a1;
	}
	public Integer getA2() {
		return a2;
	}
	public void setA2(Integer a2) {
		this.a2 = a2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Adiacenza o) {
		return -Double.compare(this.peso, o.peso);
	}
	@Override
	public String toString() {
		return "[a1=" + a1 + ", a2=" + a2 + ", peso=" + peso + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
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
		Adiacenza other = (Adiacenza) obj;
		if (a1 == null) {
			if (other.a1 != null)
				return false;
		} else if (!a1.equals(other.a1))
			return false;
		if (a2 == null) {
			if (other.a2 != null)
				return false;
		} else if (!a2.equals(other.a2))
			return false;
		return true;
	}
	
	

}
