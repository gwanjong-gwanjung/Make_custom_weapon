package me.gwanjong.gwanjung.Item.Weapon.ICBM

import me.gwanjong.gwanjung.tool.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


fun ICBM(): ItemStack {

    val Lore = ArrayList<Component>()
    val ICBM = MakeWeapon(ItemStack(Material.STICK), "ICBM","대륙간 탄도 미사일","지정한 플레이어에게 ICBM을 발사한다","플레이어가 어떤 위치에 있더라도 명중한다", 5000 ,Lore)
    Lore.add(Component.text("북쪽의 돼지 한마리가 좋아할것 같은 무기"))
    Lore.add(Component.text("${ChatColor.BLUE}"))
    Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

    ICBM.lore(Lore)
    return ICBM


}