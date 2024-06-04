package presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.TimerTask;
//import java.util.Timer;

import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.JGusanito;
import persistencia.ArchivoSerializable;

public class FGusanito extends JFrame implements ActionListener {
	private JGusanito jGusanito;
	private PPantalla pPantalla;
	private JPanel pElementos;
	private JLabel lTiempo;
	private JLabel lPuntaje;
	private JButton bNuevoJuego;
	private String dir;
	private Timer loop;
	private int seg=0;
	private TimerTask timerTask;
	private final String nombreArchivo = "puntajes.gus";

	public FGusanito() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Gusanito POO 2024");
		this.setSize (800, 850);
		this.setLayout(new BorderLayout());
		this.lTiempo = new JLabel("Tiempo: " + this.seg);
		this.lPuntaje = new JLabel("Puntaje: 0");		
		this.bNuevoJuego = new JButton("Nuevo Juego");
		this.pElementos = new JPanel();
		this.pElementos.setLayout(new GridLayout(1,3,10,10));
		this.pElementos.add(this.bNuevoJuego);
		this.pElementos.add(this.lPuntaje);
		this.pElementos.add(this.lTiempo);

		loop = new Timer(120,this);
		loop.start();

		this.bNuevoJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dir =null;
				nuevoJuego();
			}

		});

		this.pPantalla = new PPantalla();
		this.add(this.pElementos, BorderLayout.NORTH);
		this.add(this.pPantalla, BorderLayout.CENTER);
		this.pPantalla.setFocusable(true);
        this.pPantalla.requestFocusInWindow();
		this.pPantalla.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				teclaOprimida(e);
			}	
		});

		this.setVisible(true);
		
	}
	
	protected void actualizarTiempo() {
		this.seg++;
		this.lTiempo.setText("Tiempo: " + this.seg);
	}


	protected void teclaOprimida(KeyEvent e) {
		if(e.getKeyCode() == JGusanito.ABA) {
			this.dir = "ABA";
		}else if (e.getKeyCode() == JGusanito.ARR) {
			this.dir = "ARR";
		}else if (e.getKeyCode() == JGusanito.IZQ) {
			this.dir = "IZQ";
		} else if (e.getKeyCode() == JGusanito.DER) {
			this.dir = "DER";
		}
		//		this.lPuntaje.setText("Puntaje: " + this.jGusanito.getPuntaje());
		//		this.lTiempo.setText("Tiempo: " + this.jGusanito.getTiempo());
		//		this.pPantalla.repaint();
	}


	private void nuevoJuego() {
		this.jGusanito = new JGusanito();
		this.pPantalla.setjGusanito(this.jGusanito);
		this.pPantalla.repaint();
		this.pPantalla.setFocusable(true);
		this.pPantalla.requestFocusInWindow();
		this.seg = 0;
		if(this.timerTask != null) {
			this.timerTask.cancel();
		}
		this.timerTask = new TimerTask() {
			@Override
			public void run() {
				actualizarTiempo();
			}
		};
		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(this.timerTask, 0, 1000);
	}

	private void guardarPuntaje(int puntaje) {
        try {
            ArchivoSerializable.almacenar(nombreArchivo, puntaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private int cargarPuntaje() {
        try {
            return (int) ArchivoSerializable.cargar(nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

	public static void main(String[] args) {
		new FGusanito();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.jGusanito != null) {
			try {
				this.jGusanito.moverGusanito(this.dir);
				this.lPuntaje.setText("Puntaje: " + this.jGusanito.getPuntaje());
				this.pPantalla.repaint();
			}catch(RuntimeException ex) {
				loop.stop();
				guardarPuntaje(this.jGusanito.getPuntaje());
				JOptionPane.showMessageDialog(this, ex.getMessage(),"Game Over", JOptionPane.INFORMATION_MESSAGE);
			}
			
            
			
		}
	}

}
