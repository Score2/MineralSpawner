package me.scoretwo.mineralspawner.bukkit.listener

import me.scoretwo.mineralspawner.bukkit.MineralSpawner
import me.scoretwo.mineralspawner.bukkit.OreSpawns
import me.scoretwo.utils.configuration.patchs.getLowerCaseNode
import org.bukkit.*
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Waterlogged
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFromToEvent
import kotlin.math.sqrt

class BlockListeners : Listener {

    @EventHandler
    fun onExecute(e: BlockFromToEvent) {
        val toBlock = e.toBlock
        val block = e.block
        val candidate = replaceOre(toBlock.world)

        var allow = false

//        println("触发事件")
        if (candidate == false) {
//            println("不替换")
            return
        }

        if (toBlock == block) {
//            println("一样的方块")
            return
        }

//        println("block: ${block.type.name}, x: ${block.location.x}, y: ${block.location.y}, z: ${block.location.z}")
//        println("toBlock: ${toBlock.type.name}, x: ${toBlock.location.x}, y: ${toBlock.location.y}, z: ${toBlock.location.z}")
        if (toBlock.type != Material.AIR || block.type != Material.LAVA) {
//            println("不符合规范")
            return
        }

        var water = false
        var lava = false
        for (bf in listOf(BlockFace.NORTH, BlockFace.WEST, BlockFace.EAST, BlockFace.SOUTH)) {
            if (toBlock.getRelative(bf).type == Material.WATER || if (toBlock.getRelative(bf).blockData is Waterlogged) (toBlock.getRelative(bf).blockData as Waterlogged).isWaterlogged else false) {
                water = true
            }
            if (toBlock.getRelative(bf).type == Material.LAVA) {
                lava = true
            }

        }
        if (water && lava) {
            allow = true
        }

        // 地狱刷石机
        if (toBlock.getRelative(BlockFace.DOWN).type == Material.SOUL_SOIL)
            for (bf in listOf(BlockFace.NORTH, BlockFace.WEST, BlockFace.EAST, BlockFace.SOUTH, BlockFace.UP)) {
            if (toBlock.getRelative(bf).type == Material.BLUE_ICE) {
                allow = true
            }
        }

        if (allow) {
//        toBlock.type = candidate as Material
            e.isCancelled = true

            e.toBlock.type = candidate as Material

            val stringSound = MineralSpawner.parsePlaceholder(
                    getLocationNearPlayer(e.toBlock.location)!!,
                    MineralSpawner.config.getString(MineralSpawner.config.getLowerCaseNode("settings.spawn-sound"))
            ).split("-").toMutableList()

            val sound = Sound.valueOf(stringSound[0])
            val volume = stringSound[1].toFloat()
            val pitch = stringSound[2].toFloat()

            e.toBlock.world.playSound(e.toBlock.location, sound, volume, pitch)
//            e.toBlock.world.playSound(e.toBlock.location, Sound.ENTITY_ITEM_PICKUP, 1.0.toFloat(), "0.${(5..9).random()}".toFloat())
        }

    }

    fun getLocationNearPlayer(location: Location): Player? {
        var result: Player? = null
        var lastDistance: Double = Double.MAX_VALUE
        for (p in location.world!!.players) {
            val distance: Double = location.distanceSquared(p.location)
            if (distance < sqrt(lastDistance)) {
                lastDistance = distance
                result = p
            }
        }

        return result
    }

    fun replaceOre(world: World) : Any {
        for (os in OreSpawns.groups) {
            if (!os.worlds.contains(world.name)) {
                continue
            }

            var total = 0
            os.oresses.values.forEach{ i -> total += i}
            val rd = (0..total).random()
            var add = 0

            for (i in 0 until os.oresses.keys.size) {
                if (i == os.oresses.keys.size - 1) {
                    return os.oresses.keys.toMutableList()[i]
                }
                if ((add + os.oresses.values.toMutableList()[i]) > rd) {
                    return os.oresses.keys.toMutableList()[i]
                }
                add += os.oresses.values.toMutableList()[i]

            }

        }

        return false
    }

}