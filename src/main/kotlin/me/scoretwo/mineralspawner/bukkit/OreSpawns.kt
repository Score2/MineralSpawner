package me.scoretwo.mineralspawner.bukkit

import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import java.util.*

class OreSpawns {

    val oresses : MutableMap<Material, Int> = EnumMap(org.bukkit.Material::class.java)
    lateinit var worlds : MutableList<String>

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
    }

    companion object {
        val groups: MutableList<OreSpawns> = ArrayList()
    }
}