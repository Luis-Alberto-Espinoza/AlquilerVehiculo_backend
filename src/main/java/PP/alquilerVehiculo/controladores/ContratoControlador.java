package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.*;
import PP.alquilerVehiculo.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "/contrato")
@Controller
public class ContratoControlador {
    @Autowired
    ReservaServicio reservaServicio;
    @Autowired
    VehiculoServicio vehiculoServicio;
    @Autowired
    ContratoServicio contratoServicio;
    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/")
    public String index_contrato(@RequestParam long ide) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        return "contrato_index";
    }

    @GetMapping("/generar_contrato")
    public String generar_contrato(@RequestParam long idres, @RequestParam long ide) throws Exception {
        /*
         * verificar que la reserva no se a convertido en contrato
         * verificar que la reserva no sea vieja
         */
        if (contratoServicio.validarReserva(idres)) {
            Empleado empleado = empleadoServicio.findById(ide);
            ReservaWeb reservaWeb = reservaServicio.findById(idres);
            System.out.println(reservaWeb.getId());
            Cliente cliente = reservaWeb.getCliente();
            Vehiculo auto = reservaWeb.getDatosVehiculo();
            Double precioTotal = vehiculoServicio.costoTotal
                    (String.valueOf(reservaWeb.getFechaRetiro()), String.valueOf(reservaWeb.getFechaEntrega()), auto.getId());
            return "genera_contrato";
        } else {
            return "index";
        }
    }

    @PostMapping("/confi_contrato")
    public String reserva(@RequestParam Long id_reserva, @RequestParam Long ide) throws Exception {
        ReservaWeb reserva = reservaServicio.findById(id_reserva);
        Empleado empleado = empleadoServicio.findById(ide);
        List<Date> listadoFechas = new ArrayList<>();
        Date fechaRegistrp = new Date();
        String titulo1 = "", titulo2 = "", descripcion = "";

        contratoServicio.guardarContrato(reserva, empleado);
        titulo1 = "EXITO!!!!";
        titulo2 = "Su Contrato fue generado";
        descripcion = "Tome Nota del número de Contrato";
        Vehiculo auto = reserva.getDatosVehiculo();
        Cliente cliente = reserva.getCliente();
        Contrato contrato = contratoServicio.contratoXidReserva(reserva.getId());
        String home = "/empleado/ventas/?correo=" + empleado.getMail();
        return "exitoGeneral";
    }
}
