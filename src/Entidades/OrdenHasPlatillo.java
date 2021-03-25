/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
    , @NamedQuery(name = "OrdenHasPlatillo.findByOrdenIdorden", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.ordenHasPlatilloPK.ordenIdorden = :ordenIdorden")
    , @NamedQuery(name = "OrdenHasPlatillo.findByPlatilloIdplatillo", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.ordenHasPlatilloPK.platilloIdplatillo = :platilloIdplatillo")
    , @NamedQuery(name = "OrdenHasPlatillo.findByCantidad", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.cantidad = :cantidad")
    , @NamedQuery(name = "OrdenHasPlatillo.findByNotas", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.notas = :notas")
    , @NamedQuery(name = "OrdenHasPlatillo.findByPrecio", query = "SELECT o FROM OrdenHasPlatillo o WHERE o.precio = :precio")})
public class OrdenHasPlatillo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenHasPlatilloPK ordenHasPlatilloPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "notas")
    private String notas;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @JoinColumn(name = "orden_idorden", referencedColumnName = "idorden", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orden orden;
    @JoinColumn(name = "platillo_idplatillo", referencedColumnName = "idplatillo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Platillo platillo;

    public OrdenHasPlatillo() {
    }

    public OrdenHasPlatillo(OrdenHasPlatilloPK ordenHasPlatilloPK) {
        this.ordenHasPlatilloPK = ordenHasPlatilloPK;
    }

    public OrdenHasPlatillo(OrdenHasPlatilloPK ordenHasPlatilloPK, int cantidad, float precio) {
        this.ordenHasPlatilloPK = ordenHasPlatilloPK;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public OrdenHasPlatillo(int ordenIdorden, int platilloIdplatillo) {
        this.ordenHasPlatilloPK = new OrdenHasPlatilloPK(ordenIdorden, platilloIdplatillo);
    }

    public OrdenHasPlatilloPK getOrdenHasPlatilloPK() {
        return ordenHasPlatilloPK;
    }

    public void setOrdenHasPlatilloPK(OrdenHasPlatilloPK ordenHasPlatilloPK) {
        this.ordenHasPlatilloPK = ordenHasPlatilloPK;
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
        hash += (ordenHasPlatilloPK != null ? ordenHasPlatilloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenHasPlatillo)) {
            return false;
        }
        OrdenHasPlatillo other = (OrdenHasPlatillo) object;
        if ((this.ordenHasPlatilloPK == null && other.ordenHasPlatilloPK != null) || (this.ordenHasPlatilloPK != null && !this.ordenHasPlatilloPK.equals(other.ordenHasPlatilloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.OrdenHasPlatillo[ ordenHasPlatilloPK=" + ordenHasPlatilloPK + " ]";
    }
    
}
