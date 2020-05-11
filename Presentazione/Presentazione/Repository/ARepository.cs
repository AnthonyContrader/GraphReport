using Microsoft.EntityFrameworkCore;
using Presentazione.Converter;
using Presentazione.DBContext;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Repository
{
    public class ARepository<D,M> : IRepository<D,M> where M:class
    {
        private readonly AContext<M> _dbContext;
        private readonly AConverter<D, M> _converter;
        public ARepository(AContext<M> dbContext, AConverter<D, M> converter)
        {
            _dbContext = dbContext;
            _converter = converter;
        }

        public IEnumerable<D> GetAll()
        {
            List<D> listDTO = new List<D>();
            
            foreach(M model in _dbContext.Query)
            {
                listDTO.Add(_converter.ToDTO(model));
            }

            return listDTO;
        }

        public D Create(D dto)
        {
            M model = _converter.ToModel(dto);
            _dbContext.Entry(model).State= EntityState.Added;
            save();
            _dbContext.Entry(model).GetDatabaseValues();
            return _converter.ToDTO(model);
        }

        public D Read(long id)
        {
            return _converter.ToDTO(_dbContext.Query.Find(id));
        }

        public void Update(D dto)
        {
            M model = _converter.ToModel(dto);
            _dbContext.Entry(model).State = EntityState.Modified;
            save();
        }

        public void Delete(long id)
        {
            _dbContext.Query.Remove(_dbContext.Query.Find(id));
            save();
        }

        void save()
        {
            _dbContext.SaveChanges();
        }

    }
}
