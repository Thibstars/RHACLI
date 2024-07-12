package com.github.thibstars.rhacli.commands;

import picocli.CommandLine.Option;

/**
 * @author Thibault Helsmoortel
 */
public abstract class SubCommand implements Runnable {

    @Option(names = {"-t", "--token"}, description = "Long-Lived Access Token to access Home Assistant")
    protected String token;
}
