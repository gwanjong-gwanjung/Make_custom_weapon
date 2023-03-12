package me.gwanjong.gwanjung.tool

import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack

/**
 * 무기 아이템을 만들어서 ItemStack로 반환
 * @param itemStack 베이스 아이템
 * @param weapon_Name 아이템의 이름
 * @param weapon_Type 아이템의 종류(검,활,지팡이 등)
 * @param base_attack_power 무기의 기본 능력
 * @param additional_abilities 무기의 보조능력
 * @param cooldownTick 무기 쿨타임
 * @param lore val Lore = ArrayList<Component>()를 넣어 사용
 */
fun MakeWeapon(itemStack: ItemStack, weapon_Name: String, weapon_Type:String, base_attack_power:String, additional_abilities: String, cooldownTick: Int,lore: ArrayList<Component>) : ItemStack {
    val item = ItemStack(itemStack).apply {
        itemMeta = itemMeta.apply {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}${weapon_Name}"))
            lore.add(Component.text("${ChatColor.GRAY}${weapon_Type}"))
            lore.add(Component.text("${ChatColor.WHITE}기초 능력치: $base_attack_power"))
            lore.add(Component.text("${ChatColor.WHITE}추가 능력치: $additional_abilities"))
            lore.add(Component.text("${ChatColor.WHITE}재사용 대기시간: ${cooldownTick/20}초"))
            lore(lore)
        }
    }

    return item

}
