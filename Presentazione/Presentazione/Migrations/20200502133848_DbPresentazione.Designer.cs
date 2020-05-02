﻿// <auto-generated />
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Presentazione.DBContext;
using Presentazione.Models;

namespace Presentazione.Migrations
{
    [DbContext(typeof(AContext<Diapositiva>))]
    [Migration("20200502133848_DbPresentazione")]
    partial class DbPresentazione
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "3.1.3")
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("Presentazione.Models.Diapositiva", b =>
                {
                    b.Property<long>("id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("bigint")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("coloreT")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("dimensioneT")
                        .HasColumnType("nvarchar(max)");

                    b.Property<bool>("isTitolo")
                        .HasColumnType("bit");

                    b.Property<int>("ordine")
                        .HasColumnType("int");

                    b.Property<string>("posizioneT")
                        .HasColumnType("nvarchar(max)");

                    b.Property<bool>("preset")
                        .HasColumnType("bit");

                    b.Property<string>("ratio")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("sfondo")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("titolo")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("id");

                    b.ToTable("diapositiva");
                });
#pragma warning restore 612, 618
        }
    }
}
