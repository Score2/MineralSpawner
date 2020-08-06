package me.scoretwo.mineralspawner.bukkit.command

import me.scoretwo.mineralspawner.bukkit.MineralSpawner
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.util.*

class Commands : Command("MineralSpawner","", "/ms", listOf("ms")) {

    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("MineralSpawner.admin")) {
            sender.sendMessage("§c权限不足.")
            return true
        }
        if (args.isEmpty()) {
            onHelp(sender, label)
            return true
        }

        when(args[0]) {
            "reload" -> {
                MineralSpawner.onReload()
                sender.sendMessage("§7[MineralSpawner] §2配置文件成功重新载入!")
            }
        }

        return true
    }

    fun onHelp(sender: CommandSender, label: String) {
        sender.sendMessage(" ")
        sender.sendMessage("§b/${label} help - 查看帮助信息")
        sender.sendMessage("§b/${label} reload - 重新载入配置文件")
        sender.sendMessage(" ")
    }

}