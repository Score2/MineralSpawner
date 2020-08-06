package me.scoretwo.mineralspawner.bukkit

import com.alibaba.fastjson.JSON
import me.scoretwo.mineralspawner.bukkit.command.Commands
import me.scoretwo.mineralspawner.bukkit.listener.BlockListeners
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.command.CommandMap
import org.bukkit.command.SimpleCommandMap
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.plugin.java.JavaPlugin
import org.yaml.snakeyaml.Yaml
import com.alibaba.fastjson.JSONObject
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileInputStream
import java.nio.charset.StandardCharsets

class MineralSpawner : JavaPlugin() {

    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        onReload()

        Bukkit.getPluginManager().registerEvents(BlockListeners(), this)

        getCommandMap().register("MineralSpawner", Commands())
    }

    companion object{
        lateinit var instance : MineralSpawner

        fun onReload() {
            OreSpawns.groups.clear()
            val yaml = Yaml()

            val json = JSON.parseObject(JSONObject.toJSONString(yaml.load(FileInputStream(File(instance.dataFolder,"config.yml")))))

            for (array in json.getJSONArray("groups")) {
                if (array !is JSONObject) continue
                OreSpawns(array)
            }
        }


        fun getCommandMap() : CommandMap {
            return Bukkit.getServer().javaClass.getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer()) as SimpleCommandMap
        }
    }
}