package me.gwanjong.gwanjung.Enchant

import io.github.monun.invfx.InvFX
import io.github.monun.invfx.openFrame
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class EnchantUI {
    private lateinit var viewer : Player

    fun open(player: Player) {
        require(!this::viewer.isInitialized)
        viewer = player

        InvFX.frame(3, Component.text("마법 부여대")) {

            onClickBottom {event->
                event.currentItem?.let {

                }
            }

            slot(4,2) {
                item = ItemStack(Material.ENCHANTING_TABLE)

                onClick {
                    player.sendMessage("Test")
                }
            }

        }.let { frame ->
            player.openFrame(frame)
        }
    }

}