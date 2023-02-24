package me.gwanjong.gwanjung.Item.Weapon.Singijeon

import me.gwanjong.gwanjung.UI.BowUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Server
import org.bukkit.entity.Player
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


class SingijeonFrame : MakeRecipeFrame() {

    private val tntarrow = ItemStack(Material.ARROW).apply {
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

    override fun back(player: Player) {
        BowUI(player)
    }
}

