package it.contrader.graphreport.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import it.contrader.graphreport.domain.enumeration.TipoGrafico;
import it.contrader.graphreport.domain.enumeration.FontStyle;

/**
 * A DTO for the Graph entity.
 */
public class GraphDTO implements Serializable {

    private Long id;

    private Long utente;

    private TipoGrafico tipoGrafico;

    private Boolean titoloBool;

    private String titolo;

    private FontStyle fontStyle;
    
    private String fontColor;

    private Integer fontSize;

    private String posTitolo;

    private Boolean legenda;

    private String posLegenda;

    private Boolean pareto;
    
    private Boolean mixed;

    private LocalDate created;

    private LocalDate modify;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtente() {
        return utente;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }

    public TipoGrafico getTipoGrafico() {
        return tipoGrafico;
    }

    public void setTipoGrafico(TipoGrafico tipoGrafico) {
        this.tipoGrafico = tipoGrafico;
    }

    public Boolean isTitoloBool() {
        return titoloBool;
    }

    public void setTitoloBool(Boolean titoloBool) {
        this.titoloBool = titoloBool;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public FontStyle getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getPosTitolo() {
        return posTitolo;
    }

    public void setPosTitolo(String posTitolo) {
        this.posTitolo = posTitolo;
    }

    public Boolean isLegenda() {
        return legenda;
    }

    public void setLegenda(Boolean legenda) {
        this.legenda = legenda;
    }

    public String getPosLegenda() {
        return posLegenda;
    }

    public void setPosLegenda(String posLegenda) {
        this.posLegenda = posLegenda;
    }

    public Boolean isPareto() {
        return pareto;
    }

    public void setPareto(Boolean pareto) {
        this.pareto = pareto;
    }
    
    public void setMixed(Boolean mixed) {
        this.mixed = mixed;
    }
    
    public Boolean getMixed() {
        return this.mixed;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModify() {
        return modify;
    }

    public void setModify(LocalDate modify) {
        this.modify = modify;
    }
    
    public String getFontColor() {
    	return this.fontColor;
    }
    
    public void setFontColor(String color) {
    	this.fontColor = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GraphDTO graphDTO = (GraphDTO) o;
        if (graphDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), graphDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GraphDTO{" +
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
