package commands.util


import Main
import commands.AnnotationFeature
import controllers.PingController
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.incendo.cloud.annotations.*

class PingCommand : AnnotationFeature {
    override fun registerFeature(
        main: Main,
        annotationParser: AnnotationParser<CommandSender?>
    ) {
        annotationParser.parse(this)
    }

    @Command("ping")
    @Permission("simpleessentials.give")
    fun commandPing(
        player: Player
    ) {
        PingController().getPingResponse(player.name).whenComplete { result, exception ->
            player.sendMessage(result)
        }
    }
}