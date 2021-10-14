package com.springweb.dondelaborar.models;

import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "postulaciones")
public class Postulacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	@Column(columnDefinition = "LONGTEXT")
	private String comentario;

	private boolean estado;

	//relaciones

	@ManyToOne
	@JoinColumn(name = "empleado_id", referencedColumnName = "id")
	private Empleado empleado;

	
	@ManyToOne
	@JoinColumn(name = "convocatoria_id", referencedColumnName = "id")
	private Convocatoria convocatoria;

	

	@PrePersist
	public void prePersisten(){
		fecha = new Date();
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Empleado getEmpleado() {
		return empleado;
	}

	
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Convocatoria getConvocatoria() {
		return convocatoria;
	}
	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
	}
	
}
