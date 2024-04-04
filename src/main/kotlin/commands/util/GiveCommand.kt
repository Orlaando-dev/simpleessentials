package commands.util


import Main
import commands.AnnotationFeature
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.incendo.cloud.annotations.*

class GiveCommand : AnnotationFeature {
    override fun registerFeature(
        main: Main,
        annotationParser: AnnotationParser<CommandSender?>
    ) {
        annotationParser.parse(this)
    }

    @Command("give <material> <amount>")
    @Permission("simpleessentials.give")
    fun commandGive(
        player: Player,
        @Argument("material") material: Material,
        @Argument("amount") number: Int,
    ) {
        val itemStack = ItemStack(material, number)
        var itemName = String.format(
            "%s's %s",
            player.name,
            material.name
                .lowercase()
                .replace('_', ' ')
        )
        val meta = itemStack.itemMeta
        if (meta != null) {
            meta.setDisplayName(itemName)
            itemStack.setItemMeta(meta)
        }
        player.inventory.addItem(itemStack)
        player.sendMessage(ChatColor.GREEN.toString() + String.format("You have been given %d x %s", number, material))
    }
}