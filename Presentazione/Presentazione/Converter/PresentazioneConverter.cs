using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

using Presentazione.Models;
using Presentazione.DTO;

namespace Presentazione.Converter
{
    public class PresentazioneConverter : AConverter<PresentazioneDTO, PresModel>
    {

        public override PresentazioneDTO ToDTO(PresModel model)
        {
            PresentazioneDTO dto = new PresentazioneDTO();
            dto.id = model.id;
            dto.nome = model.nome;
            dto.dataCreazione = model.dataCreazione;
            dto.ultimaModifica = model.ultimaModifica;
            
            return dto;
        }

        public override PresModel ToModel(PresentazioneDTO dto)
        {
            PresModel model = new PresModel();
            model.id = dto.id;
            model.nome = dto.nome;
            model.dataCreazione = dto.ultimaModifica;
            model.ultimaModifica = dto.ultimaModifica;

            return model;
        }

    }
}
