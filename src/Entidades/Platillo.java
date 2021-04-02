/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jbran
 */
@Entity
@Table(name = "platillo")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Platillo.findAll", query = "SELECT p FROM Platillo p")
    , @NamedQuery(name = "Platillo.findByIdplatillo", query = "SELECT p FROM Platillo p WHERE p.idplatillo = :idplatillo")
    , @NamedQuery(name = "Platillo.findByTipoProducto", query = "SELECT p FROM Platillo p WHERE p.tipoProducto = :tipoProducto")
    , @NamedQuery(name = "Platillo.findByNombre", query = "SELECT p FROM Platillo p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Platillo.findByDescripcion", query = "SELECT p FROM Platillo p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Platillo.findByCosto", query = "SELECT p FROM Platillo p WHERE p.costo = :costo")})
public class Platillo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplatillo")
    private Integer idplatillo;
    @Basic(optional = false)
    @Column(name = "tipoProducto")
    private String tipoProducto;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "costo")
    private float costo;
    @ManyToMany(mappedBy = "platilloList")
    private List<Ingredientes> ingredientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "platillo")
    private List<OrdenHasPlatillo> ordenHasPlatilloList;

    public Platillo() {
    }

    public Platillo(Integer idplatillo) {
        this.idplatillo = idplatillo;
    }

    public Platillo(Integer idplatillo, String tipoProducto, String nombre, float costo) {
        this.idplatillo = idplatillo;
        this.tipoProducto = tipoProducto;
        this.nombre = nombre;
        this.costo = costo;
    }

    public Integer getIdplatillo() {
        return idplatillo;
    }

    public void setIdplatillo(Integer idplatillo) {
        this.idplatillo = idplatillo;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    //@XmlTransient
    public List<Ingredientes> getIngredientesList() {
        return ingredientesList;
    }

    public void setIngredientesList(List<Ingredientes> ingredientesList) {
        this.ingredientesList = ingredientesList;
    }

    //@XmlTransient
    public List<OrdenHasPlatillo> getOrdenHasPlatilloList() {
        return ordenHasPlatilloList;
    }

    public void setOrdenHasPlatilloList(List<OrdenHasPlatillo> ordenHasPlatilloList) {
        this.ordenHasPlatilloList = ordenHasPlatilloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplatillo != null ? idplatillo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Platillo)) {
            return false;
        }
        Platillo other = (Platillo) object;
        if ((this.idplatillo == null && other.idplatillo != null) || (this.idplatillo != null && !this.idplatillo.equals(other.idplatillo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre + "\t" +"$"+costo +" \n" +
                formatIngredientes();
    }

    public String formatIngredientes() { // Brandon hizo el cambio a esto el 01/04/2021
        String ingredientes = "";
        if(ingredientesList.size() == 11){
            return "CT";
        } else {
        for (Ingredientes ingrediente : ingredientesList) {
            ingredientes = ingredientes.concat("\t" +ingrediente.getNombre() +"\n");
        }
        
        return ingredientes;
        }
    }

}
