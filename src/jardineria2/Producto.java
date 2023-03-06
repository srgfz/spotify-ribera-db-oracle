package jardineria2;

public class Producto {
//Atributos
	private String CodigoProducto;
	private String Nombre;
	private String Gama;
	private String Dimensiones;
	private String Proveedor;
	private String Descripcion;
	private int CantidadEnStock;
	private float PrecioVenta;
	private float PrecioProveedor;
//Constructores
	//sin parámetros
	public Producto() {
		super();
		CodigoProducto = "";
		Nombre = "";
		Gama = "";
		Dimensiones = "";
		Proveedor = "";
		Descripcion = "";
		CantidadEnStock = 0;
		PrecioVenta = 0;
		PrecioProveedor = 0;
	}
	//con parámetros
	public Producto(String codigoProducto, String nombre, String gama, String dimensiones, String proveedor,
			String descripcion, int cantidadEnStock, float precioVenta, float precioProveedor) {
		super();
		CodigoProducto = codigoProducto;
		Nombre = nombre;
		Gama = gama;
		Dimensiones = dimensiones;
		Proveedor = proveedor;
		Descripcion = descripcion;
		CantidadEnStock = cantidadEnStock;
		PrecioVenta = precioVenta;
		PrecioProveedor = precioProveedor;
	}
//Getters y Setters
	public String getCodigoProducto() {
		return CodigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		CodigoProducto = codigoProducto;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getGama() {
		return Gama;
	}
	public void setGama(String gama) {
		Gama = gama;
	}
	public String getDimensiones() {
		return Dimensiones;
	}
	public void setDimensiones(String dimensiones) {
		Dimensiones = dimensiones;
	}
	public String getProveedor() {
		return Proveedor;
	}
	public void setProveedor(String proveedor) {
		Proveedor = proveedor;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public int getCantidadEnStock() {
		return CantidadEnStock;
	}
	public void setCantidadEnStock(int cantidadEnStock) {
		CantidadEnStock = cantidadEnStock;
	}
	public float getPrecioVenta() {
		return PrecioVenta;
	}
	public void setPrecioVenta(float precioVenta) {
		PrecioVenta = precioVenta;
	}
	public float getPrecioProveedor() {
		return PrecioProveedor;
	}
	public void setPrecioProveedor(float precioProveedor) {
		PrecioProveedor = precioProveedor;
	}
//toString
	@Override
	public String toString() {
		return "Producto [CodigoProducto=" + CodigoProducto + ", Nombre=" + Nombre + ", Gama=" + Gama + ", Dimensiones="
				+ Dimensiones + ", Proveedor=" + Proveedor + ", Descripcion=" + Descripcion + ", CantidadEnStock="
				+ CantidadEnStock + ", PrecioVenta=" + PrecioVenta + ", PrecioProveedor=" + PrecioProveedor + "]";
	}
	
}
