using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.DTO
{
    public class GraphDTO
    {
        public long id { get; set; }

        public long utente { get; set; }

        public string tipoGrafico { get; set; }

        public bool titoloBool { get; set; }

        public string titolo { get; set; }

        public string fontStyle { get; set; }

        public string fontColor { get; set; }

        public int fontSize { get; set; }

        public string posTitolo { get; set; }

        public bool legenda { get; set; }

        public string posLegenda { get; set; }

        public bool pareto { get; set; }

        public bool mixed { get; set; }

        public DateTime created { get; set; }

        public DateTime modify { get; set; }
    }
}
