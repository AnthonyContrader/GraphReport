package it.contrader.graphreport.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

/**
 * A Categoria.
 */
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    //@OneToMany(mappedBy = "categoria")
    //private List<Unitamisura> unitamisuras;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public Collection<Unitamisura> getUnitamisuras() {
//        return unitamisuras;
//    }
//
//    public Categoria unitamisuras(List<Unitamisura> unitamisuras) {
//        this.unitamisuras = unitamisuras;
//        return this;
//    }
//
//    public Categoria addUnitamisura(Unitamisura unitamisura) {
//        this.unitamisuras.add(unitamisura);
//        unitamisura.setCategoria(this);
//        return this;
//    }
//
//    public Categoria removeUnitamisura(Unitamisura unitamisura) {
//        this.unitamisuras.remove(unitamisura);
//        unitamisura.setCategoria(null);
//        return this;
//    }
//
//    public void setUnitamisuras(List<Unitamisura> unitamisuras) {
//        this.unitamisuras = unitamisuras;
//    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Categoria categoria = (Categoria) o;
        if (categoria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Categoria{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
