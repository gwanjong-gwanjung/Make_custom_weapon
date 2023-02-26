package me.gwanjong.gwanjung.Item.Weapon.HyperMonsterBow

import me.gwanjong.gwanjung.UI.BowUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe

fun HyperMonsterBowRecipe(server: Server) {
    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("hypermonsterbow"),
            HyperMonsterBow()
        ).apply {
            shape("RRR", "SBS", "GGG")
            setIngredient('G', Material.GUNPOWDER)
            setIngredient('S', Material.BONE)
            setIngredient('B', Material.BOW)
            setIngredient('R', Material.ROTTEN_FLESH)
        }
    )

}

class HyperMonsterBowFrame : MakeRecipeFrame() {

    override fun setItem(): ItemStack {
        return HyperMonsterBow()
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            ItemStack(Material.ROTTEN_FLESH), ItemStack(Material.ROTTEN_FLESH), ItemStack(Material.ROTTEN_FLESH),
            ItemStack(Material.BONE), ItemStack(Material.BOW), ItemStack(Material.BONE),
            ItemStack(Material.GUNPOWDER), ItemStack(Material.GUNPOWDER), ItemStack(Material.GUNPOWDER)
        )

        return item
    }

    override fun back(player: Player) {
        BowUI(player)
    }
}