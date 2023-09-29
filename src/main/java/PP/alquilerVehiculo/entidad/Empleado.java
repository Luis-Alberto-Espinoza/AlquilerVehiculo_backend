package PP.alquilerVehiculo.entidad;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Empleado")
public class Empleado extends Persona {
    private Date alta;
    private Date baja;
    private String typeEmpleado;
    @OneToMany(mappedBy = "empleado")
    private List<Contrato> contrato;

    public Empleado() {
    }

    public Empleado(Date alta, Date baja, String typeEmpleado) {
        this.alta = alta;
        this.baja = baja;
        this.typeEmpleado = typeEmpleado;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getBaja() {
        return baja;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

    public String getTypeEmpleado() {
        return typeEmpleado;
    }

    public void setTypeEmpleado(String typeEmpleado) {
        this.typeEmpleado = typeEmpleado;
    }
}
