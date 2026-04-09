package application;

public class DetallePedido {
	private Integer idMaterial;
    private String nombreMaterial;
    private Integer cantidad;

    public DetallePedido(Integer id, String nombre, Integer cant) {
        this.idMaterial = id;
        this.nombreMaterial = nombre;
        this.cantidad = cant;
    }

    // Getters necesarios para PropertyValueFactory
    public String getNombreMaterial() { return nombreMaterial; }
    public Integer getCantidad() { return cantidad; }
    public Integer getIdMaterial() { return idMaterial; }
}

