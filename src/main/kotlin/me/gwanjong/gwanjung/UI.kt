package me.gwanjong.gwanjung

import io.github.monun.invfx.InvFX
import me.gwanjong.gwanjung.Item.Weapon.HumanBow.HumanBow
import me.gwanjong.gwanjung.Item.Weapon.HumanBow.HumanBowFrame
import me.gwanjong.gwanjung.Item.Weapon.Hyperion.Hyperion
import me.gwanjong.gwanjung.Item.Weapon.Hyperion.HyperionFrame
import me.gwanjong.gwanjung.Item.Weapon.Singijeon.Singijeon
import me.gwanjong.gwanjung.Item.Weapon.Singijeon.SingijeonFrame
import me.gwanjong.gwanjung.Item.subItem.tntarrow
import me.gwanjong.gwanjung.Item.subItem.tntarrowFrame
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun mainFrame(player: Player){


    val subitem = InvFX.frame(1, Component.text("Weapon")) {

        slot (0, 0) {
            item = Singijeon()
            onClick { clickEvent ->
                SingijeonFrame().openFrame(player)
            }
        }

        slot (1, 0) {
            val tntarrow = tntarrow()

            item = tntarrow
            onClick { clickEvent ->
                tntarrowFrame().openFrame(player)
            }
        }

        slot (2, 0) {
            item = Hyperion()
            onClick { clickEvent ->
                HyperionFrame().openFrame(player)
            }
        }

        slot (3, 0) {
            item = HumanBow()
            onClick { clickEvent ->
                HumanBowFrame().openFrame(player)
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
}