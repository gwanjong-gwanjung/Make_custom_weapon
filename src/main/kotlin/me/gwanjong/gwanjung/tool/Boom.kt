package me.gwanjong.gwanjung.tool

import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity

fun Boom(location: Location, size: Int,x: Int, y:Int) {
    val boom = size.toFloat()
    val world = location.world

    for (i in 0..y) {
        val boom10 = world.spawnEntity(Location(location.world, location.x, location.y + y - i, location.z), EntityType.ARMOR_STAND)
        world.createExplosion(boom10.location, boom)
    }

    for (i in 0..x) {

        val boom1 = world.spawnEntity(Location(location.world, location.x + i, location.y , location.z), EntityType.ARMOR_STAND) as LivingEntity
        val boom2 = world.spawnEntity(Location(location.world, location.x + i, location.y , location.z + i), EntityType.ARMOR_STAND) as LivingEntity
        val boom3 = world.spawnEntity(Location(location.world, location.x + i, location.y , location.z - i), EntityType.ARMOR_STAND) as LivingEntity
        val boom4 = world.spawnEntity(Location(location.world, location.x, location.y , location.z), EntityType.ARMOR_STAND) as LivingEntity
        val boom5 = world.spawnEntity(Location(location.world, location.x - i, location.y , location.z), EntityType.ARMOR_STAND) as LivingEntity
        val boom6 = world.spawnEntity(Location(location.world, location.x - i, location.y , location.z + i), EntityType.ARMOR_STAND) as LivingEntity
        val boom7 = world.spawnEntity(Location(location.world, location.x - i, location.y , location.z - i), EntityType.ARMOR_STAND) as LivingEntity
        val boom8 = world.spawnEntity(Location(location.world, location.x, location.y , location.z - i), EntityType.ARMOR_STAND) as LivingEntity
        val boom9 = world.spawnEntity(Location(location.world, location.x, location.y , location.z + i), EntityType.ARMOR_STAND) as LivingEntity


        world.createExplosion(boom1.location, boom)
        world.createExplosion(boom2.location, boom)
        world.createExplosion(boom3.location, boom)
        world.createExplosion(boom4.location, boom)
        world.createExplosion(boom5.location, boom)
        world.createExplosion(boom6.location, boom)
        world.createExplosion(boom7.location, boom)
        world.createExplosion(boom8.location, boom)
        world.createExplosion(boom9.location, boom)
    }

}