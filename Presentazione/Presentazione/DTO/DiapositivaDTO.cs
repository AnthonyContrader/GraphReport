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

        public PresModel presentazione { get; set; }

        public Colore sfondo { get; set; }

        public int ordine { get; set; }

        public Boolean preset { get; set; }

        public string ratio { get; set; }

        public Boolean isTitolo { get; set; }

        public string titolo { get; set; }

        public string posizioneT { get; set; }

        public string dimensioneT { get; set; }

        public Colore coloreT { get; set; }
    }
}
