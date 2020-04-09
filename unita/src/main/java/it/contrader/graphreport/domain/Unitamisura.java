package it.contrader.graphreport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Unitamisura.
 */
@Entity
@Table(name = "unitamisura")
public class Unitamisura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Categoria unicat;

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

    public Unitamisura nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getUnicat() {
        return unicat;
    }

    public Unitamisura unicat(Categoria categoria) {
        this.unicat = categoria;
        return this;
    }

    public void setUnicat(Categoria categoria) {
        this.unicat = categoria;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Unitamisura unitamisura = (Unitamisura) o;
        if (unitamisura.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), unitamisura.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Unitamisura{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
