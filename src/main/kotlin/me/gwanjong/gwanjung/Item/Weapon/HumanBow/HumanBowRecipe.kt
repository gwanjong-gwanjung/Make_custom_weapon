package me.gwanjong.gwanjung.Item.Weapon.HumanBow

import me.gwanjong.gwanjung.UI.BowUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe

fun HumanBowRecipe(server: Server) {
    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("playerhead"),
            ItemStack(Material.PLAYER_HEAD)
        ).apply {
            shape("ABC", "ABC", "ABC")
            setIngredient('A', Material.REDSTONE)
            setIngredient('B', Material.BONE)
            setIngredient('C', Material.ROTTEN_FLESH)
        }
    )


    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("humanbow"),
            HumanBow()
        ).apply {
            shape(
                "HHH",
                "HBH",
                "HHH"
            )
            setIngredient('H', ItemStack(Material.PLAYER_HEAD))
            setIngredient('B', ItemStack(Material.BOW))
        }
    )
}

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

    override fun back(player: Player) {
        BowUI(player)
    }

}

