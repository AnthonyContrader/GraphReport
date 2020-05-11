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
    [Route("api/presentazione")]
    [ApiController]
    public class SlideController : ControllerBase
    {

        private readonly PresentazioneRepository _repository;
        public SlideController(ARepository<PresentazioneDTO, PresModel> repository)
        {
            _repository = (PresentazioneRepository)repository;
        }

        [HttpGet]
        public IEnumerable<PresentazioneDTO> Get()
        {
            return _repository.GetAll();
        }

        [HttpGet("{id}")]
        public PresentazioneDTO Read(long id)
        {
            return _repository.Read(id);
        }

        [HttpPut]
        public void Update([FromBody] PresentazioneDTO dto)
        {
            _repository.Update(dto);
        }

        [HttpPost]
        public void Create([FromBody] PresentazioneDTO dto)
        {
            _repository.Create(dto);
        }

        [HttpDelete("{id}")]
        public void Delete(long id)
        {
            _repository.Delete(id);
        }

        [HttpGet("byUser/{id}")]
        public IEnumerable<PresentazioneDTO> GetAllByUser(long id)
        {
            return _repository.GetAllByUser(id);
        }
    }
}
