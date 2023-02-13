package me.gwanjong.gwanjung

import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack

fun MakeWeapon(itemStack: ItemStack, weapon_Name: String, weapon_Type:String, base_attack_power:String, additional_abilities: String, lore: ArrayList<Component>) : ItemStack {
    val item = ItemStack(itemStack).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}${weapon_Name}"))
            lore.add(Component.text("${ChatColor.GRAY}${weapon_Type}"))
            lore.add(Component.text("${ChatColor.WHITE}기초 능력치: $base_attack_power"))
            lore.add(Component.text("${ChatColor.WHITE}추가 능력치: $additional_abilities"))
            lore(lore)
        }
    }
    return item

}
