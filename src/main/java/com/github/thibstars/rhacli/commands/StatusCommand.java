package com.github.thibstars.rhacli.commands;

import com.github.thibstars.jhaapi.Configuration;
import com.github.thibstars.jhaapi.client.status.response.Status;
import com.github.thibstars.jhaapi.client.status.StatusService;
import com.github.thibstars.jhaapi.client.status.StatusServiceImpl;
import java.net.MalformedURLException;
import java.net.URI;
import picocli.CommandLine.Command;

/**
 * @author Thibault Helsmoortel
 */
@Command(name = "status", description = "Fetches the HA status.", mixinStandardHelpOptions = true)
@SuppressWarnings("java:S106") // This is a console application, so use of System.out is just fine
public class StatusCommand extends SubCommand {

    @Override
    public void run() {
        Configuration configuration;
        if (baseUrl == null) {
            configuration = new Configuration(token);
        } else {
            try {
                configuration = new Configuration(URI.create(baseUrl).toURL(), token);
            } catch (MalformedURLException | IllegalArgumentException e) {
                System.out.println("Malformed URL: " + baseUrl + ", will use default configuration instead.");
                configuration = new Configuration(token);
            }
        }
        StatusService statusService = new StatusServiceImpl(configuration);

        System.out.println(
                statusService.getStatus()
                        .map(Status::message)
                        .orElse("Unable to fetch status.")
        );
    }
}
