package me.glaremasters.tribalmobs;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;

/**
 * Created by Glare
 * Date: 6/14/2019
 * Time: 10:42 PM
 */
@CommandAlias("tribalmobs")
public class Reload extends BaseCommand {
    @Dependency private TribalMobs tribalMobs;

    @Default
    @Subcommand("reload")
    @CommandPermission("tribalmobs.reload")
    public void execute(CommandSender sender) {
        tribalMobs.getMobs().clear();
        tribalMobs.setMobs(tribalMobs.getConfigMobs());
        sender.sendMessage("Config Reloaded");
    }
}
