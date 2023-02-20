package me.gwanjong.gwanjung.Weapon.Hyperion

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Server
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