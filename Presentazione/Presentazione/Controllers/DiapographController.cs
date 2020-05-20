using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Presentazione.DTO;
using Presentazione.Models;
using Presentazione.Repository;
using Steeltoe.Common.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
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
            String jwt = Request.Headers["Authorization"].ToString().Replace("Bearer ","");
            IEnumerable<DiapographDTO> list = _repository.getAllByDiapositiva(id);
            foreach (DiapographDTO dto in list)
            {
                dto.graph = GetGraph(dto.graph.id,jwt).Result;
            }
            return list;
        }

        static async Task<GraphDTO> GetGraph(long id, String jwt )
        {
            GraphDTO graph = null;
            HttpClient client = new HttpClient();

            client.BaseAddress = new Uri("http://localhost:8080/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            client.DefaultRequestHeaders.Authorization =
                new AuthenticationHeaderValue("Bearer", jwt );

            HttpResponseMessage response = await client.GetAsync("graph/api/graphs/" + id);
            if (response.IsSuccessStatusCode)
            {
                graph = await response.Content.ReadAsJsonAsync<GraphDTO>();
            }
            return graph;
        }
    }
}
