using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Http;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Presentazione.Converter;
using Presentazione.DBContext;
using Presentazione.DTO;
using Presentazione.Models;
using Presentazione.Repository;
using Steeltoe.Discovery.Client;
using System;

namespace Presentazione
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();

            services.AddDbContext<AContext<Diapositiva>>(z => z.UseSqlServer(Configuration.GetConnectionString("DefaultConnection")));
            services.AddTransient<AConverter<DiapositivaDTO,Diapositiva>, DiapositivaConverter>();
            services.AddTransient<ARepository<DiapositivaDTO,Diapositiva>, DiapositivaRepository>();

            services.AddDbContext<AContext<PresModel>>(z => z.UseSqlServer(Configuration.GetConnectionString("DefaultConnection")));
            services.AddTransient<AConverter<PresentazioneDTO, PresModel>, PresentazioneConverter>();
            services.AddTransient<ARepository<PresentazioneDTO, PresModel>, PresentazioneRepository>();

            services.AddDbContext<AContext<Testo>>(z => z.UseSqlServer(Configuration.GetConnectionString("DefaultConnection")));
            services.AddTransient<AConverter<TestoDTO, Testo>, TestoConverter>();
            services.AddTransient<ARepository<TestoDTO, Testo>, TestoRepository>();

            services.AddDbContext<AContext<Diapograph>>(z => z.UseSqlServer(Configuration.GetConnectionString("DefaultConnection")));
            services.AddTransient<AConverter<DiapographDTO, Diapograph>, DiapographConverter>();
            services.AddTransient<ARepository<DiapographDTO, Diapograph>, DiapographRepository>();

            services.AddCors(options =>
            {
                options.AddPolicy("AllowOrigin",
                    builder => builder.WithOrigins("http://localhost:8080"));
            });

            services.AddSingleton<IHttpContextAccessor, HttpContextAccessor>();

            services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme);

            services.AddAuthorization();

            services.AddSwaggerDocument();

            services.AddDiscoveryClient(Configuration);

        }

        private int AContext<T>(Func<object, object> p)
        {
            throw new NotImplementedException();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, ILoggerFactory loggerFactory)
        {
            
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseRouting();

            app.UseCors();

            app.UseAuthorization();

            app.UseOpenApi();
            app.UseSwaggerUi3();

            app.UseDiscoveryClient();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
