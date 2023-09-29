package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vehiculo")//
public class VehiculoControlador {
    @Autowired
    VehiculoServicio vehiculoServicio;
    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/tablav")
    public String listarAutos() throws Exception {
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        return "autos";
    }

    @GetMapping("/new_auto_1")
    public String new_auto_1(@RequestParam Integer id) throws Exception {
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        Empleado empleado = empleadoServicio.findById(id);
        return "registro_vehiculo.html";
    }

    @PostMapping("/alta")
    public String alta_vehiculo(Integer id, Integer ide,
                                @RequestParam String marca, @RequestParam String modelo,
                                @RequestParam String patente, @RequestParam String color,
                                @RequestParam String tipoVehiculo, @RequestParam String cilindradaMotor,
                                @RequestParam String combustible, @RequestParam String typeGama,
                                @RequestParam String precio, @RequestParam String operativo

    ) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        String home = "/empleado/admin/?correo=" + empleado.getMail();
        String titulo1 = "", titulo2 = "", descripcion = "";
        Vehiculo newVehiculo = new Vehiculo();
        newVehiculo.setTipoVehiculo(tipoVehiculo);
        newVehiculo.setMarca(marca);
        newVehiculo.setModelo(modelo);
        newVehiculo.setPatente(patente);
        newVehiculo.setColor(color);
        newVehiculo.setCilindradaMotor(cilindradaMotor);
        newVehiculo.setCombustible(combustible);
        newVehiculo.setTypeGama(typeGama);
        newVehiculo.setPrecio(Double.parseDouble(precio));
        newVehiculo.setOperativo(operativo);
        vehiculoServicio.save(newVehiculo);
        titulo1 = "EXITO!!!!";
        titulo2 = "¡Su gestión fue realizada con éxito !";
        descripcion = "El vehículo fue guardado en la Base de Datos.";
        return "exitoGeneral";
    }

    @GetMapping("/delet_vehiculo")
    public String eliminarVehiculo(Integer idv, Integer ide) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        Vehiculo vehiculo = vehiculoServicio.findById(idv);
        String home = "/empleado/admin/?correo=" + empleado.getMail();
        String titulo1 = "", titulo2 = "", descripcion = "";
        vehiculoServicio.deleteById(idv);
        List<Vehiculo> autos = vehiculoServicio.findAll();
        titulo1 = "EXITO!!!!";
        titulo2 = "¡Su gestión fue realizada con éxito !";
        descripcion = "El vehículo fue eliminado en la Base de Datos.";
        return "exitoGeneral";
    }

    @GetMapping("/edit_vehiculo")
    public String editarVehiculo(Integer idv, Integer ide) throws Exception {
        Vehiculo vehiculo = vehiculoServicio.findById(idv);
        Empleado empleado = empleadoServicio.findById(ide);
        List<Vehiculo> autos = vehiculoServicio.findAll();
        return "editar_vehiculo";
    }

    @PostMapping("/actualizar_vehiculo")
    public String editarVehiculo1(Integer idv, Integer ide,
                                  @RequestParam String marca, @RequestParam String modelo,
                                  @RequestParam String patente, @RequestParam String color,
                                  @RequestParam String tipoVehiculo, @RequestParam String cilindradaMotor,
                                  @RequestParam String combustible, @RequestParam String typeGama,
                                  @RequestParam String precio, @RequestParam String operativo
    ) throws Exception {
        String titulo1 = "", titulo2 = "", descripcion = "";
        Empleado empleado = empleadoServicio.findById(ide);
        String home = "/empleado/admin/?correo=" + empleado.getMail();
        Vehiculo vehiculo = vehiculoServicio.findById(idv);
        vehiculo.setTipoVehiculo(tipoVehiculo);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setPatente(patente);
        vehiculo.setColor(color);
        vehiculo.setCilindradaMotor(cilindradaMotor);
        vehiculo.setCombustible(combustible);
        vehiculo.setTypeGama(typeGama);
        vehiculo.setPrecio(Double.parseDouble(precio));
        vehiculo.setOperativo(operativo);
        vehiculoServicio.save(vehiculo);
        titulo1 = "EXITO!!!!";
        titulo2 = "Su Gestión fue satisfactoria";
        List<Vehiculo> autos = vehiculoServicio.findAll();
        return "exitoGeneral";
    }
}
