using Microsoft.EntityFrameworkCore;
using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.DBContext
{
    public class DiapositivaContext : AContext<Diapositiva>
    {
        private static DbContextOptions<AContext<Diapositiva>> _option;
        public DiapositivaContext(DbContextOptions<AContext<Diapositiva>> option) : base(option) 
        {
            _option = option;
        }

    }
}
