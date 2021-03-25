/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jbran
 */
@Entity
@Table(name = "ingredientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredientes.findAll", query = "SELECT i FROM Ingredientes i")
    , @NamedQuery(name = "Ingredientes.findByIdingredientes", query = "SELECT i FROM Ingredientes i WHERE i.idingredientes = :idingredientes")
    , @NamedQuery(name = "Ingredientes.findByNombre", query = "SELECT i FROM Ingredientes i WHERE i.nombre = :nombre")})
public class Ingredientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idingredientes")
    private Integer idingredientes;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "platillo_has_ingredientes", joinColumns = {
        @JoinColumn(name = "ingredientes_idingredientes", referencedColumnName = "idingredientes")}, inverseJoinColumns = {
        @JoinColumn(name = "platillo_idplatillo", referencedColumnName = "idplatillo")})
    @ManyToMany
    private List<Platillo> platilloList;

    public Ingredientes() {
    }

    public Ingredientes(Integer idingredientes) {
        this.idingredientes = idingredientes;
    }

    public Ingredientes(Integer idingredientes, String nombre) {
        this.idingredientes = idingredientes;
        this.nombre = nombre;
    }

    public Integer getIdingredientes() {
        return idingredientes;
    }

    public void setIdingredientes(Integer idingredientes) {
        this.idingredientes = idingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Platillo> getPlatilloList() {
        return platilloList;
    }

    public void setPlatilloList(List<Platillo> platilloList) {
        this.platilloList = platilloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idingredientes != null ? idingredientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredientes)) {
            return false;
        }
        Ingredientes other = (Ingredientes) object;
        if ((this.idingredientes == null && other.idingredientes != null) || (this.idingredientes != null && !this.idingredientes.equals(other.idingredientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Ingredientes[ idingredientes=" + idingredientes + " ]";
    }
    
}
