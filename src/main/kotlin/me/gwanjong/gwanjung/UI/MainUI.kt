package me.gwanjong.gwanjung.UI

import io.github.monun.invfx.InvFX
import io.github.monun.invfx.openFrame
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun MainUI(player:Player) {

    val MainUI = InvFX.frame(1, Component.text("MainUI")) {

        slot (0, 0) {
            val sword = ItemStack(Material.IRON_SWORD).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("${ChatColor.YELLOW}검 아이템"))
                }
            }

            item = sword
            onClick { clickEvent ->
                SwordUI(player)
            }
        }

        slot (1, 0) {
            val bow = ItemStack(Material.BOW).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("${ChatColor.YELLOW}활 아이템"))
                }
            }

            item = bow
            onClick { clickEvent ->
                BowUI(player)
            }
        }

        slot (2, 0) {
            val subItem = ItemStack(Material.NETHER_STAR).apply {
                itemMeta = itemMeta.apply {
                    displayName(Component.text("기타 아이템"))
                }
            }

            item = subItem

            onClick { clickEvent ->
                SubItemUI(player)
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
    player.openFrame(MainUI)

}