package me.gwanjong.gwanjung.Item.Weapon.HyperMonsterBow

import me.gwanjong.gwanjung.tool.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


fun HyperMonsterBow(): ItemStack {

    val Lore = ArrayList<Component>()
    val HyperMonsterBow = MakeWeapon(ItemStack(Material.BOW), "HyperMonsterBow","활","좌클릭으로 발사할 수 있다","화살이 닿은곳엔 랜덤한 확률로 몬스터가 스폰한다", 0,Lore)
    Lore.add(Component.text(""))
    Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

    HyperMonsterBow.lore(Lore)
    return HyperMonsterBow
}