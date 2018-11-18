object Main extends App{
    val airportValues = (0:Int, null:String, null:String, null:String, 0:Double, 0:Double);
    var airports = Array(airportValues);
    var filename : String = null;
    
    println(airports.size);
    airports = airports.tail;
    println(airports.size);

    //def loadAirports(filename:String) : List(airportValues) = {
    //
    //}
}