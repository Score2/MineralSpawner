package me.scoretwo.mineralspawner.bukkit

import me.scoretwo.mineralspawner.bukkit.command.Commands
import me.scoretwo.mineralspawner.bukkit.listener.BlockListeners
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.command.CommandMap
import org.bukkit.command.SimpleCommandMap
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.plugin.java.JavaPlugin

class MineralSpawner : JavaPlugin() {

    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        Bukkit.getPluginManager().registerEvents(BlockListeners(), this)

        getCommandMap().register("MineralSpawner", Commands())
    }

    companion object{
        lateinit var instance : MineralSpawner

        fun onReload() {
            OreSpawns.groups.clear()
            instance.reloadConfig()
            for (group in instance.config.getList("groups")!!) {
                if (group !is ConfigurationSection) continue
                OreSpawns(group)
            }
        }

        fun replaceOre(world : World) : Any {
            for (os in OreSpawns.groups) {
                if (!os.worlds.contains(world.name)) continue

                var total = 0
                os.oresses.values.forEach{i -> total += i}

                for (i in 0..os.oresses.keys.size) {
                    if (i == os.oresses.keys.size) {
                        return os.oresses.keys.toList()[i]
                    }

                    if (os.oresses[os.oresses.keys.toList()[i]]?.plus(os.oresses[os.oresses.keys.toList()[i + 1]]!!) ?: 0 > (0..total).random()) {
                        return os.oresses.keys.toList()[i]
                    }
                }

            }

            return false
        }

        fun getCommandMap() : CommandMap {
            return Bukkit.getServer().javaClass.getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer()) as SimpleCommandMap
        }
    }
}