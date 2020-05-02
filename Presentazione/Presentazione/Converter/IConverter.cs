using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Converter
{
    public interface IConverter<D,M>
    {

        D ToDTO(M model);
        M ToModel(D dto);


    }
}
