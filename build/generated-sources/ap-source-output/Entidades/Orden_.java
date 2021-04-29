package Entidades;

import Entidades.OrdenHasPlatillo;
import Entidades.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-27T23:28:46")
@StaticMetamodel(Orden.class)
public class Orden_ { 

    public static volatile SingularAttribute<Orden, Date> fecha;
    public static volatile ListAttribute<Orden, OrdenHasPlatillo> ordenHasPlatilloList;
    public static volatile SingularAttribute<Orden, Float> total;
    public static volatile SingularAttribute<Orden, Integer> idorden;
    public static volatile SingularAttribute<Orden, Usuarios> usuarios;

}