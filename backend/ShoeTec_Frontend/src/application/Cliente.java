package application;

import com.google.gson.annotations.SerializedName;

public class Cliente {
	@SerializedName("id_cliente")
	private Integer id_cliente;
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private String email;
    private String telefono;
    private String password;

    // Constructores
    public Cliente() {}
    public Cliente(Integer id_cliente, String nombre, String direccion, String localidad, String provincia, String email, String telefono) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.email = email;
        this.telefono = telefono;
              
    }

    // Getters y Setters (IMPORTANTE: La tabla los usa para leer los datos)
    public Integer getId_cliente() { return id_cliente; }
    public void setId_cliente(Integer id_cliente) { this.id_cliente = id_cliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }
    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public String toString() {
    	return nombre != null ? nombre : "Cliente sin nombre";
        }
}
