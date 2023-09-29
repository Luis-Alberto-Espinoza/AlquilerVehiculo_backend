package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {
    @Autowired
    private EmpleadoServicio empleadoServicio;

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @Autowired
    private ClienteServicio clienteServicio;


    @GetMapping("/admin")
    public String adminHome(@RequestParam String correo) throws Exception {
        Empleado usuario = empleadoServicio.buscarXmail(correo);
        return "index_admin";
    }


    @GetMapping("/admin_p")
    //http://localhost:9000/cliente/?correo=Correocliente_14%40correo.com&password=123456
    public String admin_persona(@RequestParam Integer ide) throws Exception {
        List<Empleado> empleados = empleadoServicio.findAll();
        Empleado usuario = empleadoServicio.findById(ide);
        return "admin_p";
    }

    @GetMapping("/delet_empleado")
    public String eliminarVehiculo(Integer idm, Integer ide, RedirectAttributes redirectAttrs) throws Exception {
        empleadoServicio.deleteById(idm);
        redirectAttrs.addAttribute("correo", empleadoServicio.findById(ide).getId());
        return "redirect:/empleado/admin_p/?ide={correo}";
    }

    @GetMapping("/new_empleado_1")//ide=2009
    public String new_empleado_1(@RequestParam Integer ide) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        return "new_empleado1";
    }

    @GetMapping("/new_empleado_2")//?ide=2009&dni=29148574
    public String new_empleado_2(@RequestParam Integer ide, String dni) throws Exception {
        Empleado empleadoLog = empleadoServicio.findById(ide);

        if (clienteServicio.hayClienteXdni(dni).equals("1")) {
            Cliente clienteNew = clienteServicio.buscarXdni(Integer.valueOf(dni));
        } else if (empleadoServicio.existeEmpleadoXdni(dni).equals("1")) {
            Empleado empleadoNew = empleadoServicio.buscarXdni(dni);
        } else {
            return "registrar_empleado";
        }
        return "new_empleado2";
    }

    @PostMapping("/new_empleado_3")
    public String saveNewEmpleado(Integer ide,
                                  @RequestParam String nombre, @RequestParam String apellido,
                                  @RequestParam String email, @RequestParam String clave1,
                                  @RequestParam String direccion, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fNacimiento,
                                  @RequestParam Integer telefono, @RequestParam Integer dni, @RequestParam String typeEmpleado) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        String titulo1 = "", titulo2 = "", descripcion = "";
        String hayEmpleado = empleadoServicio.existeEmpleado(email);
        Empleado newEmpleado;
        if (hayEmpleado.equals("0")) {
            newEmpleado = new Empleado();
            titulo2 = "El empleado fue creado";
            Date fechaRegistro = new Date();
            newEmpleado.setAlta(fechaRegistro);
        } else {
            newEmpleado = empleadoServicio.buscarXdni(String.valueOf(dni));
            titulo2 = "El empleado fue actualizado";
        }
        newEmpleado.setNombre(nombre);
        newEmpleado.setApellido(apellido);
        newEmpleado.setMail(email);
        newEmpleado.setClave1(clave1);
        newEmpleado.setDireccion(direccion);
        newEmpleado.setFechaNacimiento(fNacimiento);
        newEmpleado.setTelefono(telefono);
        newEmpleado.setDni(dni);
        newEmpleado.setTypeEmpleado(typeEmpleado);
        Empleado empleadoGuarado = empleadoServicio.save(newEmpleado);
        titulo1 = "EXITO!!!!";
        descripcion = "Tome Nota del número de empleado";
        String home = "/empleado/admin/?correo=" + empleado.getMail();
        return "/exitoGeneral";
    }

    @GetMapping("/edit_empleado")
    public String editarVehiculoEmpleado(Integer idm, Integer ide) throws Exception {
        Empleado aModificar = empleadoServicio.findById(idm);
        Empleado empleado = empleadoServicio.findById(ide);
        return "new_empleado2";
    }

    @GetMapping("/admin_v")
    //http://localhost:9000/cliente/?correo=Correocliente_14%40correo.com&password=123456
    public String admin_vehiculo(@RequestParam Integer id) throws Exception {
        Empleado empleado = empleadoServicio.findById(id);
        List<Vehiculo> vehiculo = vehiculoServicio.findAll();
        return "admin_v";
    }

    @GetMapping("/ventas")
    //http://localhost:9000/cliente/?correo=Correocliente_14%40correo.com&password=123456
    public String ventasHome(@RequestParam String correo) throws Exception {
        Empleado usuario = empleadoServicio.buscarXmail(correo);
        return "index_ventas";
    }

    @GetMapping("/tabla")
    public String mostrar_e() throws Exception {
        List<Empleado> listaEmpleados = empleadoServicio.findAll();
        return "hitorial_reserva_cliente";
    }

    @PostMapping("/mostrar")
    public String mostrar(@RequestParam String email) {
        try {
            empleadoServicio.guardarMail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "indexe";
    }

    @GetMapping("/prueba_empleado")
    public void LEVentas() throws Exception {
        Empleado empleado = empleadoServicio.buscarXmail("CorreoEmpleado_09@correo.com");
        System.out.println(empleado.getId() + " id " + empleado.getTypeEmpleado() + "  " + empleado.getNombre());
    }
}
