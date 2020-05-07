using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Presentazione.Models;
using Presentazione.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Presentazione.DTO;

namespace Presentazione.Controllers
{
    [EnableCors("AllowOrigin")]
    [Route("api/testo")]
    [ApiController]
    public class TestoController : ControllerBase
    {
        private readonly ARepository<TestoDTO, Testo> _repository;
        public TestoController(ARepository<TestoDTO, Testo> repository)
        {
            _repository = repository;
        }

        [HttpGet]
        public IEnumerable<TestoDTO> Get()
        {
            return _repository.GetAll();
        }

        [HttpGet("{id}")]
        public TestoDTO Read(long id)
        {
            return _repository.Read(id);
        }

        [HttpPut]
        public void Update([FromBody] TestoDTO dto)
        {
            _repository.Update(dto);
        }

        [HttpPost]
        public void Create([FromBody] TestoDTO dto)
        {
            _repository.Create(dto);
        }

        [HttpDelete("{id}")]
        public void Delete(long id)
        {
            _repository.Delete(id);
        }
    }
}
