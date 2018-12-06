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
        var filteredAirports:List[AirportValues] = Nil;
        var choice = 0;

        //var countryName:Array[String] = new Array[String](contryNum-1);

        println("Which filter do you want to use ? \n    1.Filter by country name\n    2.Filter by hemisphere\n    3.Filter by zone\n");
        print("Enter the number corresponding of your choice : ");
        choice = StdIn.readInt();

        choice match{
            case 1 => {
                var contryNum = 0;
                var countryName:List[String] = Nil;

                print("\nEnter the number of countries you need to filter : ");
                contryNum = StdIn.readInt();

                print("Enter your country name : ");
                while(contryNum > 0){
                    countryName = '"'+StdIn.readLine()+'"' :: countryName;
                    contryNum -= 1;
                }

                for(i <- 0 to airports.length-1){
                    if(airports(i)._4 == countryName)
                        filteredAirports = airports(i) :: filteredAirports;
                }
            }

            case 2 => {
                var index = 0;

                print("\nDo you want to filter all the airports in the northern hemisphere ? (y/o)  ");
                if(StdIn.readLine() == 'y'){
                    for(i <- 0 to airports.length-1){
                        if(airports(i)._5 >= 0)
                            filteredAirports = airports(i) :: filteredAirports;
                    }
                }
                else{
                    for(i <- 0 to airports.length-1){
                        if(airports(i)._5 < 0)
                            filteredAirports = airports(i) :: filteredAirports;
                    }
                }
            }

            case 3 => {
                
            }
        }

        // println(countryName);
        // println();
        // for(i <- 0 to filteredAirports.length-1){
        //     println(filteredAirports(i));
        // }

        return filteredAirports.toArray;
    }    

    val filteredAirports = filter(airports);
    println(filteredAirports.length)

    //---------------------------------------------------------------------------------------------------------------------(0h15 research - 2h30 programming)
}