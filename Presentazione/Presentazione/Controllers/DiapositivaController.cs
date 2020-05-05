using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Presentazione.DTO;
using Presentazione.Models;
using Presentazione.Repository;

namespace Presentazione.Controllers
{
    [EnableCors("AllowOrigin")]
    [Route("api/[controller]")]
    [ApiController]
    public class DiapositivaController : ControllerBase
    {

        private readonly ARepository<DiapositivaDTO,Diapositiva> _repository;
        public DiapositivaController(ARepository<DiapositivaDTO, Diapositiva> repository)
        {
            _repository = repository;
        }
        // GET: api/Diapositiva
        [HttpGet]
        public IEnumerable<DiapositivaDTO> Get()
        {
            return _repository.GetAll();
        }

        // GET: api/Diapositiva/5
        [HttpGet("{id}")]
        public DiapositivaDTO Get(long id)
        {
            return _repository.Read(id);
        }

        // POST: api/Diapositiva
        [HttpPut]
        public void Update([FromBody] DiapositivaDTO dto)
        {
            _repository.Update(dto);
        }

        // PUT: api/Diapositiva/5
        [HttpPost]
        public void Create([FromBody] DiapositivaDTO dto)
        {
            _repository.Create(dto);
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(long id)
        {
            _repository.Delete(id);
        }
    }
}
