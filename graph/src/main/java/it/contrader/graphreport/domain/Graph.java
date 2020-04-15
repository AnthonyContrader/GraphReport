package it.contrader.graphreport.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import it.contrader.graphreport.domain.enumeration.TipoGrafico;

import it.contrader.graphreport.domain.enumeration.FontStyle;

/**
 * A Graph.
 */
@Entity
@Table(name = "graph")
public class Graph implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "utente")
    private Long utente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_grafico")
    private TipoGrafico tipoGrafico;

    @Column(name = "titolo_bool")
    private Boolean titoloBool;

    @Column(name = "titolo")
    private String titolo;

    @Enumerated(EnumType.STRING)
    @Column(name = "font_style")
    private FontStyle fontStyle;

    @Column(name = "font_size")
    private Integer fontSize;

    @Column(name = "pos_titolo")
    private String posTitolo;

    @Column(name = "legenda")
    private Boolean legenda;

    @Column(name = "pos_legenda")
    private String posLegenda;

    @Column(name = "pareto")
    private Boolean pareto;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "jhi_modify")
    private LocalDate modify;

    @OneToMany(mappedBy = "graph")
    private Set<MtM> mtMS = new HashSet<>();
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

    public Graph utente(Long utente) {
        this.utente = utente;
        return this;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }

    public TipoGrafico getTipoGrafico() {
        return tipoGrafico;
    }

    public Graph tipoGrafico(TipoGrafico tipoGrafico) {
        this.tipoGrafico = tipoGrafico;
        return this;
    }

    public void setTipoGrafico(TipoGrafico tipoGrafico) {
        this.tipoGrafico = tipoGrafico;
    }

    public Boolean isTitoloBool() {
        return titoloBool;
    }

    public Graph titoloBool(Boolean titoloBool) {
        this.titoloBool = titoloBool;
        return this;
    }

    public void setTitoloBool(Boolean titoloBool) {
        this.titoloBool = titoloBool;
    }

    public String getTitolo() {
        return titolo;
    }

    public Graph titolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public FontStyle getFontStyle() {
        return fontStyle;
    }

    public Graph fontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public void setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public Graph fontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getPosTitolo() {
        return posTitolo;
    }

    public Graph posTitolo(String posTitolo) {
        this.posTitolo = posTitolo;
        return this;
    }

    public void setPosTitolo(String posTitolo) {
        this.posTitolo = posTitolo;
    }

    public Boolean isLegenda() {
        return legenda;
    }

    public Graph legenda(Boolean legenda) {
        this.legenda = legenda;
        return this;
    }

    public void setLegenda(Boolean legenda) {
        this.legenda = legenda;
    }

    public String getPosLegenda() {
        return posLegenda;
    }

    public Graph posLegenda(String posLegenda) {
        this.posLegenda = posLegenda;
        return this;
    }

    public void setPosLegenda(String posLegenda) {
        this.posLegenda = posLegenda;
    }

    public Boolean isPareto() {
        return pareto;
    }

    public Graph pareto(Boolean pareto) {
        this.pareto = pareto;
        return this;
    }

    public void setPareto(Boolean pareto) {
        this.pareto = pareto;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Graph created(LocalDate created) {
        this.created = created;
        return this;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModify() {
        return modify;
    }

    public Graph modify(LocalDate modify) {
        this.modify = modify;
        return this;
    }

    public void setModify(LocalDate modify) {
        this.modify = modify;
    }

    public Set<MtM> getMtMS() {
        return mtMS;
    }

    public Graph mtMS(Set<MtM> mtMS) {
        this.mtMS = mtMS;
        return this;
    }

    public Graph addMtM(MtM mtM) {
        this.mtMS.add(mtM);
        mtM.setGraph(this);
        return this;
    }

    public Graph removeMtM(MtM mtM) {
        this.mtMS.remove(mtM);
        mtM.setGraph(null);
        return this;
    }

    public void setMtMS(Set<MtM> mtMS) {
        this.mtMS = mtMS;
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
        Graph graph = (Graph) o;
        if (graph.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), graph.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Graph{" +
            "id=" + getId() +
            ", utente=" + getUtente() +
            ", tipoGrafico='" + getTipoGrafico() + "'" +
            ", titoloBool='" + isTitoloBool() + "'" +
            ", titolo='" + getTitolo() + "'" +
            ", fontStyle='" + getFontStyle() + "'" +
            ", fontSize=" + getFontSize() +
            ", posTitolo='" + getPosTitolo() + "'" +
            ", legenda='" + isLegenda() + "'" +
            ", posLegenda='" + getPosLegenda() + "'" +
            ", pareto='" + isPareto() + "'" +
            ", created='" + getCreated() + "'" +
            ", modify='" + getModify() + "'" +
            "}";
    }
}
