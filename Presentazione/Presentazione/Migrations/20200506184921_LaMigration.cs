using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Presentazione.Migrations
{
    public partial class LaMigration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Presentazione",
                columns: table => new
                {
                    id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    nome = table.Column<string>(nullable: false),
                    dataCreazione = table.Column<DateTime>(nullable: false),
                    ultimaModifica = table.Column<DateTime>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Presentazione", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "Diapositiva",
                columns: table => new
                {
                    id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    presentazioneId = table.Column<long>(nullable: false),
                    sfondo = table.Column<string>(nullable: false),
                    ordine = table.Column<int>(nullable: false),
                    preset = table.Column<bool>(nullable: false),
                    ratio = table.Column<string>(nullable: false),
                    isTitolo = table.Column<bool>(nullable: false),
                    titolo = table.Column<string>(nullable: true),
                    posizioneT = table.Column<string>(nullable: true),
                    dimensioneT = table.Column<string>(nullable: true),
                    coloreT = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Diapositiva", x => x.id);
                    table.ForeignKey(
                        name: "FK_Diapositiva_Presentazione_presentazioneId",
                        column: x => x.presentazioneId,
                        principalTable: "Presentazione",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "DiapoGraph",
                columns: table => new
                {
                    id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    dimensione = table.Column<string>(nullable: false),
                    posizione = table.Column<string>(nullable: false),
                    diapositivaid = table.Column<long>(nullable: false),
                    idGraph = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_DiapoGraph", x => x.id);
                    table.ForeignKey(
                        name: "FK_DiapoGraph_Diapositiva_diapositivaid",
                        column: x => x.diapositivaid,
                        principalTable: "Diapositiva",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Testo",
                columns: table => new
                {
                    id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    text = table.Column<string>(nullable: false),
                    dimensione = table.Column<string>(nullable: false),
                    posizione = table.Column<string>(nullable: false),
                    fontSize = table.Column<int>(nullable: false),
                    colore = table.Column<string>(nullable: false),
                    fontStyle = table.Column<int>(nullable: false),
                    diapositivaid = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Testo", x => x.id);
                    table.ForeignKey(
                        name: "FK_Testo_Diapositiva_diapositivaid",
                        column: x => x.diapositivaid,
                        principalTable: "Diapositiva",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_DiapoGraph_diapositivaid",
                table: "DiapoGraph",
                column: "diapositivaid");

            migrationBuilder.CreateIndex(
                name: "IX_Diapositiva_presentazioneId",
                table: "Diapositiva",
                column: "presentazioneId");

            migrationBuilder.CreateIndex(
                name: "IX_Testo_diapositivaid",
                table: "Testo",
                column: "diapositivaid");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "DiapoGraph");

            migrationBuilder.DropTable(
                name: "Testo");

            migrationBuilder.DropTable(
                name: "Diapositiva");

            migrationBuilder.DropTable(
                name: "Presentazione");
        }
    }
}
