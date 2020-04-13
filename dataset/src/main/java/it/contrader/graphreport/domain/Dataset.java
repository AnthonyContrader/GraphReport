package it.contrader.graphreport.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Dataset.
 */
@Entity
@Table(name = "dataset")
public class Dataset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "titolo", nullable = false)
    private String titolo;

    @NotNull
    @Column(name = "valori", nullable = false)
    private String valori;

    @Column(name = "commento")
    private String commento;

    @NotNull
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @NotNull
    @Column(name = "id_unita", nullable = false)
    private Long idUnita;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public Dataset titolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getValori() {
        return valori;
    }

    public Dataset valori(String valori) {
        this.valori = valori;
        return this;
    }

    public void setValori(String valori) {
        this.valori = valori;
    }

    public String getCommento() {
        return commento;
    }

    public Dataset commento(String commento) {
        this.commento = commento;
        return this;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Dataset idUser(Long idUser) {
        this.idUser = idUser;
        return this;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdUnita() {
        return idUnita;
    }

    public Dataset idUnita(Long idUnita) {
        this.idUnita = idUnita;
        return this;
    }

    public void setIdUnita(Long idUnita) {
        this.idUnita = idUnita;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dataset)) {
            return false;
        }
        return id != null && id.equals(((Dataset) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dataset{" +
            "id=" + getId() +
            ", titolo='" + getTitolo() + "'" +
            ", valori='" + getValori() + "'" +
            ", commento='" + getCommento() + "'" +
            ", idUser=" + getIdUser() +
            ", idUnita=" + getIdUnita() +
            "}";
    }
}
