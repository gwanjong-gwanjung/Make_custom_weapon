package me.gwanjong.gwanjung.tool

import io.github.monun.invfx.InvFX
import io.github.monun.invfx.frame.InvFrame
import io.github.monun.invfx.openFrame
import me.gwanjong.gwanjung.UI.MainUI
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class MakeRecipeFrame {

    open fun MakeRecipeFrame() : InvFrame {
        val frame = InvFX.frame(6, Component.text("${setItem().itemMeta.displayName} ${ChatColor.BLACK}조합법")) {

            slot (7, 2) {
                item = setItem()

                onClick { clickEvent ->
                    val player = clickEvent.whoClicked as Player
                    if(player.gameMode != GameMode.CREATIVE) return@onClick
                    player.inventory.addItem(setItem())
                }

            }

            slot (1, 1) { item = recipe()[0]}
            slot (2, 1) { item = recipe()[1]}
            slot (3, 1) { item = recipe()[2]}

            slot (1, 2) { item = recipe()[3]}
            slot (2, 2) { item = recipe()[4]}
            slot (3, 2) { item = recipe()[5]}

            slot (1, 3) { item = recipe()[6]}
            slot (2, 3) { item = recipe()[7]}
            slot (3, 3) { item = recipe()[8]}

            slot (5,2) { item = ItemStack(Material.CRAFTING_TABLE)}

            slot (4,5) {
                val next = ItemStack(Material.ARROW).apply {
                    itemMeta = itemMeta.apply {
                        displayName(Component.text("이전 페이지로"))
                    }
                }

                item = next

                onClick { clickEvent ->
                    back(clickEvent.whoClicked as Player)
                }

            }

        }

        return frame
    }

    open fun openFrame(player: Player) {
        setItem()
        MakeRecipeFrame()
        player.openFrame(MakeRecipeFrame())
    }

    open fun back(player: Player) {
        MainUI(player)
    }

    open fun recipe(): Array<ItemStack> {
        val air = ItemStack(Material.AIR)

        return arrayOf(
            air, air, air,
            air, air, air,
            air, air, air
        )
    }

    open fun setItem() : ItemStack {
        val item = ItemStack(Material.BARRIER)
        return item
    }
}