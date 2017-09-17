# Prosser Usage

The `prosser-api.jar` is an executable jar that can compile and write out your
experiment to its constituent files.  Usage is below:

    java -jar prosser-api.jar <path to java file> <output path>

There are two example java files in this bundle, `ExperimentFncsTest.java` and
`TestSeanZExperiment.java`.  `TestSeanZExperiment.java` is a GridLabD only
experiment that was our first test of the GridLabD parts of Prosser.
`ExperimentFncsTest.java` is a full experiment that generates GridLabD and NS-3
files.  FNCS file generation is currently unimplemented.  An example of usage
is below:

1. Open a command prompt or shell in the folder with all of these files
2. Run `java -jar prosser-api.jar ExperimentFncsTest.java out`
3. This will compile the `ExperimentFncsTest.java` and put the results in the
   `out` folder

You must run the command with a Java Development Kit (JDK) in order for the
compilation to work. Most of the time `java` is linked to a Java Runtime
Environment (JRE).  All of the code has been compiled with Java 8 so you will
need to have a Java 8 JDK in order to run this command.  In my case on Windows,
I had to run the command with `"C:\Program Files\Java\jdk1.8.0_31\bin\java.exe"`

The `prosser-api-javadoc.jar` is a zip file that contains the Prosser javadoc
that you can use when developing your own Experiment.
