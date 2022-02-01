import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import figuras.Alien_medio;
import figuras.Alienfacil;
import figuras.Bloque;
import figuras.Disparo;
import figuras.Figura;
import figuras.Nave;


public class JuegoSpace extends Canvas {

	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	private static String TITULO = "Poo 2019-2020. Space Invaders";
	
	private BufferStrategy strategy;

	
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean firePressed = false;
	private boolean Enter = false;
	
	
	
	
	/**
	 * Aqui estamos creando la ventana del juego
	 */
	public JuegoSpace()
	{
		JFrame container = new JFrame(TITULO);
		JPanel panel = (JPanel) container.getContentPane();
		
		panel.setPreferredSize(new Dimension(ANCHO,ALTO));
		panel.setLayout(null);
		
		setBounds(0,0,ANCHO,ALTO);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);			//Decimos que no se puede redimensionar
		container.setVisible(true);				//Lo hacemos visible
		
		container.addWindowListener(new WindowAdapter() {   // esto hace que se cierre bien el programa
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		addKeyListener(new KeyInputHandler());
		
		//Obtenemos el foco
		requestFocus();

		// create the buffering strategy which will allow AWT to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
	}
	
	

	
	/**
	 *  Este metodo nos sirve para hacer la pantalla de inicio, donde hasta que no pulses enter no empieza el juego
	 */
	public void iniciar_juego() {
		
		while(true) {
		
		Graphics2D g= (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,ANCHO,ALTO);
		Image logo;
		URL url = this.getClass().getClassLoader().getResource("recursos/Logo.png");
		try {
			logo = ImageIO.read(url);														
			
			g.drawImage(logo,150,100,null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.setColor(Color.white);
		Font small = new Font("Helvetica", Font.BOLD, 22);
		g.setFont(small);
		g.drawString("Pulsa enter para empezar",300,400);
		
		g.dispose();
		strategy.show();
		
		while(true) {
		
			if(Enter==true) 
			{
				gameLoop();
			}
			try { Thread.sleep(10);} catch (Exception e) {}
		}	
			
		}
	}
	
	
	/**
	 * este metodo llevara toda la logica del juego, aqui crearemos las figuras y las meteremos en los arrays
	 */
	public void gameLoop() {
		int i;
		int j;
		int y;
		int puntos = 0;
		int sitiox;
		int sitioy;
		int figurax;
		int figuray=600;
		int tiempo=0;
		long ultimo = System.currentTimeMillis();
		boolean partida=true;
		
		ArrayList<Figura> arrfigura= new ArrayList<Figura>();
		
		Nave nave=new Nave();
		arrfigura.add(nave);
		
		
		
		for(figurax=200;figurax<=400;figurax+=200) {
			
			Bloque bloque =new Bloque(figurax, figuray);
			arrfigura.add(bloque);
			
		}
		
		
		for(sitiox=100;sitiox<=450;sitiox+=45) {
			
			for(sitioy=100;sitioy<=190;sitioy+=30) {
				
				Alienfacil alien1 = new Alienfacil(sitiox,sitioy);
				arrfigura.add(alien1);
			}
			
		}
		
		
		
			
		
		while (partida == true) {
			
			
			
			
			//esto mueve la nave
			//nave.movimiento(leftPressed,rightPressed);
			if(leftPressed)
				nave.setDireccion(1);
			else if (rightPressed)
				nave.setDireccion(2);
			else
				nave.setDireccion(0);
			
			//Generar disparo
			
			tiempo +=System.currentTimeMillis()-ultimo;
			
			if(firePressed && tiempo>1000) {
				Disparo p1= new Disparo(nave.getposx(), nave.getposy());
				arrfigura.add(p1);
				ultimo=System.currentTimeMillis();
				tiempo=0;
			}
			
			
			
			
			// hace bajar a los aliens
			for(i=0;i<arrfigura.size();i++) {
				if(arrfigura.get(i).clase()=="AlienFacil") {
					if(((Alienfacil) arrfigura.get(i)).sentidoalien()==true) {
						Alienfacil.bajar();
						
					
						break;
					}
				}
			}
			
			// hace bajar a los aliens
			for(i=0;i<arrfigura.size();i++) {
				if(arrfigura.get(i).clase()=="alienmedio") {
					if(((Alien_medio) arrfigura.get(i)).sentidoalien()==true) {
							Alien_medio.bajar();
					
						break;
					}
				}
			}
			
			if(arrfigura.size()==3) {
				
				for(sitiox=100;sitiox<=450;sitiox+=45) {
					
					for(sitioy=100;sitioy<=190;sitioy+=30) {
						
						Alien_medio alien2 = new Alien_medio(sitiox,sitioy);
						arrfigura.add(alien2);
					}
				}
				
			}
			
			
			//Mover aliens y disparo
			for(i=0;i<arrfigura.size();i++) {
				//if (arrfigura.get(i).clase()=="AlienFacil")
					arrfigura.get(i).movimiento();
			
				
				if (arrfigura.get(i).clase()=="Disparo") {
					y=arrfigura.get(i).getposy();
					if (y<0)
					{
						arrfigura.remove(i);
						i--;
					}
					else
					{
						y=arrfigura.get(i).getposy();
						((Disparo) arrfigura.get(i)).setposy(y-1);
					}
				}
			}
			Alienfacil.nobajar();	
			Alien_medio.nobajar();
			
			
			// colisiones
			for(i=0; i<arrfigura.size(); i++) {
				if (arrfigura.get(i).clase()=="Disparo") {
					for(j=0; j<arrfigura.size(); j++) {
						if (arrfigura.get(j).clase()=="AlienFacil") {
							if(arrfigura.get(i).haColisionadoCon(arrfigura.get(j))) {
								arrfigura.get(j).me_han_dado();
								arrfigura.get(i).me_han_dado();
								puntos+=50;
							}
						}
						else if (arrfigura.get(j).clase()=="alienmedio"){
							
							if(arrfigura.get(i).haColisionadoCon(arrfigura.get(j))) {
								arrfigura.get(j).me_han_dado();
								arrfigura.get(i).me_han_dado();
								puntos+=100;
							}
							
						}
						
					}
				}
				if (arrfigura.get(i).clase()=="nave") {
					for(j=0; j<arrfigura.size(); j++) {
						if (arrfigura.get(j).clase()=="AlienFacil") {
							if(arrfigura.get(i).haColisionadoCon(arrfigura.get(j))) {
								arrfigura.get(j).me_han_dado();
								arrfigura.get(i).me_han_dado();
							}
						}
						else if (arrfigura.get(j).clase()=="alienmedio"){
							
							if(arrfigura.get(i).haColisionadoCon(arrfigura.get(j))) {
								arrfigura.get(j).me_han_dado();
								arrfigura.get(i).me_han_dado();
							
							}
							
						}
						
					}
				}
			}
			
			// Elimina todos los que tienen menos de 0 vidas
			for(i=0;i<arrfigura.size();i++)
			{
				if (arrfigura.get(i).get_num_vidas()<=0) {
					arrfigura.remove(i);
					i--;
				}
				
			}
		
			
			
			
			
			
			
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			
			g.setColor(Color.black);
			g.fillRect(0,0,ANCHO,ALTO);
			
		
			
			g.setColor(Color.white);
			g.drawString("vidas:"+nave.get_num_vidas(),700,20);
			g.drawString("puntuacion: " + puntos ,10,20); 
			
			// cuando se muera informa al jugador de que ha terminado
			if(nave.get_num_vidas()<=0) {
				
				g.drawString("GAME OVER",300,400);
				
			}
			
			
			for(i=0;i<arrfigura.size();i++) {
				arrfigura.get(i).dibujar_imagen(g);
			}
			
			g.dispose();
			strategy.show();
			
			// finally pause for a bit. Note: this should run us at about
			// 100 fps but on windows this might vary each loop due to
			// a bad implementation of timer
			try { Thread.sleep(10); } catch (Exception e) {}

		}
	}
	

	private class KeyInputHandler extends KeyAdapter {
		private int pressCount = 1;
		
		
		/**
		 * Notification from AWT that a key has been press.
		 *
		 * @param e The details of the key that was press 
		 */
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = true;
			}
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				Enter=true;
			}
		} 
		
		/**
		 * Notification from AWT that a key has been released.
		 *
		 * @param e The details of the key that was released 
		 */
		public void keyReleased(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = false;
			}
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				Enter=false;
			}
		}

		/**
		 * Notification from AWT that a key has been typed. Note that
		 * typing a key means to both press and then release it.
		 *
		 * @param e The details of the key that was typed. 
		 */
		public void keyTyped(KeyEvent e) {
			
			
			// if we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
			

		}

		
	}
	
}	

	
