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
        private readonly AConverter<DiapositivaDTO,Diapositiva> _converter;
        private readonly AContext<Diapositiva> _context;

        public DiapositivaRepository(AConverter<DiapositivaDTO, Diapositiva> converter, AContext<Diapositiva> context) : base(context, converter)
        {
            _converter = converter;
            _context = context;
        } 

    }
}
