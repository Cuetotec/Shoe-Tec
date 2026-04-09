package application;

import com.google.gson.annotations.SerializedName;

public class Material {
	@SerializedName("id_material")
	private Integer id;
    private String nombre;
    private String proveedor;
    private Integer stockActual;
    @SerializedName("stock_minimo")
    private Integer stockMinimo;
    
    // Constructores
    public Material() {}

    // Getters (Importante: JavaFX los usa para llenar la tabla)
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getProveedor() { return proveedor; }
    public Integer getStockActual() { return stockActual; }
    public Integer getStockMinimo() { return stockMinimo; }
    
    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
}
