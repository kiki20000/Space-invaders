package figuras;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Disparo extends Figura{

	/*
	 * Este es el constructor de disparo
	 */
	public Disparo(int x, int y) {
		
		vidas=1;
		posx=x;
		quiensoy = "Disparo";
		posy=y;
		url = this.getClass().getClassLoader().getResource("recursos/shot.gif");
		try {
			imagen = ImageIO.read(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * este metodo nos devuelve el numero de vidas de nave
	 */
	public int get_num_vidas() {
		return vidas;
	}
	
	/**
	 * este metodo nos devuelva la posicion x del disparo
	 * @return posx
	 */
	public int get_posx() {
		return posx;
	}
	
	/**
	 * este metodo nos devuelve la posicion del disparo en el eje y
	 * @return posy
	 */
	public int get_posy() {
		return posy;
	}
	/**
	 * este metodo hace que el disparo suba hacia arriba
	 */
	public void movimiento() {
		posy=posy-4;
	}


	/**
	 * este metodo le resta una vida al disparo
	 */

	public int me_han_dado() {
		return vidas--;
	}
	/**
	 * este metodo hace que cambiemos el valor del disparo del eje y
	 * @param y
	 */
	public void setposy(int y) {
		posy=y;
		
	}
	/**
	 * este metodo nos devuelve la posicion y del disparo
	 */

	public int getposy() {
		return posy;
	}


	/**
	 * este metodo nos dice quien es la figura
	 */

	public String clase() {
		return quiensoy;
	}
	
	/**
	 * este metodo nos devuelve la posicion x del disparo
	 */

	public int getposx() {
		return posx;
	}


}
