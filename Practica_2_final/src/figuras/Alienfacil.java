package figuras;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Alienfacil extends Figura{

	private static boolean bajar;
	private static boolean sentido;// t derecha f izquierda
	
	/**
	 * Contructor de alienfacil
	 * @param x=posicion eje x
	 * @param y=posicion eje y
	 */
	public Alienfacil(int x, int y){
		
		quiensoy = "AlienFacil";
		posx=x;
		posy=y;
		vidas=1;

		sentido=true;

		url = this.getClass().getClassLoader().getResource("recursos/alien1.gif");
		try {
			imagen = ImageIO.read(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		}

	/**
	 * esta funcion se encarga de que los aliens se mantengan dentro del marco
	 * @return true o false segun si queremos que vaya en un sentido u otro
	 */
	public boolean sentidoalien() {
		if (posx>757)
		{
			sentido=false;
			return true;
		}
		
		if (posx<0)
		{
			sentido=true;
			return true;
		}
		return false;
	}
	
	/**
	 * Este metodo se encarga de que los aliens se muevan de manera correcta
	 */
	public void movimiento() {
		if (sentido==true)
			posx++;
		else 
			posx--;
		if (bajar)
			posy=posy+30;
		
	}

	/**
	 * Este metodo te devuelve el numero de vidas del alien
	 */
	
	public int get_num_vidas() {
		return vidas;
	}

	/**
	 * Este metodo le resta una vida si le dan al alien
	 */

	public int me_han_dado() {
		return vidas--;
	}


	/**
	 * Este metodo nos muestra la posicion x
	 */
	
	public int getposx() {
		return posx;
	}

	/**
	 * Este metodo nos muestra la posicion y
	 */

	public int getposy() {
		return posy;
	}
	
	
	/**
	 * este metodo nos dice quien es cada cosa
	 */
	public String clase()
	{
		return quiensoy;
	}
	
	/**
	 * este metodo hace que los aliens bajen
	 */
	public static void bajar() {
		bajar=true;
	}
	/**
	 * este metodo hace que los aliens no bajen
	 */
	public static void nobajar() {
		bajar=false;
	}
	

	
	
}