import scala.io.{Source, StdIn};

object Test extends App{
    type AirportValues = (Int, String, String, String, Double, Double);
    var filename : String = "src/airports.dat";

    def loadAirports(filename:String) : Array[AirportValues] = {
        
        //Parse les données de airports.dat dans une liste de liste a tuple
        var i = 0;
        var airports = new Array[AirportValues](Source.fromFile(filename).getLines.length);      

        for (line <- Source.fromFile(filename).getLines){
            val cols = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim);

            airports(i) = (cols(0).toInt, cols(1), cols(2), cols(3), cols(6).toDouble, cols(7).toDouble);
            i += 1;
        }

        return airports;
    }

    val airports : Array[AirportValues] = loadAirports(filename);
    //---------------------------------------------------------------------------------------------------------------------(2h30 research - 1h30 programming)

    def filter(airports:Array[AirportValues]): Array[AirportValues] = {
        
        //Filtre par noms de pays
        var contryNum = 0;
        var countryName:List[String] = Nil;
        var filteredAirports:Array[AirportValues] = Array();

        print("Enter the number of countries you need to filter : ");
        contryNum = StdIn.readInt();

        //var countryName:Array[String] = new Array[String](contryNum-1);

        do{
            print("Enter your country name : ");
            countryName = '"'+StdIn.readLine()+'"' :: countryName;
        }while(contryNum < 0);

        for(i <- 0 to airports.length-1){
            if(airports(i)._4 == countryName)
                filteredAirports = filteredAirports :+ airports(i);
        }

        // println(countryName);
        // println();
        // for(i <- 0 to filteredAirports.length-1){
        //     println(filteredAirports(i));
        // }

        return filteredAirports;
    }    

    val filteredAirports = filter(airports);
}