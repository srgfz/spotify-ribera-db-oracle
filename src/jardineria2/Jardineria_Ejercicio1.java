package jardineria2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Jardineria_Ejercicio1 {
	public static void main(String[] args) throws SQLException {
		//1.Crear el driver
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		//2.Crear la conexi�n con server Oracle
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jardineria2","123"); 
			//*Para conectar con la base de datos de otro ordenador tendremos que estar dentro de la misma red wifi y poner en vez de "localhost" la ip del compa�ero y su usuario y su contrase�a de la BBDD
		//3.Crear la consulta//4.Ejecuta la consulta: 
			Statement stnt=conn.createStatement();
			//C�digo y nombre de los clientes
//				ResultSet rset=stnt.executeQuery("SELECT CODIGOCLIENTE,NOMBRECLIENTE FROM CLIENTES");
			//Ciudad y el tel�fono de las oficinas de EEUU
				ResultSet rset=stnt.executeQuery("select ciudad,telefono from oficinas\r\n"
				+ "where pais='EEUU'");
				//Recorremos la tabla
				while(rset.next())
					System.out.println(rset.getString(1)+" || "+rset.getString(2)); //La primera columna es 1, no 0
					System.out.println("\n----------------------------\n");
			//Nombre, apellidos y mail de los empleados a cargo de Alberto Soria
					ResultSet rset2=stnt.executeQuery("select nombre,apellido1||' '||apellido2 as apellidos,email from empleados\r\n"
					+ "where codigojefe=7");
					//Recorremos la tabla
					while(rset2.next())
						System.out.println(rset2.getString(1)+" || "+rset2.getString(2)+" || "+rset2.getString(3));
						System.out.println("\n----------------------------\n");
			//Nombre de clientes espa�oles
				ResultSet rset3=stnt.executeQuery("SELECT NOMBRECLIENTE FROM CLIENTES\r\n"
				+ "where clientes.pais in ('Espa�a', 'Spain')");
				//Recorrer la tabla
				while(rset3.next())
					System.out.println(rset3.getString(1));
					System.out.println("\n----------------------------\n");
			//
		//5.Recorrer la tabla
//		while(rset.next())
//			System.out.println(rset.getString(1)+" "+rset.getString(2));
		//**Para saber el n�mero de columnas e un resulset para poder usar un for:
				ResultSetMetaData rsmd = rset2.getMetaData();//cuento las columnas de la consulta rset2
				int numColumnas = rsmd.getColumnCount();
				//6.Cerrar la conexi�n
		stnt.close();
		/*select ciudad,telefono from oficinas where Pais='EEUU';
select nombre,apellido1||' '||apellido2 as apellidos,email from empleados
where codigojefe=7;
select nombrecliente from clientes where pais='Espa�a';*/
	}
}
