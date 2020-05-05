using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace Presentazione.Models
{
    
    public class PresentazioneDTO
    {

        public long id { get; set; }

        public string nome { get; set; }

        public DateTime dataCreazione { get; set; }

        public DateTime ultimaModifica { get; set; }

    }
}
