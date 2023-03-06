package singleton;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebaBD_Cancionesygrupos {
	//main
	public static void main(String[] args) {
		BD miconexion = BD.getInstance();
		BD miconexion3 = BD.getInstance();//Nos devolverá la misma instancia que ya hemos creado, ya que solamanete puede haber una instancia y con ella hacemos todas las consultas
		
		//Nos conectamos con miconexion con el servidor, el usuario y la contraseña
			miconexion.setCadenaConexion("jdbc:oracle:thin:@localhost:1521:XE");
			miconexion.setUsuario("cancionesygrupos3");
			miconexion.setPass("123");
			//También podemos hacerlo sin miconexión sustituyendo "miconexión" por "BD.getInstance()," pero con miconexion nos aseguramos que solo tenga una instancia
				BD.getInstance().setCadenaConexion("jdbc:oracle:thin:@localhost:1521:XE");
				BD.getInstance().setUsuario("cancionesygrupos3");
				BD.getInstance().setPass("123");
			
				String user="admin",pass="admin", tipo="";
				Boolean loginAdmin=false;
				//Hacemos una consulta. Podemos hacer tantas consultas con la instancia miconexion como queramos
					try {
						ResultSet rset=miconexion.consultaBD("SELECT LOGIN, PASSWORD, DNI, NOMBRE, APELLIDOS, TIPO_MUSICA FROM USUARIOS WHERE TIPO_USUARIO='U'");
				while(rset.next())//Mientras haya datos
					System.out.println(rset.getString(6));//Imprimimos la primera columna (La primera columna empieza por 1)

				
				miconexion.cerrarConsulta();//Cerramos la instancia de miconexion
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
