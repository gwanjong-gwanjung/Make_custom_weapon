package me.gwanjong.gwanjung.Weapon.HumanBow

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Server
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