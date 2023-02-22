package me.gwanjong.gwanjung.Item.subItem

import me.gwanjong.gwanjung.UI.SubItemUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class PlayerHeadFrame() : MakeRecipeFrame() {

    val redstone = ItemStack(Material.REDSTONE)
    val bone = ItemStack(Material.BONE)
    val flesh = ItemStack(Material.ROTTEN_FLESH)

    override fun setItem(): ItemStack {
        return ItemStack(Material.PLAYER_HEAD)
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            redstone, bone, flesh,
            redstone, bone, flesh,
            redstone, bone, flesh
        )

        return item
    }

    override fun back(player: Player) {
        SubItemUI(player)
    }

}