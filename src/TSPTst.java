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