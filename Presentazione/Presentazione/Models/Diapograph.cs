using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Models
{
    [Table("DiapoGraph")]
    [Serializable]
    public class Diapograph
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long id { get; set; }

        [Required]
        public string dimensione { get; set; }

        [Required]
        public string posizione { get; set; }

        [Required]
        public Diapositiva diapositiva { get; set; }

        public long idGraph { get; set; }



    }
}
