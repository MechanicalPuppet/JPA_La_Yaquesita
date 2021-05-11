/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jbran
 */
@Entity
@Table(name = "orden_has_platillo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenHasPlatillo.findAll", query = "SELECT o FROM OrdenHasPlatillo o")
    , @NamedQuery(name = "OrdenHasPlatillo.findByCantidad", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.cantidad = :cantidad")
    , @NamedQuery(name = "OrdenHasPlatillo.findByNotas", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.notas = :notas")
    , @NamedQuery(name = "OrdenHasPlatillo.findByPrecio", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.precio = :precio")
    , @NamedQuery(name = "OrdenHasPlatillo.findByIdOrdenHasPlatillo", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.idOrdenHasPlatillo = :idOrdenHasPlatillo")})
public class OrdenHasPlatillo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "notas")
    private String notas;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orden_has_platillo")
    private Integer idOrdenHasPlatillo;
    @JoinColumns({
        @JoinColumn(name = "orden_idorden", referencedColumnName = "idorden")
        , @JoinColumn(name = "orden_idorden", referencedColumnName = "idorden")})
    @ManyToOne(optional = false)
    private Orden orden;
    @JoinColumns({
        @JoinColumn(name = "platillo_idplatillo", referencedColumnName = "idplatillo")
        , @JoinColumn(name = "platillo_idplatillo", referencedColumnName = "idplatillo")})
    @ManyToOne(optional = false)
    private Platillo platillo;

    public OrdenHasPlatillo() {
    }

    public OrdenHasPlatillo(Integer idOrdenHasPlatillo) {
        this.idOrdenHasPlatillo = idOrdenHasPlatillo;
    }

    public OrdenHasPlatillo(Integer idOrdenHasPlatillo, int cantidad, float precio) {
        this.idOrdenHasPlatillo = idOrdenHasPlatillo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Integer getIdOrdenHasPlatillo() {
        return idOrdenHasPlatillo;
    }

    public void setIdOrdenHasPlatillo(Integer idOrdenHasPlatillo) {
        this.idOrdenHasPlatillo = idOrdenHasPlatillo;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenHasPlatillo != null ? idOrdenHasPlatillo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenHasPlatillo)) {
            return false;
        }
        OrdenHasPlatillo other = (OrdenHasPlatillo) object;
        if ((this.idOrdenHasPlatillo == null && other.idOrdenHasPlatillo != null) || (this.idOrdenHasPlatillo != null && !this.idOrdenHasPlatillo.equals(other.idOrdenHasPlatillo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idOrdenHasPlatillo.toString();
    }
    
}
