package me.scoretwo.mineralspawner.bukkit

import org.bukkit.Material
import java.util.*
import kotlin.collections.ArrayList

class OreSpawns(spawns: MutableList<String>, var worlds: MutableList<String>) {

    val oresses : MutableMap<Material, Int> = EnumMap(org.bukkit.Material::class.java)

    init {
        for (spawn in spawns) {
            val type = Material.valueOf(spawn.substring(0, spawn.indexOf(":")))
            val chance = spawn.substring(spawn.indexOf(":") + 1).toInt()

            oresses[type] = chance
        }
        groups.add(this)
        println("新的组添加成功")
    }

    companion object {
        val groups: MutableList<OreSpawns> = ArrayList()
    }
}