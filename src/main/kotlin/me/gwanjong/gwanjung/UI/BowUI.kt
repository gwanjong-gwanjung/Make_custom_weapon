package me.gwanjong.gwanjung.UI

import io.github.monun.invfx.InvFX
import io.github.monun.invfx.openFrame
import me.gwanjong.gwanjung.Item.Weapon.HumanBow.HumanBow
import me.gwanjong.gwanjung.Item.Weapon.HumanBow.HumanBowFrame
import me.gwanjong.gwanjung.Item.Weapon.HyperMonsterBow.HyperMonsterBow
import me.gwanjong.gwanjung.Item.Weapon.HyperMonsterBow.HyperMonsterBowFrame
import me.gwanjong.gwanjung.Item.Weapon.Singijeon.Singijeon
import me.gwanjong.gwanjung.Item.Weapon.Singijeon.SingijeonFrame
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun BowUI(player: Player) {

    val BowUI = InvFX.frame(1, Component.text("Bow")) {

        slot (0, 0) {
            item = Singijeon()
            onClick { clickEvent ->
                SingijeonFrame().openFrame(player)
            }
        }

        slot (1, 0) {
            item = HumanBow()
            onClick { clickEvent ->
                HumanBowFrame().openFrame(player)
            }
        }

        slot (2, 0) {
            item = HyperMonsterBow()
            onClick { clickEvent ->
                HyperMonsterBowFrame().openFrame(player)
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
    player.openFrame(BowUI)

}