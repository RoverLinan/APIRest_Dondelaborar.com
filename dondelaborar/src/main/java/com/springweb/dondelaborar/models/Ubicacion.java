package com.springweb.dondelaborar.models;
import javax.persistence.*;




@Entity
@Table(name = "ubicacion")
public class Ubicacion {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int id;

	private String pais;
	private String departamento;
	private String provincia;
	private String distrito;

	@Column(name = "codigopostal")
	private int codigoPostal;
	
    private double latitud;
    private double longitud;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	@Override
	public String toString() {
		return "Ubicacion [codigoPostal=" + codigoPostal + ", departamento=" + departamento + ", distrito=" + distrito
				+ ", id=" + id + ", latitud=" + latitud + ", longitud=" + longitud + ", pais=" + pais + ", provincia="
				+ provincia + "]";
	}



	

}
