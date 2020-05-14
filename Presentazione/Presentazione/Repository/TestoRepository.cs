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
    public class TestoRepository : ARepository<TestoDTO, Testo>
    {
        private readonly AConverter<TestoDTO, Testo> _converter;
        private readonly AContext<Testo> _context;

        public TestoRepository(AConverter<TestoDTO, Testo> converter, AContext<Testo> context) : base(context, converter)
        {
            _converter = converter;
            _context = context;
        }

        public IEnumerable<TestoDTO> getAllByDiapositiva(long id)
        {
            return _converter.toListDTO(_context.Query.Where(r => r.diapositivaid == id));
        }

    }
}
