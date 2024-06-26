package PP.alquilerVehiculo.entidad;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Base() {
    }

    public Base(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
