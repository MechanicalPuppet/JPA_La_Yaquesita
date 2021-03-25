/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.OrdenJpaController;
import Controladores.UsuariosJpaController;
import Entidades.Orden;
import Entidades.Platillo;
import Entidades.Usuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jbrandon
 */
public  class Pruebas {
    
    public static void main(String[] args) {
         
    try{
        
   // Creación del controlador (Principal gestor de la BD)
   OrdenJpaController COrden = new OrdenJpaController();  
   UsuariosJpaController CUsuarios = new UsuariosJpaController();
   
   
   // Consulta de todos los usuarios y órdenes existentes
   List<Usuarios> usuarios = CUsuarios.findUsuariosEntities();
   List<Orden> ordenes = COrden.findOrdenEntities();
   
   //Solicitamos un usuario para hacer pruebas
   Usuarios usuario = usuarios.get(0);
   
   //Creación de la orden y llenado de datos de la misma
   Orden orden = new Orden();
   orden.setFecha(new Date());
   orden.setIdusuario(usuario);   
   orden.setTotal(190f);
 
   //Inserción en la base de datos
   //COrden.create(orden);
   
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
    
   
   
    } catch (Exception x){
        x.printStackTrace();
    }
   
   
   
    
    }
    
}
