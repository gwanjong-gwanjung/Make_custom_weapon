package me.gwanjong.gwanjung.Weapon.HumanBow

import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class HumanBowFrame() : MakeRecipeFrame() {

    val head = ItemStack(Material.PLAYER_HEAD)

    override fun setItem(): ItemStack {
        return HumanBow()
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            head, head, head,
            head, ItemStack(Material.BOW), head,
            head, head, head
        )

        return item
    }
}