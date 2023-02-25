package me.gwanjong.gwanjung.Item.subItem.ICBM

import me.gwanjong.gwanjung.Item.DropItem.ElderGuardianEye
import me.gwanjong.gwanjung.UI.SubItemUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class BodyFrame : MakeRecipeFrame() {

    private val body = ItemStack(Material.IRON_BLOCK).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}몸체"))
        }
    }

    private val coal = ItemStack(Material.COAL_BLOCK)
    private val iron = ItemStack(Material.IRON_BLOCK)

    override fun setItem(): ItemStack {
        return body
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            iron, coal, iron,
            iron, ElderGuardianEye(), iron,
            iron, coal, iron
        )

        return item
    }

    override fun back(player: Player) {
        SubItemUI(player)
    }

}