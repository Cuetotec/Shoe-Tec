package application;

public class Servicios {
	private Integer id_service;
	private String descripcion;
	private Double precio;
	
	private Integer idMaterialConsumible;
	private Integer cantidadConsumible;

	public Integer getId_service() { return id_service; }
	public String getDescripcion() { return descripcion; }
    public Double getPrecio() { return precio; }
	public Integer getIdMaterialConsumible() { return idMaterialConsumible; }
	public Integer getCantidadConsumible() { return cantidadConsumible; }
	
	public void setId_service(Integer id_service) { this.id_service = id_service; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public void setIdMaterialConsumible(Integer idMaterialConsumible) { this.idMaterialConsumible = idMaterialConsumible;}
    public void setCantidadConsumible(Integer cantidadConsumible) { this.cantidadConsumible = cantidadConsumible;}
    
    @Override
    public String toString() {
        return descripcion; // Esto ayuda a que el Combo lo muestre por defecto
    }
}
