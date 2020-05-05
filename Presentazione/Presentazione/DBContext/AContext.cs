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
            modelBuilder.Entity<Diapositiva>()
                .HasOne(a => a.presentazione).WithMany()
                .HasForeignKey(e => e.id);
        }
    }
}
