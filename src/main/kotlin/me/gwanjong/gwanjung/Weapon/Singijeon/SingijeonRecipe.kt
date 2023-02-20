package me.gwanjong.gwanjung.Weapon.Singijeon

import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Server
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe

fun SingijeonRecipe(server: Server) {

    val tntarrow = ItemStack(Material.ARROW).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}폭발화살"))
        }
    }

    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("tntarrow"),
            tntarrow
        ).apply {
            shape(
                "AAA",
                "ATA",
                "AAA"
            )
            setIngredient('T', ItemStack(Material.TNT))
            setIngredient('A', ItemStack(Material.ARROW))
        }
    )

    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("singijeon"),
            Singijeon()
        ).apply {
            shape(
                "TTT",
                "TBT",
                "TTT"
            )
            setIngredient('T', tntarrow)
            setIngredient('B', ItemStack(Material.BOW))
        }
    )

}