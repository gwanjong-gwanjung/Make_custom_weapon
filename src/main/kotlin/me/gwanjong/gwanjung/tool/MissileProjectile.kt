package me.gwanjong.gwanjung.tool

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Fireball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.util.Vector


//나중에
class ArmorCradle : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK && event.hand == EquipmentSlot.HAND) {
            val block = event.clickedBlock ?: return
            if (block.type == Material.IRON_BLOCK) {
                spawnArmorCradle(block.location)
            }
        }
    }

    private fun spawnArmorCradle(location: Location) {
        val armorStand = location.world?.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand
        armorStand.isVisible = false
        armorStand.isSmall = true
        armorStand.isMarker = true
        armorStand.isInvulnerable = true

        val fireBall = location.world?.spawn(location, Fireball::class.java)
        fireBall?.velocity = Vector(0, 1, 0)
        fireBall?.direction = Vector(0, 1, 0)

        Bukkit.getPluginManager().getPlugin("me.gwanjong.gwanjung.tool.ArmorCradle")?.let {
            Bukkit.getScheduler().runTaskLater(it, Runnable { checkForCollision(fireBall) }, 20)
        }
    }

    private fun checkForCollision(fireBall: Fireball?) {
        val entity = fireBall ?: return
        val location = entity.location
        val blocks = arrayOf(
            location.clone().add(0.0, 0.0, 0.0).block,
            location.clone().add(1.0, 0.0, 0.0).block,
            location.clone().add(-1.0, 0.0, 0.0).block,
            location.clone().add(0.0, 0.0, 1.0).block,
            location.clone().add(0.0, 0.0, -1.0).block,
            location.clone().add(0.0, 1.0, 0.0).block,
            location.clone().add(0.0, -1.0, 0.0).block
        )

        for (block in blocks) {
            if (!block.isEmpty) {
                return
            }
        }
    }
}




