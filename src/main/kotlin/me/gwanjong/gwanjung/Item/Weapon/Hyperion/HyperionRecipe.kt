package me.gwanjong.gwanjung.Item.Weapon.Hyperion

import me.gwanjong.gwanjung.UI.SwordUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe

fun HyperionRecipe(server: Server) {
    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("hyperion"),
            Hyperion()
        ).apply {
            shape("CCC", "CSC", "CCC")
            setIngredient('S', Material.IRON_SWORD)
            setIngredient('C', Material.END_CRYSTAL)
        }
    )

}

class HyperionFrame : MakeRecipeFrame() {

    private val tnt = ItemStack(Material.END_CRYSTAL)

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

    override fun back(player: Player) {
        SwordUI(player)
    }
}