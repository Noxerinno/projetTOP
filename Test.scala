import scala.io.Source;

object Test extends App{
    for (line <- Source.fromFile("src/airports.dat").getLines){
        val cols = line.split(",").map(_.trim);
        //println(s"${cols(0)}~${cols(1)}~${cols(2)}~${cols(3)}~${cols(6)}~${cols(7)}~");
        println(cols(0) + " ~ " + cols(1) + " ~ " + cols(2) + " ~ " + cols(3) + " ~ " + cols(6) + " ~ " + cols(7));
    }
}