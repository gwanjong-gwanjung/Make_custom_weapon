package me.gwanjong.gwanjung.UI

import io.github.monun.invfx.InvFX
import io.github.monun.invfx.openFrame
import me.gwanjong.gwanjung.Item.subItem.ICBM.BodyFrame
import me.gwanjong.gwanjung.Item.subItem.ICBM.EngineFrame
import me.gwanjong.gwanjung.Item.subItem.ICBM.WarheadFrame
import me.gwanjong.gwanjung.Item.subItem.PlayerHeadFrame
import me.gwanjong.gwanjung.Item.subItem.tntarrow
import me.gwanjong.gwanjung.Item.subItem.tntarrowFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun SubItemUI(player: Player) {

    val SubItemRecipeUI = InvFX.frame(1, Component.text("Bow")) {

        slot (0, 0) {
            item = tntarrow()
            onClick { clickEvent ->
                tntarrowFrame().openFrame(player)
            }
        }

        slot (1, 0) {
            item = ItemStack(Material.PLAYER_HEAD)
            onClick { clickEvent ->
                PlayerHeadFrame().openFrame(player)
            }
        }

        slot (2, 0) {
            val warhead = ItemStack(Material.TNT).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("${ChatColor.LIGHT_PURPLE}탄두"))
                }
            }

            item = warhead
            onClick { clickEvent ->
                WarheadFrame().openFrame(player)
            }
        }
        slot (3, 0) {
            val body = ItemStack(Material.IRON_BLOCK).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("${ChatColor.LIGHT_PURPLE}몸체"))
                }
            }

            item = body
            onClick { clickEvent ->
                BodyFrame().openFrame(player)
            }
        }
        slot (4, 0) {
            val engine = ItemStack(Material.POINTED_DRIPSTONE).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("${ChatColor.LIGHT_PURPLE}엔진"))
                }
            }

            item = engine
            onClick { clickEvent ->
                EngineFrame().openFrame(player)
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
    player.openFrame(SubItemRecipeUI)

}