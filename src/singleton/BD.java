package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class BD {
	//Atributos:
		private static BD miInstancia=null;
		private static boolean permitirInstancianueva;
		private String cadenaConexion;
		private String usuario;
		private String pass;
		private Connection conn;
		private Statement stmt;
	//Con el booleano permitirInstancianueva controlar que solo exista una
		BD() throws Exception{
			if(!permitirInstancianueva)
				throw new Exception("Ya hay una instancia greadea; usa getInstance()");
		}
	//La primera y única vez que se crea
	//Fíjate que se inicializa a nulo al declarar la variable de la clase
		public static BD getInstance() {
			if(miInstancia==null) {//Si es la primera vez que creo la instancia
				permitirInstancianueva=true;
				try {
					miInstancia=new BD();//Añadimos el try catch por la excepción que lanza el BD()
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//Creo la instancia
				permitirInstancianueva=false;//Después de crear lo vuelvo a poner a false
			}
			
			return miInstancia;
		}
	//Devuelve el conjunto de tuplas de la consulta
		public ResultSet consultaBD(String consulta) throws SQLException {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn=DriverManager.getConnection(cadenaConexion, usuario, pass);
			stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(consulta);
			
			return rset;
		 }
	//Cerrar consulta
		public void cerrarConsulta() throws SQLException {
			stmt.close();
		 }
	//Añadir Getters y Setters
		public String getCadenaConexion() {
			return cadenaConexion;
		}
		public void setCadenaConexion(String cadenaConexion) {
			this.cadenaConexion = cadenaConexion;
		}
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		public Connection getConn() {
			return conn;
		}
		public void setConn(Connection conn) {
			this.conn = conn;
		}
		public Statement getStmt() {
			return stmt;
		}
		public void setStmt(Statement stmt) {
			this.stmt = stmt;
		}
}