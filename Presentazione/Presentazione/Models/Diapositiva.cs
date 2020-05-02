using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace Presentazione.Models
{
    [Table("diapositiva")]
    [Serializable]
    public class Diapositiva
    {

        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long id { get; set; }

        [Required]
        public string sfondo { get; set; }

        [Required]
        public int ordine { get; set; }

        [Required]
        public Boolean preset { get; set; }

        [Required]
        public string ratio { get; set; }

        [Required]
        public Boolean isTitolo { get; set; }

        public string titolo { get; set; }

        public string posizioneT { get; set; }

        public string dimensioneT { get; set; }

        public string coloreT { get; set; }

    }
}
