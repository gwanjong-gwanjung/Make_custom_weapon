package me.gwanjong.gwanjung

import io.github.monun.kommand.kommand
import me.gwanjong.gwanjung.Weapon.ElderWand.ElderWandEvent
import me.gwanjong.gwanjung.Weapon.HumanBow.HumanBow
import me.gwanjong.gwanjung.Weapon.HumanBow.HumanBowEvent
import me.gwanjong.gwanjung.Weapon.Hyperion.Hyperion
import me.gwanjong.gwanjung.Weapon.Hyperion.HyperionEvent
import me.gwanjong.gwanjung.Weapon.Singijeon.Singijeon
import me.gwanjong.gwanjung.Weapon.Singijeon.SingijeonEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

var cultural_language = false

class Main : JavaPlugin(), Listener{

    override fun onEnable() {

        cultural_language = false

        Bukkit.getPluginManager().registerEvents(ElderWandEvent(), this)
        Bukkit.getPluginManager().registerEvents(HyperionEvent(), this)
        Bukkit.getPluginManager().registerEvents(SingijeonEvent(), this)
        Bukkit.getPluginManager().registerEvents(HumanBowEvent(), this)

        HumanBowRecipe()
        HyperionRecipe()
        SingijeonRecipe()

        setupCommands()

        logger.info("==============================")
        logger.info("        plugin is loaded      ")
        logger.info("==============================")

    }

    private fun setupCommands() = kommand {
        Command.register(this@Main, this)
    }



    private fun HumanBowRecipe() {
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

    private fun HyperionRecipe() {
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

    private fun SingijeonRecipe() {

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
}
