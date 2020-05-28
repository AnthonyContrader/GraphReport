using Microsoft.EntityFrameworkCore.Migrations;

namespace Presentazione.Migrations
{
    public partial class migrazione : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Diapositiva_Presentazione_presentazioneId",
                table: "Diapositiva");

            migrationBuilder.RenameColumn(
                name: "presentazioneId",
                table: "Diapositiva",
                newName: "presentazioneid");

            migrationBuilder.RenameIndex(
                name: "IX_Diapositiva_presentazioneId",
                table: "Diapositiva",
                newName: "IX_Diapositiva_presentazioneid");

            migrationBuilder.AddColumn<long>(
                name: "utente",
                table: "Presentazione",
                nullable: false,
                defaultValue: 0L);

            migrationBuilder.AlterColumn<int>(
                name: "dimensioneT",
                table: "Diapositiva",
                nullable: false,
                oldClrType: typeof(string),
                oldType: "nvarchar(max)",
                oldNullable: true);

            migrationBuilder.AddColumn<string>(
                name: "borderColor",
                table: "Diapositiva",
                nullable: true);

            migrationBuilder.AddColumn<int>(
                name: "borderSize",
                table: "Diapositiva",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "fontFamily",
                table: "Diapositiva",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "fontRotation",
                table: "Diapositiva",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<string>(
                name: "fontStyle",
                table: "Diapositiva",
                nullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_Diapositiva_Presentazione_presentazioneid",
                table: "Diapositiva",
                column: "presentazioneid",
                principalTable: "Presentazione",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Diapositiva_Presentazione_presentazioneid",
                table: "Diapositiva");

            migrationBuilder.DropColumn(
                name: "utente",
                table: "Presentazione");

            migrationBuilder.DropColumn(
                name: "borderColor",
                table: "Diapositiva");

            migrationBuilder.DropColumn(
                name: "borderSize",
                table: "Diapositiva");

            migrationBuilder.DropColumn(
                name: "fontFamily",
                table: "Diapositiva");

            migrationBuilder.DropColumn(
                name: "fontRotation",
                table: "Diapositiva");

            migrationBuilder.DropColumn(
                name: "fontStyle",
                table: "Diapositiva");

            migrationBuilder.RenameColumn(
                name: "presentazioneid",
                table: "Diapositiva",
                newName: "presentazioneId");

            migrationBuilder.RenameIndex(
                name: "IX_Diapositiva_presentazioneid",
                table: "Diapositiva",
                newName: "IX_Diapositiva_presentazioneId");

            migrationBuilder.AlterColumn<string>(
                name: "dimensioneT",
                table: "Diapositiva",
                type: "nvarchar(max)",
                nullable: true,
                oldClrType: typeof(int));

            migrationBuilder.AddForeignKey(
                name: "FK_Diapositiva_Presentazione_presentazioneId",
                table: "Diapositiva",
                column: "presentazioneId",
                principalTable: "Presentazione",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
