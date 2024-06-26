package PP.alquilerVehiculo.entidad;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reserva")
public class ReservaWeb extends Base {

    private Date fechaRetiro;
    private Date fechaEntrega;
    private Date fechaReserva;
    private String estadoReserva;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "datos_vehiculo_id")
    private Vehiculo datosVehiculo;

    public ReservaWeb() {
    }

    public ReservaWeb(Integer id, Date fechaRetiro, Date fechaEntrega, Date fechaReserva, String estadoReserva, Cliente cliente, Vehiculo datosVehiculo) {
        super(id);
        this.fechaRetiro = fechaRetiro;
        this.fechaEntrega = fechaEntrega;
        this.fechaReserva = fechaReserva;
        this.estadoReserva = estadoReserva;
        this.cliente = cliente;
        this.datosVehiculo = datosVehiculo;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getDatosVehiculo() {
        return datosVehiculo;
    }

    public void setDatosVehiculo(Vehiculo datosVehiculo) {
        this.datosVehiculo = datosVehiculo;
    }
}

