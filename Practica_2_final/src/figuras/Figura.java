package figuras;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public abstract class Figura extends Canvas{
	
	
	
	// atributos
	protected int posx;
	protected int posy;
	protected int vidas;
	protected String quiensoy;
	protected URL url;
	protected Image imagen;
	protected BufferStrategy strategy;

	/**
	 * este metodo nos dira cuando se han colisionado dos objetos
	 * @param otro
	 * @return
	 */
	public boolean haColisionadoCon(Figura otro)  {
		Rectangle me = new Rectangle();  
		Rectangle him = new Rectangle();    
		me.setBounds((int) posx,(int) posy, imagen.getWidth(null), imagen.getHeight(null));  
		him.setBounds((int) otro.posx,(int) otro.posy, otro.imagen.getWidth(null), otro.imagen.getHeight(null)); 
		 
		 return me.intersects(him);
	}

	// ver numero de vidas
	public abstract int get_num_vidas();
	//resto numero de vidas
	public abstract int me_han_dado();
	
	// imprimo las imagenes
	public void dibujar_imagen(Graphics2D g) {
		g.drawImage(imagen, posx, posy, null);
	}
	
	// muestra la posicion x
	public abstract int getposx();
	// muestra la posicion y
	public abstract int getposy();
	// dira quien es cada objeto
	public abstract String clase();
	// hara el movimiento
	public abstract void movimiento();
	
	
	

}
