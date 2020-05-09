using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;
using Presentazione.Models;

namespace Presentazione.DTO
{
    public class DiapositivaDTO
    {
        public long id { get; set; }

        public PresentazioneDTO presentazione { get; set; }

        public Colore sfondo { get; set; }
        
        public int ordine { get; set; }

        public Boolean preset { get; set; }

        public string ratio { get; set; }

        public Boolean isTitolo { get; set; }

        public string titolo { get; set; }

        public string posizioneT { get; set; }

        public int dimensioneT { get; set; }

        public Colore coloreT { get; set; }

        public int fontFamily { get; set; }

        public string fontStyle { get; set; }

        public int fontRotation { get; set; }

        public int borderSize { get; set; }

        public Colore borderColor { get; set; }

    }
}
