using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Repository
{
    public interface IRepository<D,M>
    {

        public IEnumerable<D> GetAll();
        public D Create(D dto);
        public D Read(long id);
        public void Update(D dto);
        public void Delete(long id);
    }
}
