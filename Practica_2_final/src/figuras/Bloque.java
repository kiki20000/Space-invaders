package figuras;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Bloque extends Figura{

	private String quiensoy = "Bloque";
	
	/**
	 * este metodo sirve para crear bloques de proteccion
	 * @param x
	 * @param y
	 */
	public Bloque(int x, int y){
		
		posx=x;
		posy=y;
		vidas=3;
		quiensoy="bloque";
		
		
		url = this.getClass().getClassLoader().getResource("recursos/bloque.png");
		try {
			imagen = ImageIO.read(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * este metodo nos devuelve el numero de vidas del bloque
	 */

	public int get_num_vidas() {
		return vidas;
	}
	/**
	 * este metodo le resta una vida al bloque
	 */

	public int me_han_dado() {
		return vidas--;
	}


	/**
	 * este metodo nos devuelve la posicion x del bloque
	 */

	public int getposx() {
		return posx;
	}

	/**
	 * nos devuelve la posicion y 
	 */

	public int getposy() {
		return posy;
	}


	/**
	 * nos dice quien es la figura
	 */
	public String clase()
	{
		return quiensoy;
	}

	@Override
	public void movimiento() {
		// no hace nada
		
	}

}
