using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Presentazione.DTO;
using Presentazione.Models;
using Presentazione.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Controllers
{
    [ApiController]
    [Route("api/diapograph")]
    [EnableCors("AllowOrigin")]
    public class DiapographController : ControllerBase
    {
        private readonly DiapographRepository _repository;
        public DiapographController(ARepository<DiapographDTO, Diapograph> repository)
        {
            _repository = (DiapographRepository) repository;
        }

        [HttpGet]
        public IEnumerable<DiapographDTO> Get()
        {
            return _repository.GetAll();
        }

        [HttpGet("{id}")]
        public DiapographDTO Read(long id)
        {
            return _repository.Read(id);
        }

        [HttpPut]
        public void Update([FromBody] DiapographDTO dto)
        {
            _repository.Update(dto);
        }

        [HttpPost]
        public void Create([FromBody] DiapographDTO dto)
        {
            _repository.Create(dto);
        }

        [HttpDelete("{id}")]
        public void Delete(long id)
        {
            _repository.Delete(id);
        }

        [HttpGet("byDiapositiva/{id}")]
        public IEnumerable<DiapographDTO> getByDiapositiva(long id)
        {
            return _repository.getAllByDiapositiva(id);
        }
    }
}
