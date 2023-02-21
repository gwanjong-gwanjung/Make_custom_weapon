package me.gwanjong.gwanjung.Weapon.Singijeon

import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class SingijeonFrame() : MakeRecipeFrame() {

    val tntarrow = ItemStack(Material.ARROW).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}폭발화살"))
        }
    }

    override fun setItem(): ItemStack {
        return Singijeon()
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            tntarrow, tntarrow, tntarrow,
            tntarrow, Singijeon(), tntarrow,
            tntarrow, tntarrow, tntarrow
        )

        return item
    }
}

class tntarrowFrame() : MakeRecipeFrame() {

    val tntarrow = ItemStack(Material.ARROW).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}폭발화살"))
        }
    }

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
}