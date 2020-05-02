using Microsoft.EntityFrameworkCore.Migrations;

namespace Presentazione.Migrations
{
    public partial class DbPresentazione : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "diapositiva",
                columns: table => new
                {
                    id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
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
                    table.PrimaryKey("PK_diapositiva", x => x.id);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "diapositiva");
        }
    }
}
