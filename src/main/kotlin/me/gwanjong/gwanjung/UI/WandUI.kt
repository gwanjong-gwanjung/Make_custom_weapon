package me.gwanjong.gwanjung.UI

import io.github.monun.invfx.InvFX
import io.github.monun.invfx.openFrame
import me.gwanjong.gwanjung.Item.Weapon.ICBM.ICBM
import me.gwanjong.gwanjung.Item.Weapon.ICBM.ICBMFrame
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun WandUI(player: Player) {
    val WandUI = InvFX.frame(1, Component.text("Wand")) {

        slot (0, 0) {
            item = ICBM()
            onClick { clickEvent ->
                ICBMFrame().openFrame(player)
            }
        }

        slot (8, 0) {

            val next = ItemStack(Material.ARROW).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("뒤로가기"))
                }
            }

            item = next
            onClick { clickEvent ->
                MainUI(player)
            }
        }

    }
    player.openFrame(WandUI)
}