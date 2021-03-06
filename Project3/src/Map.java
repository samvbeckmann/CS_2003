import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created on Oct 13, 2004
 *
 * @author class_sandip
 */
public class Map
{
    private ListInterface<City> adjList;

    public Map(String mapFileName)
    {
        adjList = new ListReferenceBased<City>();
        createFlightMap(mapFileName);
    }

    public void createFlightMap(String mapFileName)
    {
        try
        {
            Scanner input = new Scanner(new File(mapFileName));
            Scanner line;
            while (input.hasNextLine())
            {
                line = new Scanner(input.nextLine());
                String origCity = line.next();
                City ct = getCity(origCity);
                if (ct == null)
                {
                    ct = new City(origCity);
                    adjList.append(ct);
                }
                String destCity = line.next();
                if (getCity(destCity) == null)
                    adjList.append(new City(destCity));
                double cost = line.nextDouble();
                ct.addNeighbor(new Destination(destCity, cost));
            }
            input.close();
        } catch (IOException e)
        {
            System.out.println("IOException in reading input file!!!");
        }
    }

    public void unvisitAll()
    {
        for (int i = 1; i <= adjList.size(); i++)
        {
            City ct = adjList.get(i);
            ct.unmarkVisited();
            ct.resetNext();
        }
    }

    // Use contains on adjList to return the City with name cityName
    // if it exists in the map, otherwise return null.
    public City getCity(String cityName)
    {
        for (int i = 1; i <= adjList.size(); i++)
        {
            City city = adjList.get(i);
            if (city.getName().equals(cityName))
                return city;
        }
        return null;
        // NEED CODE FOR PROJECT
    }

    public City getNextCity(City ct)
    {
        // If there are more neighbors to visit from ct,
        // loop
        //   get name of next neighbor
        //   retrieve the City with that name
        //   if that City is unvisited return it
        //
        // if no unvisited neighbor of ct remains, return null
        try
        {
            City nextCity = getCity(ct.getNextCityName());

            if (nextCity != null)
                return nextCity;
            else
                return null;
        } catch (IndexOutOfBoundsException e)
        {
            return null;
        }

        // NEED CODE FOR PROJECT
    }

    public void findPath(String origin, String destination)
    {
        //	   ---------------------------------------------------
        //	   Determines whether a sequence of flights between
        //	   two cities exists. Nonrecursive stack version.
        //	   Precondition: origin and destination are the origin
        //	   and destination city names, respectively.
        //	   Postcondition: Prints out a sequence of flights
        //	   connecting origin to destination and the total
        //	   cost, otherwise prints out a failure
        //	   message. Cities visited during search are marked as
        //	   visited in the flight map.  Implementation notes:
        //	   Uses a stack for the cities of a potential
        //	   path. Calls unvisitAll, markVisited, and
        //	   getNextCity.
        //	   ---------------------------------------------------

        City originCity = getCity(origin);
        if (originCity == null)
            System.out.println("No flights from " + origin + "\n");
        else
        {
            City endCity = getCity(destination);
            StackInterface<City> path = new StackVectorBased<City>();
            path.push(originCity);
            boolean flag = true;
            while (path.peek() != endCity)
            {
                City nextCity = this.getNextCity(path.peek());
                if (nextCity != null)
                {
                    path.push(nextCity);
                    nextCity.markVisited();
                } else
                {
                    path.pop();
                }
                if (path.isEmpty())
                {
                    flag = false;
                    break;
                }
            }
            unvisitAll();

            if (flag)
            {
                System.out.println(getPath(path));
            } else
            {
                System.out.println("No Path Found.\n");
            }

            // NEED CODE FOR PROJECT
            // Use stack to search the map and if path is found,
            // print out the path and the total cost
        }
    } // end isPath

    private static String getPath(StackInterface<City> reversePath)
    {
        StackInterface<City> path = new StackVectorBased<City>();
        while (!reversePath.isEmpty())
        {
            path.push(reversePath.pop());
        }
        City buffer1 = path.pop();
        City buffer2;
        String result = "";
        int totalCost = 0;
        while (!path.isEmpty())
        {
            buffer2 = path.pop();
            Destination dest = buffer1.findDest(buffer2.getName());
            result += buffer1 + " --> " + dest.toString() + "\n";
            totalCost += dest.getCost();
            buffer1 = buffer2;
        }
        result += "Total Cost: " + totalCost + "\n";
        return result;
    }
}
