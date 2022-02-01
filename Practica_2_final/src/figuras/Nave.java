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



public class Nave extends Figura{

	private int direccion=0;
	
	
	
	/**
	 * Este es el contructor de nave, donde le asignaremos todos sus parametros inciales
	 */
	public Nave (){
		posx=400;
		posy=550;
		vidas=3;
		quiensoy="nave";
		url = this.getClass().getClassLoader().getResource("recursos/ship.gif");
		try {
			imagen = ImageIO.read(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * nos muestra el numero de vidas de nave
	 */

	public int get_num_vidas() {
		return vidas;
	}
	/**
	 * le resta una vida a nave
	 */

	public int me_han_dado() {
		return vidas--;
	}
	/**
	 * nos muestra la posicion x de nave
	 */

	public int getposx() {
		return posx;
	}


	/**
	 * nos muestra la posicion y de nave
	 */

	public int getposy() {
		return posy;
	}

	/**
	 * Este metodo nos dice quien es el objeto 
	 */
	public String clase()
	{
		return quiensoy;
	}
	/**
	 * Este metodo sirve para controlar el movimiento de la nave
	 * @param left
	 * @param right
	 */
	/*
	public void movimiento(boolean left, boolean right) {
		if (left==true) {
			posx--;
		}
		else if (right==true) {
			posx++;
		}
	}
	*/

	@Override
	public void movimiento() {
		if (direccion==1) {
			posx--;
		}
		else if (direccion==2) {
			posx++;
		}
	}

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	
}
