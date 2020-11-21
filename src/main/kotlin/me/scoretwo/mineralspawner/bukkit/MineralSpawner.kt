package me.scoretwo.mineralspawner.bukkit

import me.scoretwo.mineralspawner.bukkit.command.Commands
import me.scoretwo.mineralspawner.bukkit.hook.PlaceholderAPIHook
import me.scoretwo.mineralspawner.bukkit.listener.BlockListeners
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.SimpleCommandMap
import org.bukkit.plugin.java.JavaPlugin
import org.yaml.snakeyaml.Yaml
import me.scoretwo.utils.configuration.file.YamlConfiguration
import me.scoretwo.utils.configuration.patchs.getConfigurationSectionList
import me.scoretwo.utils.configuration.patchs.getLowerCaseNode
import me.scoretwo.utils.configuration.patchs.loadConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.io.FileInputStream

class MineralSpawner : JavaPlugin() {

    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        reload()

        Bukkit.getPluginManager().registerEvents(BlockListeners(), this)

        getCommandMap().register("MineralSpawner", Commands())
    }

    companion object{
        lateinit var instance : MineralSpawner
        lateinit var config: YamlConfiguration
        lateinit var file: File

        fun reload() {
            OreSpawns.groups.clear()
            file = File(instance.dataFolder,"config.yml")
            config = file.loadConfiguration()

            if (!config.contains(config.getLowerCaseNode("groups"))) {
                return
            }

            for (section in config.getConfigurationSectionList(config.getLowerCaseNode("groups"))!!) {
                OreSpawns(
                        section.getStringList(section.getLowerCaseNode("spawns")),
                        section.getStringList(section.getLowerCaseNode("enabled-world"))
                )
            }
        }

        fun parsePlaceholder(player: Player, string: String): String {
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                return PlaceholderAPIHook.parsePlaceholder(player, string)
            }
            return string
        }

        fun getCommandMap() : CommandMap {
            return Bukkit.getServer().javaClass.getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer()) as SimpleCommandMap
        }
    }
}