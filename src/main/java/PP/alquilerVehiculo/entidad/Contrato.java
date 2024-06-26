package PP.alquilerVehiculo.entidad;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Contrato")
@Entity
public class Contrato extends Base {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaContrato;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_empleado", nullable = false)
    private Empleado empleado;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_reserva", nullable = false)
    private ReservaWeb reserva;

    public Contrato() {
        super();
    }

    public Contrato(Integer id, Date fechaContrato, Empleado empleado, ReservaWeb reserva) {
        super(id);
        this.fechaContrato = fechaContrato;
        this.empleado = empleado;
        this.reserva = reserva;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public ReservaWeb getReserva() {
        return reserva;
    }

    public void setReserva(ReservaWeb reserva) {
        this.reserva = reserva;
    }
}
