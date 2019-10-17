package ds.training.mitocode.ventas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.training.mitocode.ventas.model.Persona;
import ds.training.mitocode.ventas.repo.IPersonaRepo;
import ds.training.mitocode.ventas.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {
	@Autowired
	private IPersonaRepo repo;

	@Override
	public Persona registrar(Persona pers) {
		return repo.save(pers);
	}

	@Override
	public Persona actualizar(Persona pers) {
		return repo.save(pers);
	}

	@Override
	public List<Persona> listar() {
		return repo.findAll();
	}

	@Override
	public Persona obtener(Integer id) {
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
