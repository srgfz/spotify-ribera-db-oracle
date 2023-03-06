package CancionesyGrupos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import singleton.BD;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;


public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	static Login frame1;
	private JButton salir;
	private JPasswordField pass;
	private ArrayList<user> personas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String tipoMusica;
					String contra;
					frame1 = new Login();
					frame1.setVisible(true);
					//Reservo espacio para las ventanas de Usuario y de Admin
					PrincipalUser.frame2 = new PrincipalUser();
					PrincipalUser.frame2.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Getters y setters de los input de usuario y contraseña
		public JTextField getUser() {
			return user;
		}
		public void setUser(JTextField user) {
			this.user = user;
		}
		public JPasswordField getPass() {
			return pass;
		}
		public void setPass(JPasswordField pass) {
			this.pass = pass;
		}
		public ArrayList<user> getPersonas() {
			return personas;
		}
		public void setPersonas(ArrayList<user> pesronas) {
			this.personas = pesronas;
		}
	/**
	 * Create the frame.
	 */
	public Login() {
		this.personas = new ArrayList<>();;
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 358);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Titulo de la ventana Login
			setTitle(" Spotify Ribera | Login");
		//Impedimos que el usuario pueda cambiar el tamaño de la ventana
			setResizable(false);
		//Hacemos que la ventana aparezca centrada
			setLocationRelativeTo(null);
		//COMPONENTES DE LA VENTANA
			//Input del usuario
				user = new JTextField();
				user.setBackground(new Color(102, 102, 102));
				user.setBounds(249, 71, 194, 30);
				contentPane.add(user);
				user.setColumns(10);
				user.setForeground(Color.white);
				//Padding al input User
				user.setBorder(BorderFactory.createCompoundBorder(user.getBorder(), BorderFactory.createEmptyBorder(5,5,5,5)));
			//Botón de entrar
				JButton entrar = new JButton("Entrar");
				entrar.setFont(new Font("Constantia", Font.BOLD, 17));
				entrar.setBackground(new Color(50, 205, 50));
				entrar.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e){ //Este es el evento que se acciona al pulsar  el botón de entrar
						//Creamos la conexión con nuestra BD
						BD miconexion = BD.getInstance();
						miconexion.setCadenaConexion("jdbc:oracle:thin:@localhost:1521:XE");
						miconexion.setUsuario("CANCIONESYGRUPOS3");//Pongo los parámetros de usuario y contraseña
						miconexion.setPass("#");
						//AL de las clases
						ArrayList<user> users = new ArrayList<>();
						ArrayList<Canciones> canciones = new ArrayList<>();
						ArrayList<Grupos> grupos = new ArrayList<>();

						//Otras variables
						String tipo="";
						
						
						
						
						//Compruebo el Login mediante el tipo de usuario de la BD:
						tipo=login(miconexion, tipo);
						
						if(tipo.equals("A")) {//Si el usuario es administrador
							frame1.setVisible(false);
							//Programa del ADMIN:
							Scanner sc = new Scanner(System.in);
							int opcion=0;
							System.out.println("Bienvenido "+frame1.getUser().getText());
							//Creamos la instancia y nos conectamos a la BD:

							do {
								menu();
								opcion=sc.nextInt();

							switch (opcion) {
							case 1: //Dar de alta un nuevo usuario en la BD
								altaGrupo(miconexion);
								break;
							case 2: //Dar de baja un grupo borrando todas sus canciones
								bajaGrupo(miconexion);
								break;
							case 3: //3. Dar de baja una canción de un grupo
								bajaCancion(miconexion);
								break;
							case 4: //4. Dar de alta canción de un grupo
								altaCancion(miconexion);
								break;
							case 5: //5. Consultar usuarios (generar un fichero txt con todos los usuarios)
								consultarUsuarios(miconexion,users);
								break;
							case 6: //6. Generar grupos.csv
								generarGrupos(miconexion, grupos);
								break;
							case 7: //7. Generar canciones.csv
								generarCanciones(miconexion,canciones);

								break;
							case 8: //8. Salir
								System.out.println("Ha salido del programa");
								try {
									miconexion.cerrarConsulta();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								break;
							default:
								System.out.println("Opción no válida");;
							}
							} while (opcion!=8);

							
							
						} else if(tipo.equals("U")) {//Si el usuario es un usuario normal
							frame1.setVisible(false);
							PrincipalUser.frame2.setVisible(true);
							//método que devuelva el objeto usuario que ha iniciado sesión
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				entrar.setBounds(118, 187, 150, 56);
				contentPane.add(entrar);
			//Botón de salir
				salir = new JButton("Salir");
				salir.setFont(new Font("Constantia", Font.BOLD, 17));
				salir.setBackground(new Color(50, 205, 50));
				salir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { //Evento del botón salir
						System.exit(0); //Para salir del programa
					}
				});
				salir.setBounds(293, 187, 150, 56);
				contentPane.add(salir);
			//Label de Usuario
				JLabel Usuario = new JLabel("Usuario:");
				Usuario.setFont(new Font("Tahoma", Font.BOLD, 15));
				Usuario.setBounds(118, 68, 102, 30);
				contentPane.add(Usuario);
				Usuario.setForeground(Color.white);
			//Label de contraseña
				JLabel Contraseña = new JLabel("Contrase\u00F1a:");
				Contraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
				Contraseña.setBounds(118, 122, 102, 25);
				contentPane.add(Contraseña);
				Contraseña.setForeground(Color.white);
			//Input de la contraseña
				pass = new JPasswordField();
				pass.setBackground(new Color(102, 102, 102));
				pass.setBounds(249, 122, 194, 30);
				contentPane.add(pass);
				pass.setForeground(Color.white);
				//Padding al input de contraseña
				pass.setBorder(BorderFactory.createCompoundBorder(pass.getBorder(), BorderFactory.createEmptyBorder(5,5,5,5)));
		}
	//MÉTODOS		
		//1. Dar de alta un nuevo grupo en la BD
		public void altaGrupo(BD miconexion) {
			Scanner sc = new Scanner(System.in);
			int codGrupo, yearGrupo;
			String nameGrupo, generoGrupo;
			//Pido al admin los datos del grupo(El código de grupo lo generaré yo)
				sc.nextLine();
				System.out.println("Nombre del grupo que quiere dar de alta");
				nameGrupo=sc.nextLine();
				System.out.println("Año de fundación del grupo");
				yearGrupo=sc.nextInt();
				sc.nextLine();
				System.out.println("Género musical del grupo");
				generoGrupo=sc.nextLine();
				System.out.println("Código del grupo");
				codGrupo=sc.nextInt();
				
			//Añado el grupo a la BD
				try {
					ResultSet rset=miconexion.consultaBD("INSERT INTO GRUPOS VALUES ("+codGrupo+",'"+nameGrupo+"',"+yearGrupo+",'"+generoGrupo+"')");
					miconexion.consultaBD("commit");
					miconexion.cerrarConsulta();
					rset.close();
					System.out.println("Grupo "+nameGrupo+" añadido a la Base de Datos");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//Añadimos los datos a la BD
				sc.close();
		}
		//2. Dar de baja un grupo borrando todas sus canciones
		public void bajaGrupo(BD miconexion) {
			Scanner sc = new Scanner(System.in);
			int codGrupo;
			//Pido el código del grupo que se quiere borrar
				System.out.println("Código del grupo que desea borrar");
				codGrupo=sc.nextInt();
			//Borro todas las canciones del grupo primero
				try {
					ResultSet rset=miconexion.consultaBD("DELETE FROM CANCIONES WHERE CODIGO_GRUPO="+codGrupo);
					miconexion.consultaBD("commit");
					miconexion.cerrarConsulta();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//Borro el grupo de la Tabla Grupo
				try {
					ResultSet rset=miconexion.consultaBD("DELETE FROM GRUPOS WHERE CODIGO="+codGrupo);
					miconexion.consultaBD("commit");
					miconexion.cerrarConsulta();
					System.out.println("El grupo al que correspondía el código "+codGrupo+" ha sido borrado de la Base de Datos");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//3. Dar de baja una canción de un grupo
		public void bajaCancion(BD miconexion) {
			Scanner sc = new Scanner(System.in);
			String tituloCancion;
			//Pido el título de la canción que quiere borrar
				System.out.println("Introduzca el título de la canción que desea borrar en mayúsculas");
				tituloCancion=sc.nextLine();
			//Borro todas las canciones del grupo primero
				try {
					ResultSet rset=miconexion.consultaBD("DELETE FROM CANCIONES WHERE UPPER(TITULO)='"+tituloCancion+"'");
					miconexion.consultaBD("commit");
					miconexion.cerrarConsulta();
					System.out.println(tituloCancion+" borrada");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//4. Dar de alta canción de un grupo
		public void altaCancion(BD miconexion) {
		Scanner sc = new Scanner(System.in);
		int codGrupo, minCancion,segCancion;
		String nameCancion, nameDisco;
		//Pido al admin los datos del grupo(El código de grupo lo generaré yo)
			sc.nextLine();
			System.out.println("Nombre de la canción que quiere dar de alta");
			nameCancion=sc.nextLine();
			sc.nextLine();
			System.out.println("Nombre del disco");
			nameDisco=sc.nextLine();
			sc.nextLine();
			System.out.println("Código del grupo");
			codGrupo=sc.nextInt();
			System.out.println("Minutos de duración de la canción");
			minCancion=sc.nextInt();
			System.out.println("Segundos de duración de la canción");
			segCancion=sc.nextInt();
			
		//Añado el grupo a la BD
			try {
				ResultSet rset=miconexion.consultaBD("INSERT INTO CANCIONES VALUES ("+codGrupo+",'"+nameDisco+"',"+nameCancion+"',"+minCancion+","+segCancion+")");
				miconexion.consultaBD("commit");
				miconexion.cerrarConsulta();
				System.out.println(nameCancion+" añadida a la base de datos");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//Añadimos los datos a la BD
		}
		//5. Consultar usuarios (generar un fichero txt con todos los usuarios)
		public void consultarUsuarios(BD miconexion, ArrayList<CancionesyGrupos.user> users) {
			//Hacemos una consulta. Podemos hacer tantas consultas con la instancia miconexion como queramos
			try {
				ResultSet rset=miconexion.consultaBD("SELECT LOGIN, PASSWORD, DNI, NOMBRE, APELLIDOS, TIPO_MUSICA FROM USUARIOS WHERE TIPO_USUARIO='U'");
		while(rset.next())//Mientras haya datos
			//Guardo los datos en un AL
			users.add(new user(rset.getString(3), rset.getString(4), rset.getString(5), "Dirección", rset.getString(1), rset.getString(2), rset.getString(6)));
			miconexion.cerrarConsulta();//Cerramos la instancia de miconexion
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Llamamos al método para imprimir un archivo txt
				imprimirTxt("usuarios.txt", users);

		}
		//6. Generar grupos.csv
		public void generarGrupos(BD miconexion, ArrayList<Grupos> grupos) {
			//Hacemos una consulta.
			try {
				ResultSet rset=miconexion.consultaBD("SELECT CODIGO, NOMBRE, AÑO, TIPO_MUSICA FROM GRUPOS");
		while(rset.next())//Mientras haya datos
			//Guardo los datos en un AL
			grupos.add(new Grupos(Integer.parseInt(rset.getString(1)), rset.getString(2), Integer.parseInt(rset.getString(3)), rset.getString(4)));
			miconexion.cerrarConsulta();//Cerramos la instancia de miconexion
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				imprimirCsvGrupos("Grupos.csv", grupos);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		//7. Generar canciones.csv
		public void generarCanciones(BD miconexion, ArrayList<Canciones> canciones) {
			//Hacemos una consulta.
			try {
				ResultSet rset=miconexion.consultaBD("SELECT CODIGO_GRUPO, DISCO, TITULO, MINUTOS, SEGUNDOS FROM CANCIONES");
		while(rset.next())//Mientras haya datos
			//Guardo los datos en un AL
			canciones.add(new Canciones( Integer.parseInt(rset.getString(1)), rset.getString(2), rset.getString(3),Integer.parseInt(rset.getString(4)), Integer.parseInt(rset.getString(5))));
			miconexion.cerrarConsulta();//Cerramos la instancia de miconexion
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			try {
				imprimirCsvCanciones("Canciones.csv", canciones);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//Otros métodos
			//menú del admin
			public static void menu() {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("Selecciones una opción:");
				System.out.println("\t 1. Dar de alta un nuevo grupo en la BD");
				System.out.println("\t 2. Dar de baja un grupo borrando todas sus canciones");
				System.out.println("\t 3. Dar de baja una canción de un grupo");
				System.out.println("\t 4. Dar de alta una canción de un grupo");
				System.out.println("\t 5. Generar usuarios.txt");
				System.out.println("\t 6. Generar grupos.csv");
				System.out.println("\t 7. Generar canciones.csv");
				System.out.println("\t 8. Salir");
			}
			//Método Login
			public String login(BD miconexion, String tipo) {
				String user="", pass="";
				user=frame1.getUser().getText();
				pass=frame1.getPass().getText();
				PrincipalUser us = new PrincipalUser();
					//Añado el grupo a la BD
					try {
						ResultSet rset=miconexion.consultaBD("SELECT * FROM USUARIOS WHERE LOGIN='"+user+"' AND PASSWORD='"+pass+"'");
						
						while(rset.next()){ //Mientras haya datos
							 if(rset.getString(3).equals("A")) {
								 admin p = new admin(rset.getString(4), rset.getString(5), rset.getString(5), "", rset.getString(1), rset.getString(2), rset.getInt(8));
						 }
							 if(rset.getString(3).equals("U")) {
								 user p = new user(rset.getString(4), rset.getString(5), rset.getString(5), "", rset.getString(1), rset.getString(2), rset.getString(7));
								 this.personas.add(p);
								 this.setPersonas(personas);
							 }
							 //Guardo el usuario y la contraseña para poder consultar el Tipo de música del usuario en la ventana del Usuario
							tipo=rset.getString(3);
						}
						miconexion.cerrarConsulta();
	
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				return tipo;
			}
			//Método imprimir.txt
			public void imprimirTxt(String Filename, ArrayList<CancionesyGrupos.user> al) {
				try {
					PrintWriter salida = new PrintWriter(new File(Filename));

					//Habrá que agregar el AL del que leer los datos que vamos a escribir
					
					for (user x : al) {
						salida.println("-"+x);
					}
					salida.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Método para imprimir canciones.csv
			public void imprimirCsvCanciones(String Filename, ArrayList<Canciones> canciones) throws FileNotFoundException {
				PrintWriter salida = new PrintWriter(new File(Filename));

					//Cabecera del csv:
					salida.println("Codigo_Grupo;Disco;Tituo_Cancion;Minutos;Segundos");


					for (Canciones x : canciones) {
						salida.println(x.getCodGrupo()+";"+x.getDisco()+";"+x.getNombre()+";"+x.getMin()+";"+x.getSeg());
					}
					salida.close();	

			}
			//Método para imprimir grupos.csv
			public void imprimirCsvGrupos(String Filename, ArrayList<Grupos> grupos) throws FileNotFoundException {
				PrintWriter salida = new PrintWriter(new File(Filename));

					//Cabecera del csv:
					salida.println("Codigo_Grupo;Nombre_Grupo;Año;Tipo_Musica");


					for (Grupos x : grupos) {
						salida.println(x.getCodigo()+";"+x.getNombre()+";"+x.getYear()+";"+x.getGenero());
					}
					salida.close();	
			}
}
