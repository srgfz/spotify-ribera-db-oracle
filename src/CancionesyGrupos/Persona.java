package CancionesyGrupos;

public abstract class Persona {
	//ATRIBUTOS
	protected String DNI;
	protected String nombre;
	protected String apellidos;
	protected String direccion;
	protected String usuario;
	protected String clave;

	//CONSTRUCTOR POR DEFECTO
	public Persona() {
		super();
		this.DNI = "";
		this.nombre = "";
		this.apellidos = "";
		this.direccion = "";
		this.usuario = "";
		this.clave = "";
	}
	//CONSTRUCTOR CON PARÁMETROS
	public Persona(String dNI, String nombre, String apellidos, String direccion, String usuario, String clave) {
		super();
		this.DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.usuario = usuario;
		this.clave = clave;
	}
	//GETTERS Y SETTERS

	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	//toString
	@Override
	public String toString() {
		return "Persona [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", usuario=" + usuario + ", clave=" + clave + "]";
	}
	
}
