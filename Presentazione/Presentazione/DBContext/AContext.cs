using Microsoft.EntityFrameworkCore;
using Presentazione.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.DBContext
{
    public class AContext<M> : DbContext where M:class
    {
        public AContext(DbContextOptions<AContext<M>> options) : base(options)
        {
        }
        public virtual DbSet<M> Query { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(@"Server=.\SQLEXPRESS;Database=Presentazione;Trusted_Connection=True;");
        }

    }
}
