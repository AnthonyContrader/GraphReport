package it.contrader.graphreport.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import it.contrader.graphreport.domain.enumeration.TipoGrafico;

/**
 * A MtM.
 */
@Entity
@Table(name = "mt_m")
public class MtM implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "utente")
    private Long utente;

    @Column(name = "asse")
    private String asse;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_set")
    private TipoGrafico tipoSet;

    @Column(name = "colore")
    private String colore;

    @ManyToOne
    @JsonIgnoreProperties("mtMS")
    private Graph graph;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtente() {
        return utente;
    }

    public MtM utente(Long utente) {
        this.utente = utente;
        return this;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }

    public String getAsse() {
        return asse;
    }

    public MtM asse(String asse) {
        this.asse = asse;
        return this;
    }

    public void setAsse(String asse) {
        this.asse = asse;
    }

    public TipoGrafico getTipoSet() {
        return tipoSet;
    }

    public MtM tipoSet(TipoGrafico tipoSet) {
        this.tipoSet = tipoSet;
        return this;
    }

    public void setTipoSet(TipoGrafico tipoSet) {
        this.tipoSet = tipoSet;
    }

    public String getColore() {
        return colore;
    }

    public MtM colore(String colore) {
        this.colore = colore;
        return this;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Graph getGraph() {
        return graph;
    }

    public MtM graph(Graph graph) {
        this.graph = graph;
        return this;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
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
        MtM mtM = (MtM) o;
        if (mtM.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mtM.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MtM{" +
            "id=" + getId() +
            ", utente=" + getUtente() +
            ", asse='" + getAsse() + "'" +
            ", tipoSet='" + getTipoSet() + "'" +
            ", colore='" + getColore() + "'" +
            "}";
    }
}
