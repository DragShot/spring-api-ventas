package ds.training.mitocode.ventas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mst_persona")
public class Persona extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_id_persona")
	private Integer idPersona;
	
	@Size(min = 3, max = 48, message = "Los nombres deben tener al menos 3 caracteres y no más de 48")
	@Column(name = "nombres", length = 48)
	private String nombres;
	
	@Size(min = 3, max = 64, message = "Los apellidos deben tener al menos 3 caracteres y no más de 64")
	@Column(name = "apellidos", length = 64)
	private String apellidos;
	
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	@JsonIgnore
	public Integer getId() {
		return idPersona;
	}
}
