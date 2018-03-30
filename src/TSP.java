import java.util.*;
import java.io.*;

// Cuz java is bs
// C:\Program Files\Java\jdk1.8.0_161\bin

public class TSP extends GA{

    private String filenameMatrix;
    private String WG_target;


    private Map<Character, Map<Character, Integer>> costMatrix;


    //NOTE: we are assuming we have an NxN matrix, where every point to every other point is defined
    public TSP(String fileNameParams, String target, String filenameMatrix)
    {
        super(fileNameParams,target);
        WG_target = new String(target);
        GA_numGenes = WG_target.length();
        this.filenameMatrix = filenameMatrix;
        //this.DisplayParams();
        /*
        if (WG_target.length() != GA_numGenes)
        {
            System.out.println("Error: Target size differs from number of genes");
            DisplayParams();
            System.exit(1);
        }*/

        costMatrix = new HashMap<Character, Map<Character, Integer>>();
        try {
            BufferedReader infile = new BufferedReader(new FileReader(filenameMatrix));
            String line = "";
            line = infile.readLine();
            String[] indexes = line.split(",");
            this.locations = new ArrayList<Character>();
            for(String index : indexes){
                this.locations.add(Character.valueOf(index.charAt(0)));
            }
            //System.out.println(this.locations);
            // While we haven't run out of lines in the file,
            int lineNumber = 0;
            while( (line = infile.readLine()) != null ) {
                String[] costs = line.split(",");
                // this is the index of ou
                HashMap<Character, Integer> innerMap = new HashMap<Character, Integer>();
                // For each number in the line,
                for(int i = 0; i < costs.length; i++) {
                    innerMap.put(locations.get(i), Integer.valueOf(costs[i]));
                }
                costMatrix.put(locations.get(lineNumber), innerMap);
                lineNumber++;
            }
        } catch (java.lang.Exception exception) {
            exception.printStackTrace();
        }
        super.DisplayParams();
        InitPop();
    }


    public void InitPop()
    {
        super.InitPop();
        ComputeCost();
        SortPop();
        TidyUp();
    }

    public void DisplayParams()
    {
        System.out.print("Target: ");
        System.out.println(WG_target);
        super.DisplayParams();
    }

    protected void ComputeCost()
    {
        for (int i = 0; i < GA_pop.size(); i++)
        {
            int cost = 0;
            Chromosome chrom = GA_pop.remove(i);
            for (int j = 0; j < GA_numGenes-1; j++) {
                char start = chrom.GetGene(j);
                char end = chrom.GetGene(j + 1);
                //System.out.print("Cost" + j + ": " + costMatrix.get(start).get(end));
                cost += costMatrix.get(start).get(end);
            }
            //System.out.println("Total cost: " + cost);
            chrom.SetCost(cost);
            GA_pop.add(i,chrom);
        }
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }

    //in earlier versions (as on ada) Evolve() from GA is here
}