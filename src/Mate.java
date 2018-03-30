import java.util.*;
import java.lang.*;

public class Mate
{
    private    Chromosome MT_father, MT_mother, MT_child1, MT_child2;
    private    int MT_posChild1, MT_posChild2, MT_posLastChild,MT_posFather, MT_posMother,
            MT_numGenes, MT_numChromes;

    public Mate(ArrayList<Chromosome> population, int numGenes, int numChromes)
    {
        MT_numGenes     = numGenes;
        MT_numChromes   = numChromes;

        MT_posChild1    = population.size()/2;
        MT_posChild2    = MT_posChild1 + 1;
        MT_posLastChild= population.size() - 1;

        for (int i = MT_posLastChild; i >= MT_posChild1; i--)
            population.remove(i);

        MT_posFather = 0;
        MT_posMother = 1;
    }

    //Simple Top-Down Pairing
    public ArrayList<Chromosome> Crossover(ArrayList<Chromosome> population, int numPairs) {
        for (int j = 0; j < numPairs; j++)
        {
            MT_father       =  population.get(MT_posFather);
            MT_mother       =  population.get(MT_posMother);
            MT_child1       = new Chromosome(MT_numGenes);
            MT_child2       = new Chromosome(MT_numGenes);
            Random rnum     = new Random();
            int crossPoint  = rnum.nextInt(MT_numGenes);

            //left side
            for (int i = 0; i < crossPoint; i++)
            {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            //right side
            for (int i = crossPoint; i < MT_numGenes; i++)
            {
                MT_child1.SetGene(i, MT_mother.GetGene(i));
                MT_child2.SetGene(i, MT_father.GetGene(i));
            }

            population.add(MT_posChild1,MT_child1);
            population.add(MT_posChild2,MT_child2);

            MT_posChild1    = MT_posChild1 + 2;
            MT_posChild2    = MT_posChild2 + 2;
            MT_posFather    = MT_posFather + 2;
            MT_posMother    = MT_posMother + 2;
        }
        return population;
    }


    public ArrayList<Chromosome> DualPointCrossover(ArrayList<Chromosome> population, int numPairs) {
        for (int i = 0; i < numPairs; i++) {
            MT_father = population.get(MT_posFather);
            MT_mother = population.get(MT_posMother);
            MT_child1 = new Chromosome(MT_numGenes);
            MT_child2 = new Chromosome(MT_numGenes);
            Random rnum = new Random();

            int crossPointOne = rnum.nextInt(MT_numGenes-2);
            int crossPointTwo = rnum.nextInt(MT_numGenes-1);
            while (crossPointTwo <= crossPointOne) {
                crossPointTwo = rnum.nextInt(MT_numGenes);
            }
            int numGenesCross = crossPointTwo - crossPointOne;
            //System.out.println("Cross one: " + crossPointOne + " Cross Two: " + crossPointTwo);
            //System.out.println("Crossing # genes: " + numGenesCross);

            // Getting the crossover points from each of our parents
            for (int j = crossPointOne; j < crossPointTwo; j++) {
                MT_child1.SetGene(j, MT_mother.GetGene(j));
                MT_child2.SetGene(j, MT_father.GetGene(j));
            }

            // For the remaining genes of our children, we append the genes from the other
            // parent, which aren't already in the child, to our child, in order.
            int childOneIndex = 0;
            int childTwoIndex = 0;
            //System.out.println("MATING");
            for (int k = 0; k < MT_numGenes; k++) {
                if (childOneIndex == crossPointOne) {
                    childOneIndex += numGenesCross;
                }
                if (childTwoIndex == crossPointOne) {
                    childTwoIndex += numGenesCross;
                }
                if (!MT_child1.Contains(MT_father.GetGene(k))) {
                    MT_child1.SetGene(childOneIndex, MT_father.GetGene(k));
                    childOneIndex++;
                }

                if (!MT_child2.Contains(MT_mother.GetGene(k))) {
                    MT_child2.SetGene(childTwoIndex, MT_mother.GetGene(k));
                    childTwoIndex++;
                }
            }
            //System.out.println("Successfully mated " + i + " times");

            population.add(MT_posChild1, MT_child1);
            population.add(MT_posChild2, MT_child2);

            MT_posChild1 = MT_posChild1 + 2;
            MT_posChild2 = MT_posChild2 + 2;
            MT_posFather = MT_posFather + 2;
            MT_posMother = MT_posMother + 2;
        }
        return population;
    }
}
