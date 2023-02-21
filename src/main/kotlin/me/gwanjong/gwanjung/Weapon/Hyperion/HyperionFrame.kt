package me.gwanjong.gwanjung.Weapon.Hyperion

import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class HyperionFrame() : MakeRecipeFrame() {

    val tnt = ItemStack(Material.END_CRYSTAL)

    override fun setItem(): ItemStack {
        return Hyperion()
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            tnt, tnt, tnt,
            tnt, ItemStack(Material.IRON_SWORD), tnt,
            tnt, tnt, tnt
        )

        return item
    }
}