package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.ReservaWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<ReservaWeb, Integer> {

    @Query("SELECT m FROM ReservaWeb m WHERE m.cliente.id = :id ")
    public List<ReservaWeb> buscarReservasPorUsuario(@Param("id") String id);

    @Query("SELECT r FROM ReservaWeb r  WHERE id = :id")
    public ReservaWeb reservaxid(@Param("id") Integer id);

    @Query("SELECT r FROM ReservaWeb r  WHERE fechaReserva = :fechaReserva")
    public ReservaWeb reservaxfechaRegistro(@Param("fechaReserva") Date fechaReserva);

    @Query("SELECT l FROM ReservaWeb l WHERE l.cliente.id = :id")
    public List<ReservaWeb> listaVehiculoXcliente(@Param("id") Integer id);

    @Query("SELECT MAX(r) FROM ReservaWeb r WHERE r.cliente.id = :id")
    public ReservaWeb ultimaReserva(@Param("id") Integer id);// funciona

    @Query("SELECT r FROM ReservaWeb r WHERE r.fechaEntrega < :fechaRetiro AND r.fechaRetiro > :fechaEntrega")
    public List<ReservaWeb> vehiculoDisponiblesLista(@Param("fechaRetiro") LocalDate fRetiro, @Param("fechaEntrega") LocalDate fDevolcion);

    /////metodo 1
    @Query("SELECT r FROM ReservaWeb r WHERE r.fechaEntrega >= :checkin AND r.fechaEntrega <= :checkout AND r.datosVehiculo.id = :id")
    public List<ReservaWeb> metodo1(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion, @Param("id") Integer id);

    /////2
    @Query("SELECT r FROM ReservaWeb r WHERE r.fechaRetiro >= :checkin AND r.fechaRetiro <= :checkout  AND r.datosVehiculo.id = :id")
    public List<ReservaWeb> metodo2(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion, @Param("id") Integer id);

    /////3
    @Query("SELECT r FROM ReservaWeb r WHERE r.fechaRetiro <= :checkin AND r.fechaEntrega >= :checkout  AND r.datosVehiculo.id = :id")
    public List<ReservaWeb> metodo3(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion, @Param("id") Integer id);

    @Query("SELECT r FROM ReservaWeb r WHERE NOT (r.fechaEntrega >= :checkin AND r.fechaEntrega <= :checkout AND r.fechaRetiro >= :checkin AND r.fechaRetiro <= :checkout AND r.fechaRetiro <= :checkin AND r.fechaEntrega >= :checkout  AND  r.datosVehiculo.id = :id)")
    public List<ReservaWeb> metodoCombinado(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion, @Param("id") Integer id);

    //// x id vehiculo
    @Query("SELECT r FROM ReservaWeb r WHERE r.datosVehiculo.id = :id")
    public List<ReservaWeb> xIdVehiculo(@Param("id") Integer id);
}
