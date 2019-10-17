package ds.training.mitocode.ventas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.training.mitocode.ventas.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer> {
	
}
