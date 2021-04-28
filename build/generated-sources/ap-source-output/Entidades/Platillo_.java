package Entidades;

import Entidades.Ingredientes;
import Entidades.OrdenHasPlatillo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-21T17:04:39")
@StaticMetamodel(Platillo.class)
public class Platillo_ { 

    public static volatile SingularAttribute<Platillo, String> descripcion;
    public static volatile ListAttribute<Platillo, OrdenHasPlatillo> ordenHasPlatilloList;
    public static volatile SingularAttribute<Platillo, String> tipoProducto;
    public static volatile SingularAttribute<Platillo, Float> costo;
    public static volatile SingularAttribute<Platillo, Integer> idplatillo;
    public static volatile SingularAttribute<Platillo, String> nombre;
    public static volatile ListAttribute<Platillo, Ingredientes> ingredientesList;

}