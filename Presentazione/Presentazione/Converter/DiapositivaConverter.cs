using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

using Presentazione.Models;
using Presentazione.DTO;

namespace Presentazione.Converter
{
    public class DiapositivaConverter : AConverter<DiapositivaDTO,Diapositiva>
    {

        public override DiapositivaDTO ToDTO(Diapositiva model)
        {
            DiapositivaDTO dto = new DiapositivaDTO();
            dto.presentazione = new PresentazioneDTO();
            dto.id = model.id;
            dto.presentazione.id = model.presentazioneid;
            //dto.presentazione.nome = model.presentazione.nome;
            //dto.presentazione.dataCreazione = model.presentazione.ultimaModifica;
            //dto.presentazione.ultimaModifica = model.presentazione.ultimaModifica;
            dto.sfondo = new Colore(model.sfondo);
            dto.ordine = model.ordine;
            dto.preset = model.preset;
            dto.ratio = model.ratio;
            dto.isTitolo = model.isTitolo;
            dto.titolo = model.titolo;
            dto.posizioneT = model.posizioneT;
            dto.dimensioneT = model.dimensioneT;
            dto.coloreT = new Colore(model.coloreT);
            dto.borderColor = new Colore(model.borderColor);
            dto.borderSize = model.borderSize;
            dto.fontFamily = model.fontFamily;
            dto.fontRotation = model.fontRotation;
            dto.fontStyle = model.fontStyle;

            return dto;
        }

        public override Diapositiva ToModel(DiapositivaDTO dto)
        {
            Diapositiva model = new Diapositiva();
            model.presentazione = new PresModel();
            model.id = dto.id;
            model.presentazioneid = dto.presentazione.id;
            //model.presentazione.id = dto.presentazione.id;
            model.sfondo = dto.sfondo.toHEX();
            model.ordine = dto.ordine;
            model.preset = dto.preset;
            model.ratio = dto.ratio;
            model.isTitolo = dto.isTitolo;
            model.titolo = dto.titolo;
            model.posizioneT = dto.posizioneT;
            model.dimensioneT = dto.dimensioneT;
            model.coloreT = dto.coloreT.toHEX();
            model.borderColor = dto.borderColor.toHEX();
            model.borderSize = dto.borderSize;
            model.fontFamily = dto.fontFamily;
            model.fontRotation = dto.fontRotation;
            model.fontStyle = dto.fontStyle;

            return model;
        }

    }
}
