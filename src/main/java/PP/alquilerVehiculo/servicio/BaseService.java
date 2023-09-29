package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Vehiculo;

import java.util.List;

public interface BaseService<E> {
    public List<E> findAll() throws Exception;

    public E findById(Integer id) throws Exception;

    public E save(E entity) throws Exception;

    public E update(Integer id, E entity) throws Exception;

    public void deleteById(Integer id) throws Exception;
}