using Microsoft.EntityFrameworkCore;
using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

// CREAZIONE CONTESTO IN CUI DEVE AGIRE ENTITY FRAMEWORK, OSSIA QUALI CLASSI DOVRANNO ESSERE UTILIZZATE PER LA CREAZIONE E LA SUCCESSIVA
// GESTIONE DEL DATABASE. IN QUESTO CASO VERRANNO MAPPATI I DBSET DELLE ENTITA' PAGAMENTO E PAGAMENTOACQUISTO

namespace Presentazione.DBContexts
{
    public class IlBelloContext : DbContext
    {
        public IlBelloContext(DbContextOptions<IlBelloContext> options) : base(options)
        {
        }
        public virtual DbSet<Diapositiva> Diapositiva { get; set; }
        public virtual DbSet<Testo> Testo { get; set; }
        public virtual DbSet<Diapograph> Diapograph { get; set; }
        public virtual DbSet<PresModel> PresModels { get; set; }



        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            //modelBuilder.Entity<Diapositiva>().ToTable("Diapositiva");
            //modelBuilder.Entity<Testo>().ToTable("Testo");
            //modelBuilder.Entity<Diapograph>().ToTable("Diapograph");
            modelBuilder.Entity<PresModel>().ToTable("presentazione");
        }
    }
}
