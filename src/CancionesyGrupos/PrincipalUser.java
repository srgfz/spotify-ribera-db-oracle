package CancionesyGrupos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import singleton.BD;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;


import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.awt.event.ActionEvent;


public class PrincipalUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField CodGrup;
	private JTextArea textArea;
	static PrincipalUser frame2;
	private ArrayList<Grupos>grupos;
	private ArrayList<Canciones>canciones;
	private ArrayList<Persona>personas;
	private JTextField minPlayList;
	
	//Getters y setters del input del código del grupo
	public JTextField getCodGrup() {
		return CodGrup;
	}
	public void setCodGrup(JTextField codGrup) {
		CodGrup = codGrup;
	}
	//Getters y setters del input del minPlayList
	public JTextField getMinPlayList() {
		return minPlayList;
	}
	public void setMinPlayList(JTextField minPlayList) {
		this.minPlayList = minPlayList;
	}
	//Getters y setters del textArea
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUser frame2 = new PrincipalUser();
					frame2.setVisible(true);
					Login.frame1 = new Login();
					Login.frame1.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalUser() {
		this.personas = new ArrayList<>();
		//Creamos la conexión con nuestra BD
		BD miconexion = BD.getInstance();
		miconexion.setCadenaConexion("jdbc:oracle:thin:@localhost:1521:XE");
		miconexion.setUsuario("CANCIONESYGRUPOS3");
		miconexion.setPass("123");
		
		//Interfaz:
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 516);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Reservo espacio para los AL
			this.grupos = new ArrayList<>();
			this.canciones = new ArrayList<>();
		//Ponemos un título a la ventana
			setTitle(" Spotify Ribera | Programa | Berta");
		//Impedimos que el usuario pueda cambiar el tamaño de la ventana
			setResizable(false);
		//Hacemos que la ventana aparezca centrada
			setLocationRelativeTo(null);
		//COMPONENTES DE LA VENTANA
			//Botón de grupos
				JButton GruposButton = new JButton("Consultar Grupo");
				GruposButton.setBackground(new Color(50, 205, 50));
				GruposButton.setFont(new Font("Constantia", Font.BOLD, 17));
				GruposButton.addActionListener(new ActionListener() {//Evento de pulsar el Botón Grupos
					public void actionPerformed(ActionEvent e) { 
						
						textArea.setText(null);	//Limpiamos el textArea
						//Limpiamos los al
						canciones.clear();
						grupos.clear();
						consultarCanciones(miconexion, getCodGrup().getText());
						}
				});
				GruposButton.setBounds(53, 159, 229, 61);
				contentPane.add(GruposButton);
			//Botón de Canciones aleatorias
				JButton CancionesButton = new JButton("Generar Lista aleatoria");
				CancionesButton.setBackground(new Color(50, 205, 50));
				CancionesButton.setFont(new Font("Constantia", Font.BOLD, 17));
				CancionesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {//Evento de pulsar el Botón de generar playlist
						textArea.setText(null);	//Limpiamos el textArea
						canciones.clear();//Limpiamos el AL
						Login l = new Login();
						//*Es necesario ejecutar la aplicación desde el login para poder saber el tipo de música del usuario
						l.login(miconexion, "");//Así cargo los datos de la persona que ha iniciado sesión para obtener el tipo de Música
						String tipoMusica = l.getPersonas().get(0).gettipoMusica();
						
						generarPlaylist(miconexion, tipoMusica, Integer.parseInt(getMinPlayList().getText()));//Llamo a la función con el tipo de música del usuario que ha hecho el login y los min que ha indicado

					}
				});
				CancionesButton.setBounds(53, 324, 229, 67);
				contentPane.add(CancionesButton);
			//Label del código de grupo
				JLabel lblNewLabel = new JLabel("C\u00F3digo de Grupo a consultar:");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel.setBounds(26, 92, 234, 57);
				contentPane.add(lblNewLabel);
				lblNewLabel.setForeground(Color.white);
			//TextField del código de grupo
				CodGrup = new JTextField();
				CodGrup.setToolTipText("");
				CodGrup.setFont(CodGrup.getFont().deriveFont(CodGrup.getFont().getStyle() | Font.ITALIC));
				CodGrup.setForeground(Color.white);
				CodGrup.setHorizontalAlignment(SwingConstants.CENTER);
				CodGrup.setBackground(new Color(102, 102, 102));
				CodGrup.setBounds(245, 109, 49, 28);
				contentPane.add(CodGrup);
				CodGrup.setColumns(10);
				
			//TextArea
				textArea = new JTextArea();
				textArea.setBackground(new Color(102, 102, 102));
				textArea.setForeground(Color.white);
				textArea.setEditable(false);
				textArea.setWrapStyleWord(true);
				textArea.setSize(473, 104);
				textArea.setLocation(1, 1);	
				//Padding al textArea:
				textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

				contentPane.add(textArea);
			//Label del Título del Text Area
				JLabel lblNewLabel_1 = new JLabel("Grupos y Canciones");
				lblNewLabel_1.setFont(new Font("Ink Free", Font.BOLD, 30));
				lblNewLabel_1.setBounds(436, 33, 268, 37);
				contentPane.add(lblNewLabel_1);
				lblNewLabel_1.setForeground(Color.GREEN);
			//Scroll
				JScrollPane scroll = new JScrollPane(textArea);
				scroll.setSize(480, 350);
				scroll.setLocation(315, 80);
					//Añado el Scroll al panel
					getContentPane().add(scroll, BorderLayout.CENTER);
					
					JLabel lblTiempoDeDuracin = new JLabel("Minutos de la PlayList:");
					lblTiempoDeDuracin.setForeground(Color.WHITE);
					lblTiempoDeDuracin.setFont(new Font("Tahoma", Font.BOLD, 15));
					lblTiempoDeDuracin.setBounds(26, 257, 234, 57);
					contentPane.add(lblTiempoDeDuracin);
					
					minPlayList = new JTextField();
					minPlayList.setToolTipText("");
					minPlayList.setHorizontalAlignment(SwingConstants.CENTER);
					minPlayList.setForeground(Color.WHITE);
					minPlayList.setFont(minPlayList.getFont().deriveFont(minPlayList.getFont().getStyle() | Font.ITALIC));
					minPlayList.setColumns(10);
					minPlayList.setBackground(new Color(102, 102, 102));
					minPlayList.setBounds(245, 274, 49, 28);
					contentPane.add(minPlayList);

	}
		//MÉTODOS
	
				//guardar Grupos
				public void guardarGrupos(BD miconexion, ArrayList<Grupos> grupos, String codGrupo) {
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

				}
				//Consulta de las canciones por el codigo del grupo
				public void consultarCanciones(BD miconexion, String codGrupo) {
					String disco="",discoAnterior="",grupoNombre="";
					//Hacemos una consulta.
					try {
						ResultSet rset=miconexion.consultaBD("SELECT * FROM GRUPOS INNER JOIN CANCIONES ON CANCIONES.CODIGO_GRUPO=GRUPOS.CODIGO WHERE CODIGO_GRUPO='"+codGrupo+"'");
				while(rset.next()) { //Mientras haya datos
					if(grupoNombre.equals("")) {
						grupoNombre = rset.getString(2);
						textArea.append("Canciones del grupo "+(rset.getString(2))+"\n");
						textArea.append("----------------------------------------------------------- \n");
					}
					disco=rset.getString(6);
					if(!disco.equals(discoAnterior) || discoAnterior.equals("")) {//Diferencio si es el mismo disco
						textArea.append("Canciones del disco "+disco+": \n");
					}
					//Imprimo las canciones
					textArea.append("\n");
					textArea.append("\t -"+rset.getString(7)+" ( "+rset.getString(8)+rset.getString(9)+")"+"\n");
					discoAnterior=disco;
				}	
				miconexion.cerrarConsulta();//Cerramos la instancia de miconexion

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					textArea.append("El código introducido no pertenece a ningún grupo de nuestra base de datos");
				}
				}
				

				//Generar playlist aleatoria
				public void generarPlaylist(BD miconexion, String tipoMusica, int minPlayList) {
					int seg = 0, min=0;
					int numRandom=0;
					//Hacemos una consulta.
					try {
						ResultSet rset=miconexion.consultaBD("SELECT * FROM GRUPOS INNER JOIN CANCIONES ON CANCIONES.CODIGO_GRUPO=GRUPOS.CODIGO WHERE TIPO_MUSICA='"+tipoMusica+"'");
				while(rset.next())//Mientras haya datos
					//Guardo los datos en un AL
					canciones.add(new Canciones(rset.getInt(1), rset.getString(6), rset.getString(7), rset.getInt(8), rset.getInt(9)));
					miconexion.cerrarConsulta();//Cerramos la instancia de miconexion
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					textArea.append("Playlist del género "+tipoMusica+":\n");
					textArea.append("\n");

				do {// Hago un bucle mientras la duración indicada sea menor que la suma de las canciones e imprimo dichas canciones seleccionadas con un número generado al azar

						numRandom = (int) (Math.random()*canciones.size());
						Canciones cancion = canciones.get(numRandom);//Guardo la canción en un objeto
						textArea.append("\t- "+cancion.getNombre()+" - "+cancion.getDisco()+"\n");//Imprimo los datos de la canción
						seg+=(cancion.getMin()*60)+cancion.getSeg();//Cuento los segundos acumulados
						canciones.remove(numRandom);//Borro la cancion del AL para que no se repitan
						
				} while (seg<(minPlayList*60));
				//Calculo los minutos y los segundos para imprimirlos
				min = seg/60;
				seg = seg - (min*60);

				textArea.append("------------------------------------------------------------------------------ \n");
				textArea.append("\t Duración: "+min+" minutos "+seg+" segundos");
					}

}