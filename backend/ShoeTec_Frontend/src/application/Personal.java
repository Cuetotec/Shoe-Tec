package application;

import com.google.gson.annotations.SerializedName;

public class Personal {
	@SerializedName("id_personal")
    private Integer id;
    
	private String nombre;
    private String rol;
    private String especialidad;

    // Getters
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
    public String getEspecialidad() { return especialidad;
    }
    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setRol(String rol) { this.rol = rol; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad;
    }
}
