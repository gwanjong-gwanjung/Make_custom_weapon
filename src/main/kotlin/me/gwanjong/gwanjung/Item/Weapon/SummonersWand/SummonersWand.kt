package me.gwanjong.gwanjung.Item.Weapon.SummonersWand

import me.gwanjong.gwanjung.tool.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


fun SummonersWand() : ItemStack{

    val Lore = ArrayList<Component>()
    val SummonersWand = MakeWeapon(ItemStack(Material.STICK), "소환사의 지팡이","마법 지팡이","좌클릭하면 삼지창이 날아간다","삼지창이 닿은 지점까지 가시를 소환한다", 0 ,Lore)
    Lore.add(Component.text("${ChatColor.BLUE}"))
    Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

    SummonersWand.lore(Lore)
    return SummonersWand
}
