/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

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
 * @author Jbrandon
 */
public class Pruebas {

    public static void main(String[] args) {

        try {

            // Creación del controlador (Principal gestor de la BD)
            OrdenJpaController COrden = new OrdenJpaController();
            UsuariosJpaController CUsuarios = new UsuariosJpaController();
            PlatilloJpaController cPlatillo = new PlatilloJpaController();
            OrdenHasPlatilloJpaController cOrdenHasPlatillo = new OrdenHasPlatilloJpaController();

            // Consulta de todos los usuarios y órdenes existentes
            List<Usuarios> usuarios = CUsuarios.findUsuariosEntities();
            List<Orden> ordenes = COrden.findOrdenEntities();
            List<Platillo> platillos = cPlatillo.findPlatilloEntities();

            //Solicitamos un usuario para hacer pruebas
            Usuarios usuario = usuarios.get(0);

            //Creación de la orden y llenado de datos de la misma
            Orden orden = new Orden();
            orden.setFecha(new Date());
            orden.setIdusuario(usuario);

            orden.setTotal(190f);

            //Inserción en la base de datos
            COrden.create(orden);

            ArrayList<OrdenHasPlatillo> platillos2 = new ArrayList<>();
            OrdenHasPlatillo pl = new OrdenHasPlatillo();
            Platillo pla = platillos.get(0);
            pl.setPlatillo(pla);
            pl.setCantidad(1);
            pl.setNotas("Tiezo");
            pl.setPrecio(15f);
            pl.setOrden(orden);
            cOrdenHasPlatillo.create(pl);

            platillos2.add(pl);
            pla.setOrdenHasPlatilloList(platillos2);
            orden.setOrdenHasPlatilloList(platillos2);
            //Llenado de datos en una variable para probar el actualizar
            Orden edit = new Orden(5);
            edit.setFecha(new Date());

            edit.setIdusuario(usuarios.get(0));
            edit.setTotal(200f);

            //Se prueba el actualizar
            //COrden.edit(edit);
            //Procederemos a eliminar la primera orden que creamos.
            //  COrden.destroy(8); //Borrar por medio de ID
            //Impresión de todas las órdenes que tenemos guardadas.
            for (Orden ordene : ordenes) {
                System.out.println(ordene.toString());

            }

        } catch (Exception x) {
            x.printStackTrace();
        }

    }

}
