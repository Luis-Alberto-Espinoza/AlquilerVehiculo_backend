
package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.ReservaServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/reserva")
public class ReservaControlador {
    @Autowired
    ReservaServicio reservaServicio;
    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    VehiculoServicio vehiculoServicio;
    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/")
    public String soloReserva(@RequestParam Integer ide) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        return "reserva_de_empleado";
    }

    @GetMapping("/generar_reserva")
    public String generar_reserva(@RequestParam Integer idv,
                                  @RequestParam Integer idc,
                                  String fecha1,
                                  String fecha2) throws Exception {
        Vehiculo auto = vehiculoServicio.findById(idv);
        Cliente cliente = clienteServicio.findById(idc);
        Double precioTotal = vehiculoServicio.costoTotal(fecha1, fecha2, idv);
        return "reserva";
    }

    @GetMapping("/generar_reserva_empleado")
    public String generar_reserva_empleado(@RequestParam Integer idv, @RequestParam Integer idc,
                                           @RequestParam String fRetiro,
                                           @RequestParam String fDevolucion,
                                           @RequestParam Integer ide) throws Exception {
        Vehiculo auto = vehiculoServicio.findById(idv);
        Cliente cliente = clienteServicio.findById(idc);
        Empleado empleado = empleadoServicio.findById(ide);
        Double precioTotal = vehiculoServicio.costoTotal(fRetiro, fDevolucion, idv);
        return "reserva_empleado";
    }

    @GetMapping("/resEmp")
    public String listarAutosReserva(@RequestParam Integer dni, @RequestParam Integer ide,
                                     @RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fRetiro,
                                     @RequestParam(value = "fDevolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fDevolucion
    ) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        List<Vehiculo> listaAutos = vehiculoServicio.autosDisponiblesXfechas(fRetiro, fDevolucion);
        Cliente cliente = clienteServicio.buscarXdni(dni);
        return "autos_reserva";
    }

    @PostMapping("/confi_reserva")
    public String reserva(@RequestParam Integer idv, @RequestParam Integer idc,
                          @RequestParam("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fRetiro,
                          @RequestParam(value = "fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fDevolucion
    ) throws Exception {

        Cliente cliente = clienteServicio.findById(idc);
        Vehiculo auto = vehiculoServicio.findById(idv);
        //se genera la fecha actual para dejar asentado la fecha de gestion de la reserva
        Date fechaActual = new Date();
        Date fechaRegistro = fechaActual;
        //se crea una lista para pasar mas rapido todas las fechas
        List<Date> listadoFechas = new ArrayList<>();
        listadoFechas.add(fDevolucion);
        listadoFechas.add(fRetiro);
        listadoFechas.add(fechaRegistro);
        String titulo1 = "", titulo2 = "", descripcion = "";

        try {
            reservaServicio.guardarReserva(cliente, auto, listadoFechas);
            titulo1 = "EXITO!!!!";
            titulo2 = "Su Reserva fue generada";
            descripcion = "Tome Nota del número de reserva";
        } catch (Exception e) {
            titulo1 = "ERROR!!!!";
            titulo2 = "Su Reserva  NO fue generada";
            descripcion = "Realice nuevamente su reserva";
            throw new RuntimeException(e);
        }
        //con el id del cliente se busca la reserva recien guardada para poder pasarla al modelo
        ReservaWeb reservaWeb = reservaServicio.ultimaReserva(cliente);
        String home = "/cliente/?correo=" + cliente.getMail();
        return "/exitoGeneral";
    }

    @PostMapping("/confi_reserva_empleado")
    public String reserva_empleado(@RequestParam Integer idv, @RequestParam Integer idc, @RequestParam Integer ide,
                                   @RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fRetiro,
                                   @RequestParam(value = "fDevolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fDevolucion
    ) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        Cliente cliente = clienteServicio.findById(idc);
        Vehiculo auto = vehiculoServicio.findById(idv);
        //se genera la fecha actual para dejar asentado la fecha de gestion de la reserva
        Date fechaActual = new Date();
        Date fechaRegistro = fechaActual;
        //se crea una lista para pasar mas rapido todas las fechas
        List<Date> listadoFechas = new ArrayList<>();
        listadoFechas.add(fDevolucion);
        listadoFechas.add(fRetiro);
        listadoFechas.add(fechaRegistro);
        String titulo1 = "", titulo2 = "", descripcion = "";
        try {
            reservaServicio.guardarReserva(cliente, auto, listadoFechas);
            titulo1 = "EXITO!!!!";
            titulo2 = "Su Reserva fue generada";
            descripcion = "Tome Nota del número de reserva";
        } catch (Exception e) {
            titulo1 = "ERROR!!!!";
            titulo2 = "Su Reserva  NO fue generada";
            descripcion = "Realice nuevamente su reserva";
            throw new RuntimeException(e);
        }
        //con el id del cliente se busca la reserva recien guardada para poder pasarla al modelo
        ReservaWeb reservaWeb = reservaServicio.ultimaReserva(cliente);
        String home = "/empleado/ventas/?correo=" + empleado.getMail();
        return "/exitoGeneral";
    }

    @GetMapping("/mis_reservas")
    public String historial(@RequestParam Integer idc) throws Exception {
        Cliente cliente = clienteServicio.findById(idc);
        return "/cliente/";
    }

    @GetMapping("/hreservas")
    public String h_reserva(@RequestParam Integer id) {
        Cliente cliente = clienteServicio.buscarPorId(id);
        List<ReservaWeb> autosReservados = reservaServicio.lDeAutosR(cliente);
        return "/hitorial_reserva_cliente";
    }

    @GetMapping("/edit_reserva")
    public String editarReserva(Integer id_reserva) throws Exception {
        ReservaWeb reservaWeb = reservaServicio.findById(id_reserva);
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        Cliente cliente = clienteServicio.buscarXdni(reservaWeb.getId());
        return "reserva";
    }

    @GetMapping("/delet_reserva")
    public String eliminarReserva(Integer id_reserva) throws Exception {
        ReservaWeb reservaWeb = reservaServicio.findById(id_reserva);
        Cliente cliente = clienteServicio.buscarXdni(id_reserva);
        reservaServicio.deleteById(id_reserva);
        List<ReservaWeb> autosReservados = reservaServicio.lDeAutosR(cliente);
        return "/hitorial_reserva_cliente";
    }

    @GetMapping("/recibir_fecha")
    public String recibir_fecha(@RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fRetiro,
                                @RequestParam(value = "fDevolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fDevolucion,
                                Integer idc) throws Exception {
        if (fRetiro.compareTo(LocalDate.now()) >= 0) {
            Cliente cliente = clienteServicio.findById(idc);
            List<Vehiculo> vehiculosDisponibles = vehiculoServicio.autosDisponiblesXfechas(fRetiro, fDevolucion);
            return "autos_disponibles";
        } else {
            return "index_cliente";
        }
    }
}
