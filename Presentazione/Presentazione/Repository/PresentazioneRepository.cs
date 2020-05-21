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
    public class PresentazioneRepository : ARepository<PresentazioneDTO, PresModel>
    {
        private readonly AConverter<PresentazioneDTO,PresModel> _converter;
        private readonly AContext<PresModel> _context;

        public PresentazioneRepository(AConverter<PresentazioneDTO, PresModel> converter, AContext<PresModel> context) : base(context, converter)
        {
            _converter = converter;
            _context = context;
        } 

        public IEnumerable<PresentazioneDTO> GetAllByUser(long id)
        {
            return _converter.toListDTO(_context.Query.Where(r => r.utente == id).OrderBy(r => r.nome));
        }

        public void DeleteByUtente(long id)
        {
            var Z = _context.Query.Where(x => x.utente == id);
            foreach (var element in Z) {
                _context.Query.Remove(_context.Query.Find(element.id));
            }
   
            save();
        }

        void save()
        {
            _context.SaveChanges();
        }

    }
}
