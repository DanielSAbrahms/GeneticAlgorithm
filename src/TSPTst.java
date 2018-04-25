/* Class: CPSC 427-01
# Author: Jason Conci, Daniel Abrahms, Tyler Tiedt, Paul De Palma
# ID's:   -jconci      -dabrahms       -ttiedt
# Assignment: Project 7 - Genetic Algorithm, Traveling Salesperson problem
#
*/




import java.lang.*;

public class TSPTst
{

    public static void main(String args[])
    {

        TSP TSPOne = new TSP(args[0],args[1],args[2]);

        System.out.println();
        //WG1.DisplayParams(); Uncomment to display the contents of the parameter file
        //WG1.DisplayPop(); Uncomment to display the population before evolution
        TSPOne.Evolve();
        //WG1.DisplayPop(); Uncomment to display the population after evolution
        System.out.println();
    }
}