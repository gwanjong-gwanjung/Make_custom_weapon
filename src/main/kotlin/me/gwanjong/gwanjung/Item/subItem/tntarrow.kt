package me.gwanjong.gwanjung.Item.subItem

import me.gwanjong.gwanjung.UI.SubItemUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun tntarrow() : ItemStack{
    val tntarrow = ItemStack(Material.ARROW).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}폭발화살"))
        }
    }

    return tntarrow
}

class tntarrowFrame() : MakeRecipeFrame() {

    val tntarrow = tntarrow()
    val arrow = ItemStack(Material.ARROW)

    override fun setItem(): ItemStack {
        return tntarrow
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            arrow, arrow, arrow,
            arrow, ItemStack(Material.TNT), arrow,
            arrow, arrow, arrow
        )

        return item
    }

    override fun back(player: Player) {
        SubItemUI(player)
    }

}