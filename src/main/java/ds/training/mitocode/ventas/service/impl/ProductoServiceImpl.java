package ds.training.mitocode.ventas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.training.mitocode.ventas.model.Producto;
import ds.training.mitocode.ventas.repo.IProductoRepo;
import ds.training.mitocode.ventas.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {
	@Autowired
	private IProductoRepo repo;

	@Override
	public Producto registrar(Producto prod) {
		return repo.save(prod);
	}

	@Override
	public Producto actualizar(Producto prod) {
		return repo.save(prod);
	}

	@Override
	public List<Producto> listar() {
		return repo.findAll();
	}

	@Override
	public Producto obtener(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public boolean eliminar(Integer id) {
		boolean done = true;
		try {
			repo.deleteById(id);
		} catch (Exception ex) {
			done = false;
		}
		return done;
	}
}
