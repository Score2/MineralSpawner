package me.scoretwo.mineralspawner.bukkit

import com.alibaba.fastjson.JSONObject
import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import java.util.*
import kotlin.collections.ArrayList

class OreSpawns {

    val oresses : MutableMap<Material, Int> = EnumMap(org.bukkit.Material::class.java)
    lateinit var worlds : MutableList<String>

    constructor(json : JSONObject) {
        val spawns: MutableList<String> = ArrayList()
        val worlds: MutableList<String> = ArrayList()

        json.getJSONArray("spawns").forEach {
            if (it is String) {
                spawns.add(it)
            }
        }
        json.getJSONArray("enabled-world").forEach {
            if (it is String) {
                worlds.add(it)
            }
        }

        OreSpawns(spawns, worlds)
    }

    constructor(section: ConfigurationSection) {
        OreSpawns(section.getStringList("spawns"), section.getStringList("enabled-world"))
    }

    constructor(spawns: MutableList<String>, worlds : MutableList<String>) {
        for (spawn in spawns) {
            val type = Material.valueOf(spawn.substring(0, spawn.indexOf(":")))
            val chance = spawn.substring(spawn.indexOf(":") + 1).toInt()

            oresses[type] = chance
        }
        this.worlds = worlds
        groups.add(this)
        println("新的组添加成功")
    }

    companion object {
        val groups: MutableList<OreSpawns> = ArrayList()
    }
}