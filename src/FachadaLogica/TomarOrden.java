/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FachadaLogica;

import Controladores.OrdenHasPlatilloJpaController;
import Controladores.OrdenJpaController;
import Controladores.PlatilloJpaController;
import Controladores.UsuariosJpaController;
import Entidades.Orden;
import Entidades.OrdenHasPlatillo;
import Entidades.Platillo;
import Entidades.Usuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hot-Dogs La Yaquesita
 */
public class TomarOrden {

    String notasOrden;
    OrdenJpaController COrden;
    UsuariosJpaController CUsuarios;
    PlatilloJpaController cPlatillo;
    ArrayList<Platillo> platillos;

    public TomarOrden(ArrayList<Platillo> platillos, String notasOrden) {
        COrden = new OrdenJpaController();
        CUsuarios = new UsuariosJpaController();
        cPlatillo = new PlatilloJpaController();
        this.platillos = platillos;
        this.notasOrden = notasOrden;
    }

    public void generarOrden() {
        guardarOrden();
    }

    public void guardarOrden() {
        List<Usuarios> usuarios = CUsuarios.findUsuariosEntities();
        List<Platillo> platillos = cPlatillo.findPlatilloEntities();
        Usuarios usuario = usuarios.get(0);

        Orden orden = new Orden();
        orden.setFecha(new Date());
        orden.setIdusuario(usuario);

        orden.setTotal(calcularOrden());

        COrden.create(orden);

        guardarOrdenHasPlatillos(orden);
    }

    public void guardarOrdenHasPlatillos2(Orden orden, OrdenHasPlatillo ordenHasPlatillos, List<OrdenHasPlatillo> ordHasList) {
        OrdenHasPlatilloJpaController cOrdenHasPlatillo = new OrdenHasPlatilloJpaController();
        ordenHasPlatillos.setCantidad(1);
        ordenHasPlatillos.setOrden(orden);
        orden.setOrdenHasPlatilloList(ordHasList);

        try {
            cOrdenHasPlatillo.create(ordenHasPlatillos);
        } catch (Exception ex) {
            System.out.println("Preexisting entity");
        }
    }

    public void guardarOrdenHasPlatillos(Orden orden) {

        List<Platillo> platillosBD = cPlatillo.findPlatilloEntities();
        ArrayList<OrdenHasPlatillo> platillos2 = new ArrayList<>();
       
        for (int i = 0; i < platillosBD.size(); i++) {

            for (Platillo platillo : platillos) {

                if (platillo.getNombre().equalsIgnoreCase(platillosBD.get(i).getNombre())) {
                    OrdenHasPlatillo ordenHasPlatillos = new OrdenHasPlatillo();
                    ordenHasPlatillos.setPlatillo(platillosBD.get(i));
                    platillos2.add(ordenHasPlatillos);
                    ordenHasPlatillos.setPrecio(platillo.getCosto());
                    ordenHasPlatillos.setNotas(platillo.toString());
                    platillos2.add(ordenHasPlatillos);
                    guardarOrdenHasPlatillos2(orden, ordenHasPlatillos, platillos2);
                }

            }

        }

    }

    public void notasOrden(Platillo platillo) {

        notasOrden = notasOrden.concat(platillo.toString() + "\n");

    }

    public float calcularOrden() {
        float total = 0F;

        for (Platillo platillo : platillos) {
            total += platillo.getCosto();
        }

        return total;
    }

}
