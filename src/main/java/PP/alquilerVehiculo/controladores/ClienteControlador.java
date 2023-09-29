package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.excepciones.ClienteServiceException;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteControlador {
    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private VehiculoServicio vehiculoServicio;
    @Autowired
    private EmpleadoServicio empleadoServicio;


    @GetMapping("/")
    public String clienteInicio() throws Exception {
//        Cliente usuario = clienteServicio.buscarXcorreo(correo);
//        List<Vehiculo> listVehiculos = vehiculoServicio.findAll();
        return "index_cliente";
    }

    @GetMapping("/editar-perfil")
    public String editarPerfil(@RequestParam Long id) {
        Cliente clienteLog = null;
        clienteLog = clienteServicio.buscarPorId(id);
        return "perfil";
    }

    @PostMapping("/actualizar-perfil")
    public String actualizar(@RequestParam Long id,
                             @RequestParam String nombre, @RequestParam String apellido,
                             @RequestParam String email,
                             @RequestParam String clave1, @RequestParam String clave2) throws ClienteServiceException {
        Cliente clienteLog = null;
        clienteLog = clienteServicio.buscarPorId(id);
        clienteServicio.modificar(id, nombre, apellido, email, clave1, clave2);// , direccion, edad, telefono, dni);
        clienteLog = clienteServicio.buscarPorId(id);
        return "redirect:/login";
    }

    @GetMapping("/clientes")
    public String mostrarClientes() throws Exception {
        List<Cliente> listaCliente = clienteServicio.findAll();
        return "clientes";
    }
}
