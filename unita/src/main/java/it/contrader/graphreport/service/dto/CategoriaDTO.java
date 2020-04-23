package it.contrader.graphreport.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import it.contrader.graphreport.domain.Unitamisura;

/**
 * A DTO for the Categoria entity.
 */
public class CategoriaDTO implements Serializable {

    private Long id;

    private String nome;
    
    private List<Unitamisura> unitamisuras = new ArrayList<Unitamisura>();

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
    
    public Collection<Unitamisura> getUnitamisuras() {
        return unitamisuras;
    }
    
    public void setUnitamisuras(List<Unitamisura> unitamisuras) {
        this.unitamisuras = unitamisuras;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoriaDTO categoriaDTO = (CategoriaDTO) o;
        if (categoriaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoriaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
