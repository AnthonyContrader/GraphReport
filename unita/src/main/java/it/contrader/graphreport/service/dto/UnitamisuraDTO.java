package it.contrader.graphreport.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Unitamisura entity.
 */
public class UnitamisuraDTO implements Serializable {

    private Long id;

    private String nome;

    private Long categoriaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UnitamisuraDTO unitamisuraDTO = (UnitamisuraDTO) o;
        if (unitamisuraDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), unitamisuraDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UnitamisuraDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", categoria=" + getCategoriaId() +
            "}";
    }
}
