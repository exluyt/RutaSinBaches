/**
 * @author Kiss, Henry, Nacho, Mario
 */
package Vista;

import Controlador.*;
import Modelo.*;

public interface Vista {

	public void setControlador(Controlador miControlador);
	public void setModelo(Modelo miModelo);
	
	public void setVisible(boolean b);
}
