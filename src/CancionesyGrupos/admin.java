package CancionesyGrupos;

public class admin extends Persona{
	//Atributos
	public static final int Ma�ana=0;
	public static final int Tarde=1;
	public static final int Noche=2;
	private int turno;
	//Constructor
		//Sin par�metros
		public admin() {
			super();
			// TODO Auto-generated constructor stub
			this.turno=0;
		}
		//Con par�metros
		public admin(String dNI, String nombre, String apellidos, String direccion, String usuario, String clave, int turno) {
			super(dNI, nombre, apellidos, direccion, usuario, clave);
			// TODO Auto-generated constructor stub
			this.turno = turno;
		}
	//Getters y Setters
		public int getTurno() {
			return turno;
		}
		public void setTurno(int turno) {
			this.turno = turno;
		}
	//toString
		@Override
		public String toString() {
			return "admin [turno=" + turno + ", DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos
					+ ", direccion=" + direccion + ", usuario=" + usuario + ", clave=" + clave + "]";
		}


		
}
