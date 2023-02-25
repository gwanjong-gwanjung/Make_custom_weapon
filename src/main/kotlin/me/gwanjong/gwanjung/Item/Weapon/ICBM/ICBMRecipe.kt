package me.gwanjong.gwanjung.Item.Weapon.ICBM

import me.gwanjong.gwanjung.UI.WandUI
import me.gwanjong.gwanjung.tool.MakeRecipeFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe


fun ICBMRecipe(server: Server) {

    val warhead = ItemStack(Material.TNT).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}탄두"))
        }
    }

    val body = ItemStack(Material.IRON_BLOCK).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}몸체"))
        }
    }

    val engine = ItemStack(Material.POINTED_DRIPSTONE).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}엔진"))
        }
    }

    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("warhead"),
            warhead
        ).apply {
            shape(
                "TTT",
                "TNT",
                "TTT"
            )
            setIngredient('T', ItemStack(Material.TNT))
            setIngredient('N', ItemStack(Material.NETHER_STAR))
        }
    )

    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("body"),
            body
        ).apply {
            shape(
                "ICI",
                "ICI",
                "ICI"
            )
            setIngredient('C', ItemStack(Material.COAL_BLOCK))
            setIngredient('I', ItemStack(Material.IRON_BLOCK))
        }
    )

    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("engine"),
            engine
        ).apply {
            shape(
                "III",
                "III",
                "INI"
            )
            setIngredient('N', ItemStack(Material.NETHER_STAR))
            setIngredient('I', ItemStack(Material.IRON_BLOCK))
        }
    )

    server.addRecipe(
        ShapedRecipe(
            NamespacedKey.minecraft("icbm"),
            engine
        ).apply {
            shape(
                " W ",
                " B ",
                " E "
            )
            setIngredient('W', warhead)
            setIngredient('B', body)
            setIngredient('E', engine)
        }
    )
}

class ICBMFrame : MakeRecipeFrame() {

    private val warhead = ItemStack(Material.TNT).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}탄두"))
        }
    }

    private val body = ItemStack(Material.IRON_BLOCK).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}몸체"))
        }
    }

    private val engine = ItemStack(Material.POINTED_DRIPSTONE).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}엔진"))
        }
    }

    private val air = ItemStack(Material.AIR)

    override fun setItem(): ItemStack {
        return ICBM()
    }

    override fun recipe(): Array<ItemStack> {

        val item = arrayOf(
            air, warhead, air,
            air, body, air,
            air, engine, air
        )

        return item
    }

    override fun back(player: Player) {
        WandUI(player)
    }

}