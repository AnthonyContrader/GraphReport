using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Converter
{
    public abstract class AConverter<D,M> : IConverter<D,M>
    {

        public abstract M ToModel(D dto);

        public abstract D ToDTO(M model);

        public List<D> toListDTO(IEnumerable<M> modelList)
        {
            List<D> listDTO = new List<D>();
            foreach (M d in modelList)
                listDTO.Add(ToDTO(d));
            return listDTO;

        }

        public List<M> toListModel(IEnumerable<D> dtoList)
        {
            List<M> listModel = new List<M>();
            foreach (D d in dtoList)
                listModel.Add(ToModel(d));
            return listModel;

        }


    }
}
