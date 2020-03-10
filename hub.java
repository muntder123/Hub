package me.hub;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class hub extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin Starting");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin Disable");
    }

    @EventHandler
    public void OnLeaveBed(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.BLUE + "You left the bed");
        player.setLevel(10);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        player.setWalkSpeed((float) 0.2);
        player.sendMessage(ChatColor.AQUA + "Your speed is 1");
        event.setCancelled(true);

    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        player.setWalkSpeed((float) 0.5);
        player.sendMessage(ChatColor.AQUA + "Your speed is 5");
        event.setCancelled(true);
    }

    @EventHandler
    public void disablepvp(EntityDamageByEntityEvent event) {
        double player = event.getFinalDamage();
        event.setCancelled(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("heal")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.getHealth() == 20.0) {
                    player.sendMessage("you can't do that you have 20 hearts");
                } else {
                    player.sendMessage("here you go");
                    player.setHealth(player.getHealth() + 1);
                }
            }
        } else
        if (command.getName().equals("fly")) {
            Player player = (Player) sender;
            if (player.hasPermission("flyy.use")) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.RED + "Fly:" + ChatColor.DARK_RED + " DISABLED");
                    return true;
                }
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    player.sendMessage(ChatColor.RED + "Fly:" + ChatColor.GREEN + " ENABLED");
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.BOLD + "You do not have permission to use this command!!");
            }
        }
        return false;
    }
}





