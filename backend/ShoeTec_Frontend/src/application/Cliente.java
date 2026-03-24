package application;

public class Cliente {
	private Integer id_cliente;
    private String nombre;
    private String email;
    private String direccion;

    // Constructores
    public Cliente() {}
    public Cliente(Integer id_cliente, String nombre, String email, String direccion) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
    }

    // Getters y Setters (IMPORTANTE: La tabla los usa para leer los datos)
    public Integer getId_cliente() { return id_cliente; }
    public void setId(Integer id_cliente) { this.id_cliente = id_cliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
