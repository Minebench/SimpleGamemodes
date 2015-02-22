# SimpleGamemodes
Simple permission based gamemode management bukkit plugin.

It features permission based auto gamemode on server join and world change. It also has some Essentials-like gamemode change commands (/gms etc.)

Commands:
------
    gamemode:
       aliases: [gm]
       description: Main gamemode change command
       usage: /<command> <mode> [<player...>]
       permission: simplegamemodes.command.gamemode
       permission-message: You don't have the permission <permission>
    gmc:
       description: Change gamemode to creative
       usage: /<command> [<player...>]
       permission: simplegamemodes.command.gamemode
       permission-message: You don't have the permission <permission>
    gms:
       description: Change gamemode to survival
       usage: /<command> [<player...>]
       permission: simplegamemodes.command.gamemode
       permission-message: You don't have the permission <permission>
    gma:
       description: Change gamemode to adventure
       usage: /<command> [<player...>]
       permission: simplegamemodes.command.gamemode
       permission-message: You don't have the permission <permission>
    gmsp:
       description: Change gamemode to survival
       usage: /<command> [<player...>]
       permission: simplegamemodes.command.gamemode
       permission-message: You don't have the permission <permission>
    checkgamemode:
       aliases: [checkmode,cgm]
       description: Get the gamemode of a player
       usage: /<command> [<player...>]
       permission: simplegamemodes.command.checkgamemode
       permission-message: You don't have the permission <permission>

Permissions:
------
    simplegamemodes.command.gamemode:
       description: Gives permission to use the gamemode command
       default: op
    simplegamemodes.command.gamemode.others:
       description: Gives permission to use the gamemode command on other players
       default: op
    simplegamemodes.command.checkgamemode:
       description: Gives permission to use the checkgamemode command
       default: op
    simplegamemodes.command.checkgamemode.others:
       description: Gives permission to use the checkgamemode command on other players
       default: op
    simplegamemodes.gamemode.creative:
       description: Gives permission to access the gamemode creative via command & join/world change. Priority 1
       default: op
    simplegamemodes.gamemode.survival:
       description: Gives permission to access the gamemode survival via command & join/world change. Priority 2
       default: op
    simplegamemodes.gamemode.adventure:
       description: Gives permission to access the gamemode adventure via command & join/world change. Priority 3
       default: op
    simplegamemodes.gamemode.spectator:
       description: Gives permission to access the gamemode spectator via command & join/world change. Priority 4
       default: op
    simplegamemodes.autogamemodeexempt:
       description: Does not set your gamemode on join or world change.
       default: op
