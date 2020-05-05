using Microsoft.JSInterop.Infrastructure;
using Presentazione.DTO;
using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Converter
{
    public class DiapographConverter : AConverter<DiapographDTO, Diapograph>
    {
        public override DiapographDTO ToDTO(Diapograph model)
        {
            DiapographDTO dto = new DiapographDTO();
            dto.id = model.id;
            dto.dimensione = model.dimensione;
            dto.posizione = model.posizione;
            dto.diapositiva = new DiapositivaDTO();
            dto.diapositiva.id = model.diapositiva.id;
            dto.idGraph = model.idGraph;

            return dto;
        }

        public override Diapograph ToModel(DiapographDTO dto)
        {
            Diapograph model = new Diapograph();
            model.id = dto.id;
            model.dimensione = dto.dimensione;
            model.posizione = dto.posizione;
            model.diapositiva = new Diapositiva();
            model.diapositiva.id = dto.diapositiva.id;
            model.idGraph = dto.idGraph;

            return model;
        }
    }
}
