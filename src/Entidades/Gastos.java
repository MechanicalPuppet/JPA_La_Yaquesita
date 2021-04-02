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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jbran
 */
@Entity
@Table(name = "gastos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g")
    , @NamedQuery(name = "Gastos.findByIdgastos", query = "SELECT g FROM Gastos g WHERE g.idgastos = :idgastos")
    , @NamedQuery(name = "Gastos.findByConcepto", query = "SELECT g FROM Gastos g WHERE g.concepto = :concepto")
    , @NamedQuery(name = "Gastos.findByCantidad", query = "SELECT g FROM Gastos g WHERE g.cantidad = :cantidad")})
public class Gastos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idgastos")
    private Integer idgastos;
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;

    public Gastos() {
    }

    public Gastos(Integer idgastos) {
        this.idgastos = idgastos;
    }

    public Gastos(Integer idgastos, String concepto, float cantidad) {
        this.idgastos = idgastos;
        this.concepto = concepto;
        this.cantidad = cantidad;
    }

    public Integer getIdgastos() {
        return idgastos;
    }

    public void setIdgastos(Integer idgastos) {
        this.idgastos = idgastos;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgastos != null ? idgastos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.idgastos == null && other.idgastos != null) || (this.idgastos != null && !this.idgastos.equals(other.idgastos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Gastos[ idgastos=" + idgastos + " ]";
    }
    
}
