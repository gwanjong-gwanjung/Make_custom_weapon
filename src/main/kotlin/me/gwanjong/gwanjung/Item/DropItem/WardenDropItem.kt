package me.gwanjong.gwanjung.Item.DropItem

import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

fun WardenHeart() : ItemStack{
    val wardenheart = ItemStack(Material.NETHER_STAR).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}워든의 심장"))
            addEnchant(Enchantment.ARROW_DAMAGE,1, false)
            addItemFlags(ItemFlag.HIDE_ENCHANTS)
        }
    }



    return wardenheart
}

