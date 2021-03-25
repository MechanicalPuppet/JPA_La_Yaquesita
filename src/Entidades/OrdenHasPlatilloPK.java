/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Jbran
 */
@Embeddable
public class OrdenHasPlatilloPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "orden_idorden")
    private int ordenIdorden;
    @Basic(optional = false)
    @Column(name = "platillo_idplatillo")
    private int platilloIdplatillo;

    public OrdenHasPlatilloPK() {
    }

    public OrdenHasPlatilloPK(int ordenIdorden, int platilloIdplatillo) {
        this.ordenIdorden = ordenIdorden;
        this.platilloIdplatillo = platilloIdplatillo;
    }

    public int getOrdenIdorden() {
        return ordenIdorden;
    }

    public void setOrdenIdorden(int ordenIdorden) {
        this.ordenIdorden = ordenIdorden;
    }

    public int getPlatilloIdplatillo() {
        return platilloIdplatillo;
    }

    public void setPlatilloIdplatillo(int platilloIdplatillo) {
        this.platilloIdplatillo = platilloIdplatillo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ordenIdorden;
        hash += (int) platilloIdplatillo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenHasPlatilloPK)) {
            return false;
        }
        OrdenHasPlatilloPK other = (OrdenHasPlatilloPK) object;
        if (this.ordenIdorden != other.ordenIdorden) {
            return false;
        }
        if (this.platilloIdplatillo != other.platilloIdplatillo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.OrdenHasPlatilloPK[ ordenIdorden=" + ordenIdorden + ", platilloIdplatillo=" + platilloIdplatillo + " ]";
    }
    
}
