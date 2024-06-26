package PP.alquilerVehiculo.entidad;

import javax.persistence.*;

@Table(name = "vehiculo")
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String patente;
    private String marca;
    private String modelo;
    private String color;
    private String tipoVehiculo;
    private String cilindradaMotor;
    private String combustible;
    private String typeGama;
    private Double precio;
    private String operativo;

    public Vehiculo() {
    }

    public Vehiculo(Integer id, String patente, String marca, String modelo, String color, String tipoVehiculo, String cilindradaMotor, String combustible, String typeGama, Double precio, String operativo) {
        this.id = id;
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipoVehiculo = tipoVehiculo;
        this.cilindradaMotor = cilindradaMotor;
        this.combustible = combustible;
        this.typeGama = typeGama;
        this.precio = precio;
        this.operativo = operativo;
    }

    public Integer getId() {
        return id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getCilindradaMotor() {
        return cilindradaMotor;
    }

    public void setCilindradaMotor(String cilindradaMotor) {
        this.cilindradaMotor = cilindradaMotor;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getTypeGama() {
        return typeGama;
    }

    public void setTypeGama(String typeGama) {
        this.typeGama = typeGama;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getOperativo() {
        return operativo;
    }

    public void setOperativo(String operativo) {
        this.operativo = operativo;
    }
}

