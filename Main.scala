import scala.io.Source;

object Main extends App{
    type AirportValues = (Int, String, String, String, Double, Double);
    var filename : String = "src/airports.dat";

    def loadAirports(filename:String) : Array[AirportValues] = {
        //Parse les données de airports.dat dans une liste de liste a tuple
        var i = 0;
        var length = 0;

        for (line <- Source.fromFile(filename).getLines){
            length += 1;
        }

        var airports = new Array[AirportValues](length);      

        for (line <- Source.fromFile(filename).getLines){
            val cols = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim);

            airports(i) = (cols(0).toInt, cols(1), cols(2), cols(3), cols(6).toDouble, cols(7).toDouble);
            i += 1;
        }

        return airports;
    }

    val airports : Array[AirportValues] = loadAirports(filename);
}