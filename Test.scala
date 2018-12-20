import scala.io.{Source, StdIn};
import scala.math.{Pi,sin,cos,pow,acos,sqrt,min,max};

object Test extends App{
    type AirportValues = (Int, String, String, String, Double, Double);
    type CountriesValues = (Int, String, String, Long, Long);

    def loadAirports(filename:String) : Array[AirportValues] = {
        
        //Parse les données de airports.dat dans une liste de liste a tuple (id, name, city, country, latitude, longitude)
        var i = 0;
        var airports = new Array[AirportValues](Source.fromFile(filename).getLines.length);      

        for (line <- Source.fromFile(filename).getLines){
            val cols = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim);

            airports(i) = (cols(0).toInt, cols(1), cols(2), cols(3), cols(6).toDouble, cols(7).toDouble);
            i += 1;
        }

        return airports;
    }

    val airports : Array[AirportValues] = loadAirports("src/airports.csv");
    //---------------------------------------------------------------------------------------------------------------------(2h30 research - 1h30 programming)

    def filter(airports:Array[AirportValues]): Array[AirportValues] = {
        var filteredAirports : Array[AirportValues] = Array();
        var choice = 0;

        //var countryName:Array[String] = new Array[String](contryNum-1);

        println("Which filter do you want to use ? \n    1.Filter by country name\n    2.Filter by hemisphere\n    3.Filter with two points\n    4.Filter with a point and a radius\n");
        print("Enter the number corresponding of your choice : ");
        choice = StdIn.readInt();

        choice match{
            case 1 => {
                var contryNum = 0;

                print("\nEnter the number of countries you need to filter : ");
                contryNum = StdIn.readInt();

                var countryName:Array[String] = new Array[String](contryNum);

                print("Enter your country name : ");
                while(contryNum > 0){
                    countryName(contryNum-1) = '"'+StdIn.readLine()+'"';
                    contryNum -= 1;
                }

                for(i <- 0 to countryName.length-1){
                    for(j <- 0 to airports.length-1){
                        if(airports(j)._4 == countryName(i))
                            filteredAirports :+= airports(i);
                    }
                }
            }

            case 2 => {
                print("\nDo you want to filter all the airports in the northern hemisphere ? (y/o)  ");

                if(StdIn.readLine().head == 'y'){
                    for(i <- 0 to airports.length-1){
                        if(airports(i)._5 >= 0)
                            filteredAirports :+= airports(i);
                    }
                }
                else{
                    for(i <- 0 to airports.length-1){
                        if(airports(i)._5 < 0)
                            filteredAirports :+= airports(i);
                    }
                }
            }

            case 3 => {
                print("\nEnter the latitude of your first point : ");
                var latX = StdIn.readDouble();
                print("Enter the longitude of your first point : ");
                var longX = StdIn.readDouble();
                print("\nEnter the latitude of your second point : ");
                var latY = StdIn.readDouble();
                print("Enter the longitude of your second point : ");
                var longY = StdIn.readDouble();

                for(i <- 0 to airports.length-10){
                    if(min(latX, latY) <= airports(i)._5 && airports(i)._5 <= max(latX, latY) && min(longX, longY) <= airports(i)._6 && airports(i)._6 <= max(longX, longY)){
                        filteredAirports :+= airports(i);
                    }
                }
            }

            case 4 => {
                print("\nEnter the latitude of your point : ");
                var centerLat = StdIn.readDouble();
                print("Enter the longitude of your point : ");
                var centerLong = StdIn.readDouble();
                print("Enter your radius : ");
                var radius = StdIn.readDouble();

                for(i <- 0 to airports.length-10){
                    if(distanceBetween2(centerLat, centerLong, airports(i)._5, airports(i)._6) <= radius){
                        filteredAirports :+= airports(i);
                    }
                }
            }

            case _ => {
                print("\nPlease enter a valid number !")
            }
        }

        return filteredAirports.toArray;
    }    

    //val filteredAirports = filter(airports);

    //---------------------------------------------------------------------------------------------------------------------(0h15 research - 4h00 programming)

    def distanceBetween2(lat1:Double, long1:Double, lat2:Double, long2:Double) : Int = {
        return (6378.137 * acos(sin(lat1*Pi/180)*sin(lat2*Pi/180)+cos(lat1*Pi/180)*cos(lat2*Pi/180)*cos((long2-long1)*Pi/180))).toInt;
    }

    def distanceArray(airports:Array[AirportValues]) : Array[Int] = {
        var distances : Array[Int] = Array();
        
        println("Under which format do you want your distances ?\n    1.An Array of Array\n    2.A list\n");
        print("Enter the number corresponding of your choice : ");
        var choice = StdIn.readInt();

        choice match{
            case 1 => {
                // var distances = Array.ofDim[Int](airports.length, airports.length);

                // for(i <- 0 to airports.length-1){
                //     for(j <- i to airports.length-1){
                //         distances(i)(j) = distanceBetween2(airports(i)._5, airports(i)._6, airports(j)._5, airports(j)._6);
                //     }
                // }

                println("\nWork in progress");
            }

            case 2 => {
                for(i <- 0 to airports.length-1){
                    for(j <- i to airports.length-1){
                        distances :+= distanceBetween2(airports(i)._5, airports(i)._6, airports(j)._5, airports(j)._6);
                    }
                }
            }

            case _ => {
                print("\nPlease enter a valid number !")
            }
        }

        return distances;
    }
    //---------------------------------------------------------------------------------------------------------------------(0h00 research - 0h15 programming)

    def loadCountries(filename:String) : Array[CountriesValues] = {
        //Parse les données de countries.csv dans une liste de liste a tuple ("id", country, country code, population, size)
        var i = 0;
        var countries = new Array[CountriesValues](Source.fromFile(filename).getLines.length);      

        for (line <- Source.fromFile(filename).getLines){
            val cols = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim);

            countries(i) = (cols(0).toInt, cols(1), cols(2), cols(3).toLong, cols(4).toLong);
            i += 1;
        }

        return countries;
    }

    val countries : Array[CountriesValues] = loadCountries("src/countries.csv");
    //---------------------------------------------------------------------------------------------------------------------(1h00 research - 0h30 programming)
}