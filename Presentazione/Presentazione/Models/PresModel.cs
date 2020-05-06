using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace Presentazione.Models
{
    [Table("Presentazione")]
    [Serializable]
    public class PresModel
    {

        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long id { get; set; }

        [Required]
        public string nome { get; set; }

        [Required]
        public DateTime dataCreazione { get; set; }

        [Required]
        public DateTime ultimaModifica { get; set; }

    }
}
