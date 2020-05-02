using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Presentazione.Models
{
    public class Colore
    {
        public Colore() { }
        public Colore(int r, int g, int b) {
            this.red = r;
            this.green = g;
            this.blue = b;
            this.alpha = 1;
        }

        public Colore(int r, int g, int b, int a)
        {
            this.red = r;
            this.green = g;
            this.blue = b;
            this.alpha = a;
        }

        public Colore(string colore)
        {
            //riconoscimento tipologia (rgba,#) conversione e salvataggio
        }

        int red { get; set; }

        int green { get; set; }

        int blue { get; set; }

        int alpha { get; set; }

        public string toRGBA() { 
            return "rgba("+red+","+green+","+blue+","+alpha+")"; 
        }

        public string toHEX()
        {
            //convertire i singoli in esadecimale e restituire stringa
            return "";
        }

    }
}
