package com.springweb.dondelaborar.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@NotEmpty(message = "Su EMAIL es obligatorio")
	private String correo;
	@NotEmpty(message = "Su PASSWORD es obligatorio")
	private String password;
	@Column(name = "fecharegistro")

	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	private int rol;
	private String urlFoto;




	//prePersist
	@PrePersist
	public void prePersist(){
		fechaRegistro = new Date();
	}







	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}







	@Override
	public String toString() {
		return "Usuario [correo=" + correo + ", fechaRegistro=" + fechaRegistro + ", id=" + id + ", password="
				+ password + ", rol=" + rol + "]";
	}

	
	
}
