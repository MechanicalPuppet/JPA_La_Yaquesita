package Entidades;

import Entidades.Orden;
import Entidades.Platillo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-10T21:56:01")
@StaticMetamodel(OrdenHasPlatillo.class)
public class OrdenHasPlatillo_ { 

    public static volatile SingularAttribute<OrdenHasPlatillo, Float> precio;
    public static volatile SingularAttribute<OrdenHasPlatillo, String> notas;
    public static volatile SingularAttribute<OrdenHasPlatillo, Integer> cantidad;
    public static volatile SingularAttribute<OrdenHasPlatillo, Orden> orden;
    public static volatile SingularAttribute<OrdenHasPlatillo, Platillo> platillo;
    public static volatile SingularAttribute<OrdenHasPlatillo, Integer> idOrdenHasPlatillo;

}