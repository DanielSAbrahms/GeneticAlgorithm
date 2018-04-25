README FILE - read this before attempting to run our code pls


COMPILATION:
    1) javac SetParams.java
    2) javac GA.java
    3) javac Mate.java
    4) javac TSP.java
    5) javac TSPTst.java

RUNNING:
    Our matrix, containing all intersection costs for all points, is in the file MatrixOne.csv

    1. Set all of our parameters
        a) command is -
           java SetParams params.dat 64 32 8 .1 1000
           where 64 = Total population
                 32 = Actual population
                 8 = size of path to be guessed
                 .1 = mutation factor
                 1000 = number of iterations to run this
                    NOTE: program will run to max number of iterations, OR until the entire population
                    converges to within the standard deviation of the average
    2. Run the program
        a) command is -
            java TSPTst params.dat "abcdefgh" MatrixOne.csv
            where params.dat = parameters file, created/members set on SetParams
                  "abcdefgh" = the "word to be guessed" (unnecessary, but working with another codebase so we kept it in
                  MatrixOne.csv = our .csv file containing all intersection costs for all points
                                  We defined this ourselves, and should be provided.
    3. This should run until all items in the population are within the standard deviation, or until
       we hit our maximum number of iterations, which is specified in SetParams