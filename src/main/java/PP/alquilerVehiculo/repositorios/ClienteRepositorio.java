package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    @Query("SELECT c FROM Cliente c WHERE c.mail = :mail")
    public Cliente buscarPorMail(@Param("mail") String mail);

    @Query("SELECT c FROM Cliente c WHERE c.dni = :dni")
    public Cliente buscarPorDNI(@Param("dni") Integer dni);

    @Query("SELECT COUNT(c.mail) FROM Cliente c WHERE c.mail = :mail")
    public String existeCliente(@Param("mail") String mail);

    @Query("SELECT COUNT(c.dni) FROM Cliente c WHERE c.dni = :dni")
    public String existeClienteXdni(@Param("dni") long dni);

    @Query("SELECT COUNT(c.clave1) FROM Cliente c WHERE c.clave1 = :clave")
    public String validaPass(@Param("clave") String clave);
}
