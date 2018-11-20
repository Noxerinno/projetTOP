import java.nio.charset.CodingErrorAction;

import scala.io.Codec;
import scala.io.Source;

object Main extends App{
    var filename : String = "src/airports.dat";

    def loadAirports(filename:String) : List[(Int, String, String, String, Double, Double)] = {
        //Parse les données de airports.dat dans une liste de liste a tuple

        implicit val codec = Codec("UTF-8");
        codec.onUnmappableCharacter(CodingErrorAction.IGNORE);
        var airports : List[(Int, String, String, String, Double, Double)] = Nil;       
        
        for (line <- Source.fromFile("src/airports.dat").getLines){
            val cols = line.split(",").map(_.trim);
            //println(s"${cols(0)}~${cols(1)}~${cols(2)}~${cols(3)}~${cols(6)}~${cols(7)}");
            airports = (s"${cols(0)}".toInt, s"${cols(1)}", s"${cols(2)}", s"${cols(3)}", s"${cols(6)}".toDouble, s"${cols(7)}".toDouble) :: airports;
        }

        println(airports.length);
        return airports;
    }

    var airports : List[(Int, String, String, String, Double, Double)] = loadAirports(filename);
}