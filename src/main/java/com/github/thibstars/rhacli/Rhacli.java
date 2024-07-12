package com.github.thibstars.rhacli;

import com.github.thibstars.rhacli.commands.StatusCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * @author Thibault Helsmoortel
 */
@Command(name = "rhacli", mixinStandardHelpOptions = true, version = "RHACLI 1.0", description = "Remote Home Assistant CLI",
        subcommands = {StatusCommand.class})
@SuppressWarnings("java:S106") // This is a console application, so use of System.out is just fine
public class Rhacli implements Runnable {

    @Override
    public void run() {
        System.out.println("subcommand needed: 'status'");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Rhacli()).execute(args);
        System.exit(exitCode);
    }
}
