package me.gwanjong.gwanjung.Item.subItem.ICBM

import me.gwanjong.gwanjung.UI.SubItemUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class EngineFrame : MakeRecipeFrame() {
    private val engine = ItemStack(Material.POINTED_DRIPSTONE).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}엔진"))
        }
    }

    private val iron = ItemStack(Material.IRON_BLOCK)
    private val star = ItemStack(Material.NETHER_STAR)

    override fun setItem(): ItemStack {
        return engine
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            iron, iron, iron,
            iron, iron, iron,
            iron, star, iron
        )

        return item
    }

    override fun back(player: Player) {
        SubItemUI(player)
    }

}