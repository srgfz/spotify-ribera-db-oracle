package jardineria2;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Jardineria_Ejercicio2 {
	ArrayList<Producto> productos = new ArrayList<Producto>();
	//MÉTODOS
		//Menú
		public static void menu() {
			System.out.println("---------------------------------------------");
			System.out.println("1. Crear pedidos.csv");
			System.out.println("2. Crear detalle.csv");
			System.out.println("3. Cargar productos");
			System.out.println("4. Crear pedidos.txt");
			System.out.println("5. Salir");
		}
		//Crear Pedidos.csv
		public static void crearPedidos(String Filename) throws SQLException, IOException {
			//Creo lo necesario para trabajar con la BBDD:
			//Crearo el driver
			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//Crearo la conexión con server Oracle
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jardineria2","123");
			Statement stnt=conn.createStatement();
			try {
	            FileWriter fw = new FileWriter(Filename);
				PrintWriter salida = new PrintWriter(fw);
				//Lo imprimo en formato csv y con su cabecera:
				salida.println("CódigoPedido;FechaPedido;FechaEsperada;FechaEntrega;Estado;Comentarios;CódigoCliente");
				ResultSet rset=stnt.executeQuery("SELECT * FROM PEDIDOS");
				//Recorro la tabla
				while(rset.next()) {
					salida.println(rset.getString(1)+";"+rset.getString(2)+";"+rset.getString(3)+";"+rset.getString(4)+";"+rset.getString(5)+";"+rset.getString(6)+";"+rset.getString(7));
				}
				
					salida.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stnt.close();
		}
		//Crear Pedidos.csv
		public static void crearDetallePedidos(String Filename) throws SQLException, IOException {
			//Creo lo necesario para trabajar con la BBDD:
			//Crearo el driver
			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//Crear la conexión con server Oracle
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jardineria2","123");
			Statement stnt=conn.createStatement();
			try {
	            FileWriter fw = new FileWriter(Filename);
				PrintWriter salida = new PrintWriter(fw);
				//Lo imprimo en formato csv y con su cabecera:
				salida.println("CódigoPedido;CódigoProducto;Cantidad;PrecioUnidad;NúmeroLínea");
				ResultSet rset=stnt.executeQuery("SELECT * FROM DETALLEPEDIDOS");
				//Recorro la tabla
				while(rset.next()) {
					salida.println(rset.getString(1)+";"+rset.getString(2)+";"+rset.getString(3)+";"+rset.getString(4)+";"+rset.getString(5));
				}
				
					salida.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stnt.close();
		}
		//Cargar productos
		public void cargarProductos(ArrayList<Producto> productos) throws SQLException{
			//Creo lo necesario para trabajar con la BBDD:
			//Crearo el driver
			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			//Crearo la conexión con server Oracle
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jardineria2","123");
			Statement stnt=conn.createStatement();
			//Consulta:
			ResultSet rset=stnt.executeQuery("SELECT * FROM PRODUCTOS");
			//Lo guardo en un al de productos
			while(rset.next()) {
			productos.add(new Producto(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), Integer.parseInt(rset.getString(7)), Float.parseFloat(rset.getString(8)), Float.parseFloat(rset.getString(8))));
			stnt.close();
			}
			} 
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	
	public static void main(String[] args) {
		Jardineria_Ejercicio2 p = new Jardineria_Ejercicio2();
		Scanner sc = new Scanner(System.in);
		int opcion;
		

			do {
				menu();
				opcion=sc.nextInt();
				switch (opcion) {
					case 1: {//Crear pedidos.csv
						try {
							crearPedidos("pedidos.csv");
						} catch (SQLException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					case 2: {//Crear detalle.csv
						try {
							crearDetallePedidos("detalle.csv");
						} catch (SQLException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					case 3: {//Cargar productos
						try {
							p.cargarProductos(p.productos);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(p.productos);
						break;
					}
					case 4: {//Crear pedidos.txt
						
						break;
					}
					case 5: {//Salir
						System.out.println("Ha salido del programa");
						break;
					}
					default:
						System.out.println("La opción introducida no está entre las opciones posibles");;
					}
			} while (opcion!=5);
		//Cierro la conexión con la bbdd
		sc.close();

	}
}
