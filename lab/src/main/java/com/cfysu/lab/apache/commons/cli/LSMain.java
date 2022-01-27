package com.cfysu.lab.apache.commons.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @Author canglong
 * @Date 2022/1/19
 */
public class LSMain {

    //java -cp /Users/chris/.m2/repository/commons-cli/commons-cli/1.4/commons-cli-1.4.jar:/Users/chris/IdeaProjects/mine/code-ysu/lab/target/classes com.cfysu.lab.apache.commons.cli.LSMain --block-size=9
    public static void main(String[] args) {
        // create the command line parser
        CommandLineParser parser = new DefaultParser();

        // create the Options
        Options options = new Options();
        options.addOption("a", "all", false, "do not hide entries starting with .");
        options.addOption("A", "almost-all", false, "do not list implied . and ..");
        options.addOption("b", "escape", false, "print octal escapes for non-graphic "
            + "characters");
        options.addOption(Option.builder("SIZE").longOpt("block-size")
            .desc("use SIZE-byte blocks")
            .hasArg()
            .build());
        options.addOption("B", "ignore-backups", false, "do not list implied entries "
            + "ending with ~");
        options.addOption("c", false, "with -lt: sort by, and show, ctime (time of last "
            + "modification of file status information) with "
            + "-l:show ctime and sort by name otherwise: sort "
            + "by ctime");
        options.addOption("C", false, "list entries by columns");

        //String[] args = new String[]{ "--block-size=10" };

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);

            // validate that block-size has been set
            if (line.hasOption("block-size")) {
                // print the value of block-size
                System.out.println(line.getOptionValue("block-size"));
            }else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("LSMain", options);
            }
        }
        catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }
    }
}
