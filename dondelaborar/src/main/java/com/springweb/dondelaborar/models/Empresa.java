package com.springweb.dondelaborar.models;


import java.util.Collection;
import java.util.Date;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@NotNull(message = "Este campo es obligatorio")
	private String razonSocial;
	@NotNull(message = "Este campo es obligatorio")
	private String ruc;
	@NotEmpty(message = "Este campo es obligatorio")
	private int cantidadEmpleados;
	@NotEmpty(message = "Este campo es obligatorio")
	private String rubro;
	@NotEmpty(message = "Este campo es obligatorio")
	private String telefono;
	@NotEmpty(message = "Este campo es obligatorio")
	private String direccion;




	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
	private Ubicacion ubicacion;


	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechacreacion;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public int getCantidadEmpleados() {
		return cantidadEmpleados;
	}
	public void setCantidadEmpleados(int cantidadEmpleados) {
		this.cantidadEmpleados = cantidadEmpleados;
	}
	public String getRubro() {
		return rubro;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	@Override
	public String toString() {
		return "Empresa [cantidadEmpleados=" + cantidadEmpleados + ", convocatorias=" + ", direccion="
				+ direccion + ", fechacreacion=" + fechacreacion + ", id=" + id + ", razonSocial=" + razonSocial
				+ ", rubro=" + rubro + ", ruc=" + ruc + ", telefono=" + telefono 
				+ ", usuario=" + usuario + "]";
	}

	

	
}
