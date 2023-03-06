package CancionesyGrupos;

public class Canciones {
	//Atributos
		private int codGrupo;
		private String disco;
		private String nombre;
		private int min;
		private int seg;
	//Constructores
		//Constructor con los parámetros por defecto
		public Canciones() {
			this.codGrupo = 0;
			this.disco = "";
			this.nombre = "";
			this.min = 0;
			this.seg = 0;
		}
		//Constructor con todos los parámetros
		public Canciones(int codGrupo, String disco, String nombre, int min, int seg) {
			this.codGrupo = codGrupo;
			this.disco = disco;
			this.nombre = nombre;
			this.min = min;
			this.seg = seg;
		}
	//Getters y Setters
		public int getCodGrupo() {
			return codGrupo;
		}
		public void setCodGrupo(int codGrupo) {
			this.codGrupo = codGrupo;
		}
		public String getDisco() {
			return disco;
		}
		public void setDisco(String disco) {
			this.disco = disco;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public int getMin() {
			return min;
		}
		public void setMin(int min) {
			this.min = min;
		}
		public int getSeg() {
			return seg;
		}
		public void setSeg(int seg) {
			this.seg = seg;
		}
	//toString
		@Override
		public String toString() {
			return "Canciones [codGrupo=" + codGrupo + ", disco=" + disco + ", nombre=" + nombre + ", min=" + min
					+ ", seg=" + seg + "]";
		}
		
			
}
