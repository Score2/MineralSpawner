package me.scoretwo.mineralspawner.bukkit.listener

import me.scoretwo.mineralspawner.bukkit.MineralSpawner
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFromToEvent

class BlockListeners : Listener {

    @EventHandler
    fun onRun(e : BlockFromToEvent) {
        val toBlock = e.toBlock
        val block = e.block
        val candidate = MineralSpawner.replaceOre(toBlock.world)

        if (candidate == false) return

        if (toBlock == block) return

        if (toBlock.type == Material.AIR || toBlock.type == Material.CAVE_AIR || toBlock.type == Material.VOID_AIR || toBlock.type == Material.WATER) return

        toBlock.type = candidate as Material
        e.isCancelled = true
    }

}