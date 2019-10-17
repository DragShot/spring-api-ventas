package ds.training.mitocode.ventas.service;

import java.util.List;

public interface ICRUD<T> {
	public T registrar(T obj);
	public T actualizar(T obj);
	public List<T> listar();
	public T obtener(Integer id);
	public boolean eliminar(Integer id);
}
