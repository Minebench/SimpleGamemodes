package de.themoep.SimpleGamemodes;

/**
 * SimpleGamemode - Gamemode management Bukkit plugin
 Copyright (C) 2015 Max Lee (https://github.com/Phoenix616/)

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.* 
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SimpleGamemodes extends JavaPlugin implements Listener, CommandExecutor {

    /**
     * Priorities of the different gamemodes
     * Modes with a lower index overwrite the ones with a higher one
     */
    final static List<GameMode> GM_PRIO = Arrays.asList(new GameMode[]{GameMode.CREATIVE, GameMode.SURVIVAL, GameMode.ADVENTURE, GameMode.SPECTATOR});

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
     public void onGameJoin(PlayerJoinEvent event) {
        if(!event.getPlayer().hasPermission("simplegamemodes.autogamemodeexempt")) {
            final UUID id = event.getPlayer().getUniqueId();
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
                @Override
                public void run() {   
                    Player p = Bukkit.getPlayer(id);
                    if(p != null && p.isOnline()) {
                        if(!p.hasPermission("simplegamemodes.gamemode." + p.getGameMode().toString().toLowerCase())) {
                            for (GameMode gm : GM_PRIO) {
                                if (p.hasPermission("simplegamemodes.gamemode." + gm.toString().toLowerCase())) {
                                    p.setGameMode(gm);
                                    Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to " + gm.toString() + " on game join.");
                                    p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + humanizeEnum(gm) + ChatColor.RED + "!");
                                }
                            }
                        }
                    }
                }
            }, 10L);
        }
    }

    @EventHandler
    public void onGameJoin(PlayerChangedWorldEvent event) {
        if(!event.getPlayer().hasPermission("simplegamemodes.autogamemodeexempt")) {
            final UUID id = event.getPlayer().getUniqueId();
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
                @Override
                public void run() {
                    Player p = Bukkit.getPlayer(id);
                    if(p != null && p.isOnline()) {
                        if(!p.hasPermission("simplegamemodes.gamemode." + p.getGameMode().toString().toLowerCase())) {
                            for (GameMode gm : GM_PRIO) {
                                if (p.hasPermission("simplegamemodes.gamemode." + gm.toString().toLowerCase())) {
                                    p.setGameMode(gm);
                                    Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to " + gm.toString() + " on world change.");
                                    p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + humanizeEnum(gm) + ChatColor.RED + "!");
                                }
                            }
                        }
                    }
                }
            }, 10L);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gamemode") && sender.hasPermission("simplegamemodes.command.gamemode")) {
            if (args.length > 0) {
                String[] newargs = new String[args.length - 1];
                for (int i = 1; i < args.length; i++)
                    newargs[i - 1] = args[i];
                if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
                    onCommand(sender, this.getCommand("gmc"), cmd.getLabel(), newargs);
                } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
                    onCommand(sender, this.getCommand("gms"), cmd.getLabel(), newargs);
                } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
                    onCommand(sender, this.getCommand("gma"), cmd.getLabel(), newargs);
                } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3")) {
                    onCommand(sender, this.getCommand("gmsp"), cmd.getLabel(), newargs);
                } else {
                    return false;
                }
            } else {
                return false;
            }
            
        } else if (cmd.getName().equalsIgnoreCase("checkgamemode") && sender.hasPermission("simplegamemodes.command.checkgamemode")) {
            if(args.length == 0) {
                if(sender instanceof Player)
                    sender.sendMessage(ChatColor.RED + "Your gamemode is " + ChatColor.YELLOW + humanizeEnum(((Player) sender).getGameMode()));
                else
                    sender.sendMessage("This command can only be run by a player without arguments. Use " + cmd.getName() + "<player...> to run it from the console!");
            } else if(args.length > 0 && sender.hasPermission("simplegamemodes.command.checkgamemode.others")) {
                for (String s : args) {
                    Player p = Bukkit.getPlayer(s);
                    if (p != null && p.isOnline()) {
                        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + p.getName() + ChatColor.RED + " is in " + ChatColor.YELLOW + humanizeEnum(p.getGameMode()) + ChatColor.RED + " mode!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " was not found online!");
                    }
                }
            } else {
                sender.sendMessage("You don't have permission to check the gamemode of other players!");
            }
            
        } else if (sender.hasPermission("simplegamemodes.command.gamemode")) {
            
            GameMode gm;
            if(cmd.getName().equalsIgnoreCase("gmc")) {
                gm = GameMode.CREATIVE;
            } else if(cmd.getName().equalsIgnoreCase("gms")) {
                gm = GameMode.SURVIVAL;
            } else if(cmd.getName().equalsIgnoreCase("gma")) {
                gm = GameMode.ADVENTURE;
            } else if(cmd.getName().equalsIgnoreCase("gmsp")) {
                gm = GameMode.SPECTATOR;
            } else {
                return false;
            }
            
            if(sender.hasPermission("simplegamemodes.gamemode." + gm.toString().toLowerCase())) {
                if (args.length > 0 && sender.hasPermission("simplegamemodes.command.gamemode.others")) {
                    for (String s : args) {
                        Player p = Bukkit.getPlayer(s);
                        if (p != null && p.isOnline()) {
                            if (p.getGameMode() != gm) {
                                p.setGameMode(gm);
                                getLogger().info(sender.getName() + " set " + p.getName() + "'s gamemode to " + gm.toString());
                                p.sendMessage(ChatColor.YELLOW + sender.getName() + ChatColor.RED + " set your gamemode to " + ChatColor.YELLOW + humanizeEnum(gm) + ChatColor.RED + "!");
                                sender.sendMessage(ChatColor.RED + "Set gamemode of " + ChatColor.YELLOW + s + ChatColor.RED + " to " + ChatColor.YELLOW + humanizeEnum(gm) + ChatColor.RED + "!");
                            } else {
                                sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " is already in " + humanizeEnum(gm) + " mode!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " was not found online!");
                        }
                    }
                } else if (sender instanceof Player) {
                    if (((Player) sender).getGameMode() != gm) {
                        ((Player) sender).setGameMode(gm);
                        getLogger().info(sender.getName() + " set own gamemode to " + gm.toString());
                        sender.sendMessage(ChatColor.RED + "Set your gamemode to " + ChatColor.YELLOW + humanizeEnum(gm) + ChatColor.RED + "!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "You are already in " + ChatColor.YELLOW + humanizeEnum(gm) + ChatColor.RED + " mode!");
                    }
                } else {
                    sender.sendMessage("This command can only be run by a player without arguments! Use " + cmd.getName() + "<player...> to run it from the console!");
                }
            } else {
                sender.sendMessage("You don't have permission to access the gamemode " + humanizeEnum(gm) + "!");
            }
            
        } else {
            sender.sendMessage("You don't have permission to run this command!");
        }
        return true;
    }

    public static String humanizeEnum(Enum e) {
        String name = e.toString();
        if (name.length() > 0)
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        else return name;
    }
}
