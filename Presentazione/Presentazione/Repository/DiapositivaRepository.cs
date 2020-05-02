using Microsoft.EntityFrameworkCore;
using Presentazione.Converter;
using Presentazione.DBContext;
using Presentazione.DTO;
using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Repository
{
    public class DiapositivaRepository : ARepository<DiapositivaDTO, Diapositiva>
    {
        private static readonly DiapositivaConverter converter;
        private static readonly AContext<Diapositiva> context;

        public DiapositivaRepository() : base(context, converter)
        {
            
        } 

    }
}
