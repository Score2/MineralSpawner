package me.scoretwo.mineralspawner.bukkit.hook

import me.clip.placeholderapi.PlaceholderAPI
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.scoretwo.mineralspawner.bukkit.MineralSpawner
import org.bukkit.entity.Player

class PlaceholderAPIHook: PlaceholderExpansion() {

    override fun getIdentifier(): String {
        return MineralSpawner.instance.description.name
    }

    override fun getAuthor(): String {
        return MineralSpawner.instance.description.authors.toString()
    }

    override fun getVersion(): String {
        return MineralSpawner.instance.description.version
    }

    companion object {

        fun parsePlaceholder(player: Player, string: String): String {
            return PlaceholderAPI.setPlaceholders(player, string)
        }

    }

}