package co.edu.interfaz;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import co.edu.aplicacion.Artista;
import co.edu.aplicacion.Cancion;
import co.edu.aplicacion.persistencia.impl.DAOArtista;
import co.edu.aplicacion.persistencia.impl.DAOCancion;
import co.edu.aplicacion.servicios.def.IReproductor;
import co.edu.aplicacion.servicios.impl.Reproductor;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ReproductorInterfaz extends javax.swing.JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3543637369788618823L;
	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private JLabel cancion;
	private JSeparator seperador;
	private JLabel reproduciendo;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel Titulo;
	private JMenuItem deleteMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu jMenu4;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem saveMenuItem;
	private JComboBox artistas;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	private final IReproductor reproductor;
	private JTable tablaCanciones;
	private TablaCanciones tab;
	private TablaCanciones tabReproducidas;
	private JScrollPane scrollPane;
//	private List<Cancion> listaReproducida;
	private int indice=0;
	private JLabel nombreArtista;
	private JFileChooser seleccionadorCarpeta;
	private JTable tablaCancionesReproducidas;
	private JScrollPane scrollPaneAnterior;
	
	
	
	
	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ReproductorInterfaz inst = new ReproductorInterfaz();
				inst.setVisible(true);
			}
		});
	}
	
	public ReproductorInterfaz() {
		super();
//		listaReproducida=new ArrayList<Cancion>();
		reproductor = new Reproductor();
		tablaCanciones=new JTable();
		tab=new TablaCanciones();
		tablaCanciones.setModel(tab);
		tabReproducidas=new TablaCanciones();
		tablaCancionesReproducidas=new JTable();
		tablaCancionesReproducidas.setModel(tabReproducidas);
		
		scrollPane=new JScrollPane();
		scrollPaneAnterior=new JScrollPane();
		tablaCanciones.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int fila = tablaCanciones.rowAtPoint(e.getPoint());
				fila = tablaCanciones.convertRowIndexToModel(fila);
				Cancion cancion = tab.obtenerCancion(fila);
				if(SwingUtilities.isLeftMouseButton(e)){
					reproducirCancion(cancion,true);
				}else{
					PantallaCancion pantallaCancion=new PantallaCancion();
					pantallaCancion.verCancion(cancion);
				}
			}
		});
		initGUI();
		try {
			cargarCanciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			{
				Titulo = new JLabel();
				this.setLayout(null);
				getContentPane().add(Titulo);
				Titulo.setText("REPRODUCTOR");
				Titulo.setBounds(250, 0, 115, 26);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Canciones Encontrada");
				jLabel1.setBounds(380, 0, 170, 26);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setBounds(550, 0, 80, 26);
			}
			{
				reproduciendo = new JLabel();
				getContentPane().add(reproduciendo);
				reproduciendo.setText("Cancion");
				reproduciendo.setBounds(250, 32, 100, 16);
			}
			{
				seperador = new JSeparator();
				getContentPane().add(seperador);
				seperador.setBounds(12, 266, 1059, -38);
			}
			{
				cancion = new JLabel();
				getContentPane().add(cancion);
				cancion.setBounds(350, 32, 200, 24);
				cancion.setBackground(Color.RED);
			}
			{
				nombreArtista=new JLabel();
				getContentPane().add(nombreArtista);
				nombreArtista.setBounds(630, 32, 200, 16);
			}
			this.setSize(1500, 542);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("File");
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("Random");
						newFileMenuItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								cancionRandom();
							}
						});
						newFileMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
					}
					{
						openFileMenuItem = new JMenuItem();
						jMenu3.add(openFileMenuItem);
						openFileMenuItem.setText("Atras");
						openFileMenuItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								reproducirCancionAnterior();
							}
						});
						openFileMenuItem.setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_DOWN_MASK));						
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("Buscar Canciones");
						saveMenuItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								buscarCarpeta();
								try {
									cargarCanciones();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						saveMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("Pausar");
						exitMenuItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								reproductor.pausar();
							}
						});
						exitMenuItem.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("Edit");
					{
						cutMenuItem = new JMenuItem();
						jMenu4.add(cutMenuItem);
						cutMenuItem.setText("Cut");
					}
					{
						copyMenuItem = new JMenuItem();
						jMenu4.add(copyMenuItem);
						copyMenuItem.setText("Copy");
					}
					{
						pasteMenuItem = new JMenuItem();
						jMenu4.add(pasteMenuItem);
						pasteMenuItem.setText("Paste");
					}
					{
						jSeparator1 = new JSeparator();
						jMenu4.add(jSeparator1);
					}
					{
						deleteMenuItem = new JMenuItem();
						jMenu4.add(deleteMenuItem);
						deleteMenuItem.setText("Delete");
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("Help");
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("Help");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarCanciones() throws Exception {
		// String ruta="/home/desarrollo10/musicaalejo";
		DAOCancion daoCancion = new DAOCancion();
		List<Cancion> canciones = daoCancion.consultarTodos();
		refrescarTabla(canciones);
		repaint();

	}
	
	
	private List<Cancion> cargarArtista(String nombre) throws Exception{
		DAOCancion daoCancion=new DAOCancion();
		 return  daoCancion.consultarCancionArtista(nombre);
	}

	
	private void refrescarTabla(List<Cancion> canciones) throws Exception{
		Collections.sort(canciones, new Comparator<Cancion>() {
			
			public int compare(Cancion o1, Cancion o2) {
				String valor1 = o1.getNombre() == null ? "" : o1.getNombre();
				String valor2 = o2.getNombre() == null ? "" : o2.getNombre();
				return valor1.compareTo(valor2);
			}
		});
		tab.removeAll();
		for (Cancion cancion : canciones) {
			tab.adicionarCanciones(cancion);
		}
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(
				tab);
		tablaCanciones.setRowSorter(ordenador);
		tablaCanciones.revalidate();
		tablaCanciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setBounds(10, 64, 700, 400);
		scrollPane.getViewport().add(tablaCanciones);
		scrollPane.revalidate();

		TableRowSorter<TableModel> ordenadorAnterior = new TableRowSorter<TableModel>(
				tabReproducidas);

		tablaCancionesReproducidas.setRowSorter(ordenadorAnterior);
		tablaCancionesReproducidas.revalidate();
		tablaCancionesReproducidas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scrollPaneAnterior.setBounds(750, 64, 700, 400);
		scrollPaneAnterior.getViewport().add(tablaCancionesReproducidas);
		scrollPaneAnterior.revalidate();

		getContentPane().add(scrollPaneAnterior);
		jLabel2.setText(canciones.size() + "");
		getContentPane().add(scrollPane);
		
		tab.fireTableDataChanged();
		getContentPane().add(scrollPane);
		{
			DAOArtista daoArtista=new DAOArtista();
			List<Artista> lista=daoArtista.consultarTodos();
			ComboBoxModel ArtistasModel = new DefaultComboBoxModel();
			artistas = new JComboBox();
			artistas.addItem("Seleccione");
			for(Artista art:lista){
				artistas.addItem(art.getNombre());
			}
			getContentPane().add(artistas);
			artistas.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource()!=null){
						JComboBox a=(JComboBox) arg0.getSource();
						String nombre=(String) a.getSelectedItem();
						if(nombre.equalsIgnoreCase("Seleccione")){
							nombre=null;
						}
						try {
							refrescarTabla(cargarArtista(nombre));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				}
			});
			artistas.setBounds(700, 0, 196, 23);
		}
		getContentPane().validate();
		getContentPane().repaint();
	}

	private void reproducirCancion(Cancion cancion,boolean actualizarIndice){
		reproductor.reproducir(cancion,this);
		if(actualizarIndice){
			tabReproducidas.adicionarCanciones(cancion);
			indice=tabReproducidas.getRowCount();
			tablaCancionesReproducidas.revalidate();
			tablaCancionesReproducidas.repaint();
		}
		try {
			final DAOCancion daoCancion=new DAOCancion();
			daoCancion.adicionarReproduccion(cancion);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ReproductorInterfaz.this.cancion.setText(cancion.getNombre());
		ReproductorInterfaz.this.nombreArtista.setText(cancion.getArtista());
		
	}
	
	private Cancion obtenerCancion(int indice){
		tablaCanciones.changeSelection(indice, 0, false, false);
		return tab.obtenerCancion(indice);
		
	}
	
	private Cancion obtenerCancionAnterior(int indice){
		tablaCancionesReproducidas.changeSelection(indice, 0, false, false);
		return tabReproducidas.obtenerCancion(indice);
		
	}

	private void cancionRandom(){
		if(indice<0){
			return;
		}
		if(indice==tabReproducidas.getRowCount()){
			int cantidad=tablaCanciones.getRowCount();
			int indice=new Double(Math.random()*cantidad).intValue();
			int fila = indice;
			reproducirCancion(obtenerCancion(fila),true);
		}else{
			Cancion cancion=obtenerCancionAnterior(indice);
			indice++;
			reproducirCancion(cancion,false);
		}
	}
	
	private void reproducirCancionAnterior(){
		if(indice==0){
			return;
		}
		indice--;
		Cancion can=obtenerCancionAnterior(indice);
		reproducirCancion(can,false);
		String nombreColumna=tablaCancionesReproducidas.getColumnName(0);
		TableColumnModel tableColumnModel=tablaCancionesReproducidas.getColumnModel();
		Enumeration<TableColumn> en = tableColumnModel.getColumns();
		while (en.hasMoreElements()) {
			TableColumn tc = en.nextElement();
			tc.setCellRenderer(new DefaultTableCellRenderer(){
				public void setBackground(Color c) {
//					super.setBackground(Color.RED);
				}});
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		cancionRandom();
	}
	
	
	public void buscarCarpeta(){
		seleccionadorCarpeta=new JFileChooser();
		seleccionadorCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int valor=seleccionadorCarpeta.showOpenDialog(this);
		if(valor==JFileChooser.APPROVE_OPTION){
			long inicial = System.currentTimeMillis();
			reproductor.buscarCanciones(seleccionadorCarpeta.getSelectedFile().getAbsolutePath());
			long finalt = System.currentTimeMillis();
			System.out.println(finalt-inicial);
			try {
				cargarCanciones();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		
	}
}
