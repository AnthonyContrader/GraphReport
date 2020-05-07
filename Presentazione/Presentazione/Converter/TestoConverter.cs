using Presentazione.DTO;
using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Converter
{
    public class TestoConverter : AConverter<TestoDTO, Testo>
    {
        public override TestoDTO ToDTO(Testo model)
        {
            TestoDTO dto = new TestoDTO();
            dto.id = model.id;
            dto.text = model.text;
            dto.posizione = model.posizione;
            dto.colore = model.colore;
            dto.dimensione = model.dimensione;
            dto.fontSize = model.fontSize;
            dto.fontStyle = model.fontStyle;
            dto.diapositiva = new DiapositivaDTO();
            dto.diapositiva.id = model.diapositivaid;

            return dto;
        }

        public override Testo ToModel(TestoDTO dto)
        {
            Testo model = new Testo();
            model.id = dto.id;
            model.text = dto.text;
            model.posizione = dto.posizione;
            model.colore = dto.colore;
            model.dimensione = dto.dimensione;
            model.fontSize = dto.fontSize;
            model.fontStyle = dto.fontStyle;
            model.diapositiva = new Diapositiva();
            model.diapositiva.id = dto.diapositiva.id;

            return model;
        }
    }
}
