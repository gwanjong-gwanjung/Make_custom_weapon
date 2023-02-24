package me.gwanjong.gwanjung.Item.subItem.ICBM

import me.gwanjong.gwanjung.UI.SubItemUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class WarheadFrame : MakeRecipeFrame() {

    private val warhead = ItemStack(Material.TNT).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}탄두"))
        }
    }

    private val tnt = ItemStack(Material.TNT)
    private val star = ItemStack(Material.NETHER_STAR)

    override fun setItem(): ItemStack {
        return warhead
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            tnt, tnt, tnt,
            tnt, star, tnt,
            tnt, tnt, tnt
        )

        return item
    }

    override fun back(player: Player) {
        SubItemUI(player)
    }

}