package it.contrader.graphreport.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link it.contrader.graphreport.domain.Dataset} entity.
 */
public class DatasetDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String titolo;

    @NotNull
    private String valori;

    private String commento;

    @NotNull
    private Long idUser;

    @NotNull
    private Long idUnita;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getValori() {
        return valori;
    }

    public void setValori(String valori) {
        this.valori = valori;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdUnita() {
        return idUnita;
    }

    public void setIdUnita(Long idUnita) {
        this.idUnita = idUnita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DatasetDTO datasetDTO = (DatasetDTO) o;
        if (datasetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), datasetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DatasetDTO{" +
            "id=" + getId() +
            ", titolo='" + getTitolo() + "'" +
            ", valori='" + getValori() + "'" +
            ", commento='" + getCommento() + "'" +
            ", idUser=" + getIdUser() +
            ", idUnita=" + getIdUnita() +
            "}";
    }
}
