/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FachadaLogica;

import Entidades.Platillo;
import java.util.ArrayList;

/**
 *
 * @author Hot-Dogs La Yaquesita
 */
public class FachadaLogica implements IFachadaLogica {

    @Override
    public void guardarOrden(ArrayList<Platillo> platillos, String notasOrden) {
        TomarOrden tomarOrden = new TomarOrden(platillos, notasOrden);
        tomarOrden.generarOrden();
    }

}
