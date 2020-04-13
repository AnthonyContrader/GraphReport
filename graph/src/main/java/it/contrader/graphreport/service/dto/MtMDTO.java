package it.contrader.graphreport.service.dto;
import java.io.Serializable;
import java.util.Objects;
import it.contrader.graphreport.domain.enumeration.TipoGrafico;

/**
 * A DTO for the MtM entity.
 */
public class MtMDTO implements Serializable {

    private Long id;

    private Long utente;

    private String asse;

    private TipoGrafico tipoSet;

    private String colore;


    private Long graphId;

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

    public String getAsse() {
        return asse;
    }

    public void setAsse(String asse) {
        this.asse = asse;
    }

    public TipoGrafico getTipoSet() {
        return tipoSet;
    }

    public void setTipoSet(TipoGrafico tipoSet) {
        this.tipoSet = tipoSet;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MtMDTO mtMDTO = (MtMDTO) o;
        if (mtMDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mtMDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MtMDTO{" +
            "id=" + getId() +
            ", utente=" + getUtente() +
            ", asse='" + getAsse() + "'" +
            ", tipoSet='" + getTipoSet() + "'" +
            ", colore='" + getColore() + "'" +
            ", graph=" + getGraphId() +
            "}";
    }
}
