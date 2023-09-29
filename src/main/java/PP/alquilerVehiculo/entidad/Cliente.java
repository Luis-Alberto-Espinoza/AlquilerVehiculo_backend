package PP.alquilerVehiculo.entidad;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name = "Cliente")
@Entity
public class Cliente extends Persona {
    private Date alta;
    private Date baja;

    @OneToMany(mappedBy = "cliente")
    private List<ReservaWeb> reserva;

    public Cliente() {
        super();
    }

    public Cliente(Date alta, Date baja, String mail, String nombre, String apellido,
                   String direccion, Date fechaNacimiento, long dni, long telefono, String clave1) {
        super(mail, nombre, apellido, direccion, fechaNacimiento, dni, telefono, clave1);
        this.alta = alta;
        this.baja = baja;
        this.reserva = reserva;
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
}
