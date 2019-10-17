package ds.training.mitocode.ventas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.training.mitocode.ventas.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {
	
}
