using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Models
{
    [Table("Testo")]
    [Serializable]
    public class Testo
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long id { get; set; }

        [Required]
        public string text { get; set; }

        [Required]
        public string dimensione { get; set; }

        [Required]
        public string posizione { get; set; }

        [Required]
        public int fontSize { get; set; }

        [Required]
        public string colore { get; set; }

        [Required]
        public int fontStyle { get; set; }

        public Diapositiva diapositiva { get; set; }
        [Required]
        public long diapositivaid { get; set; }


    }
}
