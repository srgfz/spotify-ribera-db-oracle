package CancionesyGrupos;

public class user extends Persona{
	//Atributos
	private String tipoMusica;
	//Constructor
		//Sin parámetros
		public user() {
			super();
			// TODO Auto-generated constructor stub
			this.tipoMusica="";
		}
		//Con parámetros
		public user(String dNI, String nombre, String apellidos, String direccion, String usuario, String clave, String tipoMusica) {
			super(dNI, nombre, apellidos, direccion, usuario, clave);
			// TODO Auto-generated constructor stub
			this.tipoMusica = tipoMusica;
		}
	//Getters y Setters
		public String gettipoMusica() {
			return tipoMusica;
		}
		public void settipoMusica(String tipoMusica) {
			this.tipoMusica = tipoMusica;
		}
	//toString
		@Override
		public String toString() {
			return "user [tipoMusica=" + tipoMusica + ", DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos
					+ ", direccion=" + direccion + ", usuario=" + usuario + ", clave=" + clave + "]";
		}
		
		
}
