using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace Presentazione.Models
{
    [JsonConverter(typeof(JsonColoreConverter))]
    public class Colore {

        public Colore(int red, int green, int blue, int alpha)
        {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }

        public Colore(string colore)
        {
            Regex rgx = new Regex(@"(\w\w)");
            var arr = rgx.Matches(colore);
            
            this.alpha = Convert.ToInt32(Convert.ToInt32(arr[0].Value,16));
            this.red = Convert.ToInt32(Convert.ToInt32(arr[1].Value, 16));
            this.green = Convert.ToInt32(Convert.ToInt32(arr[2].Value, 16));
            this.blue = Convert.ToInt32(Convert.ToInt32(arr[3].Value, 16));
        }

        public int red { get; set; }

        public int green { get; set; }

        public int blue { get; set; }

        public int alpha { get; set; }

        public string toRGBA() {
            
            return "rgba(" + red + "," + green + "," + blue + "," + alpha + ")";
        }

        public string toHEX()
        {
            
            return "#" + alpha.ToString("X2") + red.ToString("X2") + green.ToString("X2") + blue.ToString("X2");
        }

    }

    public class JsonColoreConverter : JsonConverter<Colore>
    {
        public override Colore Read(ref Utf8JsonReader reader, Type Object, JsonSerializerOptions options) {
            reader.Read();
            reader.Read();
            reader.TryGetInt32(out int red);
            reader.Read();
            reader.Read();
            reader.TryGetInt32(out int green);
            reader.Read();
            reader.Read();
            reader.TryGetInt32(out int blue);
            reader.Read();
            reader.Read();
            reader.TryGetInt32(out int alpha);
            reader.Read();
            return new Colore(red, green, blue, alpha);
        }


        public override void Write(Utf8JsonWriter writer, Colore colore, JsonSerializerOptions options)
        {
            writer.WriteStartObject();
            writer.WritePropertyName("red");
            writer.WriteNumberValue(colore.red);
            writer.WritePropertyName("green");
            writer.WriteNumberValue(colore.green);
            writer.WritePropertyName("blue");
            writer.WriteNumberValue(colore.blue);
            writer.WritePropertyName("alpha");
            writer.WriteNumberValue(colore.alpha);
            writer.WriteEndObject();
            
        }
    }
}
