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
@Table(name = "mst_producto")
public class Producto extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_id_producto")
	private Integer idProducto;
	
	@Size(min = 3, max = 96, message = "El nombre debe tener al menos 3 caracteres y no más de 96")
	@Column(name = "nombre", length = 96)
	private String nombre;
	
	@Size(min = 3, max = 64, message = "La marca debe tener al menos 3 caracteres y no más de 64")
	@Column(name = "marca", length = 64)
	private String marca;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	@JsonIgnore
	public Integer getId() {
		return idProducto;
	}
}
