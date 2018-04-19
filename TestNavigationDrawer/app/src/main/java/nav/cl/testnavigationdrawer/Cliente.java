package nav.cl.testnavigationdrawer;

/**
 * Created by prez on 18/04/18.
 */

public class Cliente {
    private String nombre;
    private int edad;
    private String ciudad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Cliente(String nombre, int edad, String ciudad) {

        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }
}
