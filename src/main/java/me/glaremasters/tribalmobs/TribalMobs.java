package me.glaremasters.tribalmobs;

import co.aikar.commands.PaperCommandManager;
import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class TribalMobs extends JavaPlugin implements Listener {

    private PaperCommandManager commandManager;
    private Map<String, Boolean> mobs;

    @Override
    public void onEnable() {
        commandManager = new PaperCommandManager(this);
        saveDefaultConfig();
        mobs = getConfigMobs();
        commandManager.registerCommand(new Reload());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        mobs.clear();
    }

    public Map<String, Boolean> getConfigMobs() {
        Map<String, Boolean> mobs = new HashMap<>();
        getConfig().getConfigurationSection("mobs").getKeys(false).forEach(k -> mobs.put(k, getConfig().getBoolean("mobs." + k)));
        return mobs;
    }

    @EventHandler
    public void onPreSpawn(PreCreatureSpawnEvent event) {
        String type = event.getType().name();
        if (event.getReason() != CreatureSpawnEvent.SpawnReason.SPAWNER) {
            if (mobs.keySet().contains(type)) {
                event.setCancelled(mobs.get(type));
            }
        }
    }

    public void setMobs(Map<String, Boolean> mobs) {
        this.mobs = mobs;
    }

    public Map<String, Boolean> getMobs() {
        return mobs;
    }
}
