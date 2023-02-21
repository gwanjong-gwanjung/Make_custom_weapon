package me.gwanjong.gwanjung

import io.github.monun.invfx.InvFX
import io.github.monun.invfx.openFrame
import me.gwanjong.gwanjung.Weapon.Singijeon.Singijeon
import me.gwanjong.gwanjung.Weapon.Singijeon.SingijeonFrame
import me.gwanjong.gwanjung.Weapon.Singijeon.tntarrowFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun mainFrame(player: Player){
    val main = InvFX.frame(1, Component.text("Weapon")) {

        slot (0, 0) {
            item = Singijeon()
            onClick { clickEvent ->
                SingijeonFrame().openFrame(player)
            }
        }

        slot (1, 0) {
            val tntarrow = ItemStack(Material.ARROW).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("${ChatColor.LIGHT_PURPLE}폭발화살"))
                }
            }

            item = tntarrow
            onClick { clickEvent ->
                tntarrowFrame().openFrame(player)
            }
        }

        slot (8, 0) {

            val next = ItemStack(Material.BARRIER).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("닫기"))
                }
            }

            item = next
            onClick { clickEvent ->
                clickEvent.whoClicked.closeInventory()
            }
        }

    }
    player.openFrame(main)

}