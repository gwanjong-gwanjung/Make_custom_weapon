package me.gwanjong.gwanjung.UI

import io.github.monun.invfx.InvFX
import io.github.monun.invfx.openFrame
import me.gwanjong.gwanjung.Item.Weapon.Hyperion.Hyperion
import me.gwanjong.gwanjung.Item.Weapon.Hyperion.HyperionFrame
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun SwordUI(player: Player) {
    val SwordUI = InvFX.frame(1, Component.text("Sword")) {

        slot (0, 0) {
            item = Hyperion()
            onClick { clickEvent ->
                HyperionFrame().openFrame(player)
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
    player.openFrame(SwordUI)
}