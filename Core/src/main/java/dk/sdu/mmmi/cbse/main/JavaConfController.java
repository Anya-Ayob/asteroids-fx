package dk.sdu.mmmi.cbse.main;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class JavaConfController {

    public static ModuleLayer createLayer(String from, String module) {
        Path pluginDir = Paths.get(from).normalize().toAbsolutePath();
        ModuleFinder finder = ModuleFinder.of(pluginDir);
        ModuleLayer parent = ModuleLayer.boot();
        Configuration cf = parent.configuration().resolve(finder, ModuleFinder.of(), Set.of(module));
        return parent.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());

    }
}
