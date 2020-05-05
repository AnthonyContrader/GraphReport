using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.DTO
{
    public class TestoDTO
    {
        public long id { get; set; }

        public string text { get; set; }

        public string dimensione { get; set; }

        public string posizione { get; set; }

        public int fontSize { get; set; }

        public string colore { get; set; }

        public int fontStyle { get; set; }

        public DiapositivaDTO diapositiva { get; set; }
    }
}
