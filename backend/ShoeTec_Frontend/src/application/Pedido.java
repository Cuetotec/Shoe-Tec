package application;

public class Pedido {
	@com.google.gson.annotations.SerializedName("id_pedido")
    private Integer id;
	
	@com.google.gson.annotations.SerializedName("fecha_creacion")
	private String fecha;
	
	private String estado;
    
	@com.google.gson.annotations.SerializedName("precio_total")
    private Double precio;
    
	@com.google.gson.annotations.SerializedName("id_cliente")
    private Integer idCliente;
	
	@com.google.gson.annotations.SerializedName("id_servicio")
    private Integer idServicio;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }
}
