package net.etum.etumatrium.akia.region;

import net.etum.etumatrium.Main;
import net.etum.etumatrium.akia.region.builder.Coordinate;
import net.etum.etumatrium.akia.region.builder.Group;
import net.etum.etumatrium.akia.region.builder.Member;
import net.etum.etumatrium.akia.region.builder.Region;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Service : Region

public class RegionManager {

    private final File file = new File("plugins//" + Main.getInstance().getName() + "//region//data.yml");
    private final Map<Integer, Region> regions;

    public RegionManager() {
        this.regions = new HashMap<>();
    }

    // Créer une nouvelle région
    public void createRegion(int id, List<Coordinate> coordinates, String name, boolean showTitle) {
        Member memberManager = new Member();
        Group groupManager = new Group();
        Region newRegion = new Region(id, coordinates, name, showTitle, memberManager, groupManager);
        regions.put(id, newRegion);
    }

    // Supprimer une région
    public void deleteRegion(int id) {
        regions.remove(id);
    }

    // Charger toutes les régions dans la map depuis le fichier YAML
    public void loadAllRegions() {
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Yaml yaml = new Yaml();
                Map<Integer, Region> loadedRegions = yaml.load(reader);
                if (loadedRegions != null) {
                    regions.putAll(loadedRegions);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Sauvegarder toutes les régions de la map dans le fichier YAML
    public void saveAllRegions() {
        try (FileWriter writer = new FileWriter(file)) {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            yaml.dump(regions, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Obtenez une région par son ID
    public Region getRegionById(int id) {
        return regions.get(id);
    }
}
