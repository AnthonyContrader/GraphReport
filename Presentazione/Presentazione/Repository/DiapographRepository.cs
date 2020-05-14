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
    public class DiapographRepository : ARepository<DiapographDTO, Diapograph>
    {
        private readonly AConverter<DiapographDTO, Diapograph> _converter;
        private readonly AContext<Diapograph> _context;

        public DiapographRepository(AConverter<DiapographDTO, Diapograph> converter, AContext<Diapograph> context) : base(context, converter)
        {
            _converter = converter;
            _context = context;
        }

        public IEnumerable<DiapographDTO> getAllByDiapositiva(long id)
        {
            return _converter.toListDTO(_context.Query.Where(r => r.diapositivaid == id));
        }
    }
}
