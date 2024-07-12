package com.github.thibstars.rhacli.commands;

import com.github.thibstars.jhaapi.Configuration;
import com.github.thibstars.jhaapi.client.status.Status;
import com.github.thibstars.jhaapi.client.status.StatusService;
import com.github.thibstars.jhaapi.client.status.StatusServiceImpl;
import picocli.CommandLine.Command;

/**
 * @author Thibault Helsmoortel
 */
@Command(name = "status", description = "Fetches the HA status.")
@SuppressWarnings("java:S106") // This is a console application, so use of System.out is just fine
public class StatusCommand extends SubCommand {

    @Override
    public void run() {
        Configuration configuration = new Configuration(token);
        StatusService statusService = new StatusServiceImpl(configuration);

        System.out.println(
                statusService.getStatus()
                        .map(Status::message)
                        .orElse("Unable to fetch status.")
        );
    }
}
