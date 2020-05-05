using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.DTO
{
    public class DiapographDTO
    {
        public long id { get; set; }

        public string dimensione { get; set; }

        public string posizione { get; set; }

        public DiapositivaDTO diapositiva { get; set; }

        public long idGraph { get; set; }
    }
}
