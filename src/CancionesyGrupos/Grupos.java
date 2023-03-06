package CancionesyGrupos;

public class Grupos {
	//Atributos
		private int codigo;
		private String nombre;
		private int year;
		private String genero;
		//En esta clase se podría incluir también un AL con las canciones de cada grupo, pero como en el ejercicio hay 
		//dos botones independientes y carga los datos en  los AL de forma independiente no lo he incluido
	//Constructores
		//Constructor por defecto
		public Grupos() {
			this.codigo = 0;
			this.nombre = "";
			this.year = 0;
			this.genero = "";
		}		
		//Constructor con todos los parámetros
		public Grupos(int codigo, String nombre, int year, String genero) {
			this.codigo = codigo;
			this.nombre = nombre;
			this.year = year;
			this.genero = genero;
		}
	//Getters y Setters
		public int getCodigo() {
			return codigo;
		}
		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getGenero() {
			return genero;
		}
		public void setGenero(String genero) {
			this.genero = genero;
		}
	//toString
		@Override
		public String toString() {
			return "Grupos [codigo=" + codigo + ", nombre=" + nombre + ", year=" + year + ", genero=" + genero + "]";
		}
		
			
}
