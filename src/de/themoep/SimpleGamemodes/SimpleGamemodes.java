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

import java.util.UUID;

public class SimpleGamemodes extends JavaPlugin implements Listener, CommandExecutor {

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
     public void onGameJoin(PlayerJoinEvent event) {
        if(!event.getPlayer().hasPermission(" simplegamemodes.autogamemodeexempt")) {
            final UUID id = event.getPlayer().getUniqueId();
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
                @Override
                public void run() {   
                    Player p = Bukkit.getPlayer(id);
                    if(p != null && p.isOnline()) {
                        if (p.hasPermission("simplegamemodes.gamemode.creative") && p.getGameMode() != GameMode.CREATIVE) {
                            p.setGameMode(GameMode.CREATIVE);
                            Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to creative on game join.");
                            p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + "Creative" + ChatColor.RED + "!");
                        } else if (p.hasPermission("simplegamemodes.gamemode.survival") && p.getGameMode() != GameMode.SURVIVAL) {
                            p.setGameMode(GameMode.SURVIVAL);
                            Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to creative on game join.");
                            p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + "Survival" + ChatColor.RED + "!");
                        } else if (p.hasPermission("simplegamemodes.gamemode.adventure") && p.getGameMode() != GameMode.ADVENTURE) {
                            p.setGameMode(GameMode.ADVENTURE);
                            Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to creative on game join.");
                            p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + "Adventure" + ChatColor.RED + "!");
                        } else if (p.hasPermission("simplegamemodes.gamemode.spectator") && p.getGameMode() != GameMode.SPECTATOR) {
                            p.setGameMode(GameMode.SPECTATOR);
                            Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to creative on game join.");
                            p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + "Spectator" + ChatColor.RED + "!");
                        }
                    }
                }
            }, 10L);
        }
    }

    @EventHandler
    public void onGameJoin(PlayerChangedWorldEvent event) {
        if(!event.getPlayer().hasPermission(" simplegamemodes.autogamemodeexempt")) {
            final UUID id = event.getPlayer().getUniqueId();
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
                @Override
                public void run() {
                    Player p = Bukkit.getPlayer(id);
                    if(p != null && p.isOnline()) {
                        if (p.hasPermission("simplegamemodes.gamemode.creative") && p.getGameMode() != GameMode.CREATIVE) {
                            p.setGameMode(GameMode.CREATIVE);
                            Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to creative on world change.");
                            p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + "Creative" + ChatColor.RED + "!");
                        } else if (p.hasPermission("simplegamemodes.gamemode.survival") && p.getGameMode() != GameMode.SURVIVAL) {
                            p.setGameMode(GameMode.SURVIVAL);
                            Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to creative on world change.");
                            p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + "Survival" + ChatColor.RED + "!");
                        } else if (p.hasPermission("simplegamemodes.gamemode.adventure") && p.getGameMode() != GameMode.ADVENTURE) {
                            p.setGameMode(GameMode.ADVENTURE);
                            Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to creative on world change.");
                            p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + "Adventure" + ChatColor.RED + "!");
                        } else if (p.hasPermission("simplegamemodes.gamemode.spectator") && p.getGameMode() != GameMode.SPECTATOR) {
                            p.setGameMode(GameMode.SPECTATOR);
                            Bukkit.getServer().getLogger().info("Automatically set gamemode of " + p.getName() + " to creative on world change.");
                            p.sendMessage(ChatColor.RED + "Automatically set your gamemode to " + ChatColor.YELLOW + "Spectator" + ChatColor.RED + "!");
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
                } else
                    return false;
            } else
                return false;
        } else if (cmd.getName().equalsIgnoreCase("gmc") && sender.hasPermission("simplegamemodes.command.gamemode") && sender.hasPermission("simplegamemodes.gamemode.creative")) {
            if (args.length > 0 && sender.hasPermission("simplegamemodes.command.gamemode.others")) {
                for (String s : args) {
                    Player p = Bukkit.getPlayer(s);
                    if (p != null && p.isOnline()) {
                        if (p.getGameMode() != GameMode.CREATIVE) {
                            p.setGameMode(GameMode.CREATIVE);
                            this.getLogger().info(sender.getName() + " set " + p.getName() + "'s gamemode to Creative.");
                            p.sendMessage(ChatColor.YELLOW + sender.getName() + ChatColor.RED + " set your gamemode to " + ChatColor.YELLOW + "Creative" + ChatColor.RED + "!");
                            sender.sendMessage(ChatColor.RED + "Set gamemode of " + ChatColor.YELLOW + s + ChatColor.RED + " to " + ChatColor.YELLOW + "Creative" + ChatColor.RED + "!");
                        } else {
                            sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " is already in Creative mode!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " was not found online!");
                    }
                }
            } else if (sender instanceof Player) {
                if (((Player) sender).getGameMode() != GameMode.CREATIVE) {
                    ((Player) sender).setGameMode(GameMode.CREATIVE);
                    this.getLogger().info(sender.getName() + " set own gamemode to creative.");
                    sender.sendMessage(ChatColor.RED + "Set your gamemode to " + ChatColor.YELLOW + "Creative" + ChatColor.RED + "!");
                } else {
                    sender.sendMessage(ChatColor.RED + "You are already in " + ChatColor.YELLOW + "Creative" + ChatColor.RED + " mode!");
                }
            } else {
                sender.sendMessage("This command can only be run by a player without arguments! Use " + cmd.getName() + "<player...> to run it from the console!");
            }
        } else if (cmd.getName().equalsIgnoreCase("gms") && sender.hasPermission("simplegamemodes.command.gamemode") && sender.hasPermission("simplegamemodes.gamemode.survival")) {
            if (args.length > 0 && sender.hasPermission("simplegamemodes.command.gamemode.others")) {
                for (String s : args) {
                    Player p = Bukkit.getPlayer(s);
                    if (p != null && p.isOnline()) {
                        if (p.getGameMode() != GameMode.SURVIVAL) {
                            p.setGameMode(GameMode.SURVIVAL);
                            this.getLogger().info(sender.getName() + " set " + p.getName() + "'s gamemode to Survival.");
                            p.sendMessage(ChatColor.YELLOW + sender.getName() + ChatColor.RED + " set your gamemode to " + ChatColor.YELLOW + "Survival" + ChatColor.RED + "!");
                            sender.sendMessage(ChatColor.RED + "Set gamemode of " + ChatColor.YELLOW + s + ChatColor.RED + " to " + ChatColor.YELLOW + "Survival" + ChatColor.RED + "!");
                        } else {
                            sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " is already in Survival mode!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " was not found online!");
                    }
                }
            } else if (sender instanceof Player) {
                if (((Player) sender).getGameMode() != GameMode.SURVIVAL) {
                    ((Player) sender).setGameMode(GameMode.SURVIVAL);
                    this.getLogger().info(sender.getName() + " set own gamemode to Survival.");
                    sender.sendMessage(ChatColor.RED + "Set your gamemode to " + ChatColor.YELLOW + "Survival" + ChatColor.RED + "!");
                } else {
                    sender.sendMessage(ChatColor.RED + "You are already in " + ChatColor.YELLOW + "Survival" + ChatColor.RED + " mode!");
                }
            } else {
                sender.sendMessage("This command can only be run by a player without arguments! Use " + cmd.getName() + "<player...> to run it from the console!");
            }
        } else if (cmd.getName().equalsIgnoreCase("gma") && sender.hasPermission("simplegamemodes.command.gamemode") && sender.hasPermission("simplegamemodes.gamemode.adventure")) {
            if (args.length > 0 && sender.hasPermission("simplegamemodes.command.gamemode.others")) {
                for (String s : args) {
                    Player p = Bukkit.getPlayer(s);
                    if (p != null && p.isOnline()) {
                        if (p.getGameMode() != GameMode.ADVENTURE) {
                            p.setGameMode(GameMode.ADVENTURE);
                            this.getLogger().info(sender.getName() + " set " + p.getName() + "'s gamemode to Adventure.");
                            p.sendMessage(ChatColor.YELLOW + sender.getName() + ChatColor.RED + " set your gamemode to " + ChatColor.YELLOW + "Adventure" + ChatColor.RED + "!");
                            sender.sendMessage(ChatColor.RED + "Set gamemode of " + ChatColor.YELLOW + s + ChatColor.RED + " to " + ChatColor.YELLOW + "Adventure" + ChatColor.RED + "!");
                        } else {
                            sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " is already in Adventure mode!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " was not found online!");
                    }
                }
            } else if (sender instanceof Player) {
                if (((Player) sender).getGameMode() != GameMode.ADVENTURE) {
                    ((Player) sender).setGameMode(GameMode.ADVENTURE);
                    this.getLogger().info(sender.getName() + " set own gamemode to Adventure.");
                    sender.sendMessage(ChatColor.RED + "Set your gamemode to " + ChatColor.YELLOW + "Adventure" + ChatColor.RED + "!");
                } else {
                    sender.sendMessage(ChatColor.RED + "You are already in " + ChatColor.YELLOW + "Adventure" + ChatColor.RED + " mode!");
                }
            } else {
                sender.sendMessage("This command can only be run by a player without arguments! Use " + cmd.getName() + " <player...> to run it from the console!");
            }
        } else if (cmd.getName().equalsIgnoreCase("gmsp") && sender.hasPermission("simplegamemodes.command.gamemode") && sender.hasPermission("simplegamemodes.gamemode.spectator")) {
            if (args.length > 0 && sender.hasPermission("simplegamemodes.command.gamemode.others")) {
                for (String s : args) {
                    Player p = Bukkit.getPlayer(s);
                    if (p != null && p.isOnline()) {
                        if (p.getGameMode() != GameMode.SPECTATOR) {
                            p.setGameMode(GameMode.SPECTATOR);
                            this.getLogger().info(sender.getName() + " set " + p.getName() + "'s gamemode to Spectator.");
                            p.sendMessage(ChatColor.YELLOW + sender.getName() + ChatColor.RED + " set your gamemode to " + ChatColor.YELLOW + "Spectator" + ChatColor.RED + "!");
                            sender.sendMessage(ChatColor.RED + "Set gamemode of " + ChatColor.YELLOW + s + ChatColor.RED + " to " + ChatColor.YELLOW + "Spectator" + ChatColor.RED + "!");
                        } else {
                            sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " is already in Spectator mode!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " was not found online!");
                    }
                }
            } else if (sender instanceof Player) {
                if (((Player) sender).getGameMode() != GameMode.SPECTATOR) {
                    ((Player) sender).setGameMode(GameMode.SPECTATOR);
                    this.getLogger().info(sender.getName() + " set own gamemode to Spectator.");
                    sender.sendMessage(ChatColor.RED + "Set your gamemode to " + ChatColor.YELLOW + "Spectator" + ChatColor.RED + "!");
                } else {
                    sender.sendMessage(ChatColor.RED + "You are already in " + ChatColor.YELLOW + "Spectator" + ChatColor.RED + " mode!");
                }
            } else {
                sender.sendMessage("This command can only be run by a player without arguments! Use " + cmd.getName() + "<player...> to run it from the console!");
            }
        } else if (cmd.getName().equalsIgnoreCase("checkgamemode") && sender.hasPermission("simplegamemodes.command.checkgamemode")) {
            if(args.length == 0) {
                if(sender instanceof Player)
                    sender.sendMessage(ChatColor.RED + "Your gamemode is " + ChatColor.YELLOW + ((Player) sender).getGameMode().toString());
                else
                    sender.sendMessage("This command can only be run by a player without arguments. Use " + cmd.getName() + "<player...> to run it from the console!");
            } else if(args.length > 0 && sender.hasPermission("simplegamemodes.command.checkgamemode.others")) {
                for (String s : args) {
                    Player p = Bukkit.getPlayer(s);
                    if (p != null && p.isOnline()) {
                        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + p.getName() + ChatColor.RED + " is in " + ChatColor.YELLOW + p.getGameMode().toString() + ChatColor.RED + " mode!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + s + ChatColor.RED + " was not found online!");
                    }
                }
            } else {
                sender.sendMessage("You don't have permission to check the gamemode of other players!");
            }
        } else {
            sender.sendMessage("You don't have permission to run this command!");
        }
        return true;
    }

}
