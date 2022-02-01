package figuras;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * en un principio esta clase iba a ser los disparos de los aliens, pero no he llegado a implementarla en ningun lado
 * @author Usuario
 *
 */

public class Bomba extends Figura{
	
	private String quiensoy;
	
	
	public Bomba(int x, int y) {
		
		
		posx=x;
		posy=y;
		vidas=1;
		quiensoy="bomba";
		
		url = this.getClass().getClassLoader().getResource("recursos/shotBad.gif");
		try {
			imagen = ImageIO.read(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	


	public void movimiento() {
		// TODO Apéndice de método generado automáticamente
		
	}


	public int get_num_vidas() {
		return vidas;
	}


	public int me_han_dado() {
		return vidas--;
	}


	public int getposx() {
		return posx;
	}


	public void setposy(int y) {
		posy=y;
		
	}


	public int getposy() {
		
		return posy;
	}



	public String clase() {
		return quiensoy;
	}


}
