package com.github.thibstars.rhacli;

import com.github.thibstars.jhaapi.Configuration;
import com.github.thibstars.jhaapi.client.status.Status;
import com.github.thibstars.jhaapi.client.status.StatusService;
import com.github.thibstars.jhaapi.client.status.StatusServiceImpl;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * @author Thibault Helsmoortel
 */
@Command(name = "rhacli", mixinStandardHelpOptions = true, version = "RHACLI 1.0", description = "Remote Home Assistant CLI")
@SuppressWarnings("java:S106") // This is a console application, so use of System.out is just fine
public class Rhacli implements Runnable {

    @Override
    public void run() {
        System.out.println("subcommand needed: 'status'");
    }

    @Command(name = "status", description = "Fetches the HA status.")
    @SuppressWarnings("unused")
    public void status(@Option(names = {"-t", "--token"}, description = "Long-Lived Access Token to access Home Assistant") String token) {
        Configuration configuration = new Configuration(token);
        StatusService statusService = new StatusServiceImpl(configuration);

        System.out.println(
                statusService.getStatus()
                        .map(Status::message)
                        .orElse("Unable to fetch status.")
        );
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Rhacli()).execute(args);
        System.exit(exitCode);
    }
}
