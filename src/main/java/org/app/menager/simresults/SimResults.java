package org.app.menager.simresults;

import org.app.menager.config.Config;

import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;
import java.util.Vector;
import java.util.Map;

public class SimResults {
    Map<Vector, UUID> ants;
    Map<Vector, UUID> pheromones;
    Config settings;


    void PrintToJson(String filepath) {
        // TODO: 15.05.2023   
    }
}
