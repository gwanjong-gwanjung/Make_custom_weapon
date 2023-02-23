package me.gwanjong.gwanjung.Item.Weapon.Singijeon

import me.gwanjong.gwanjung.cultural_language
import me.gwanjong.gwanjung.tool.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


fun Singijeon() : ItemStack{

    if(cultural_language) {

        val Lore = ArrayList<Component>()
        val Singijeon = MakeWeapon(ItemStack(Material.BOW), "신기전","신기전","화살을 쏘면 100개의 로케트가 쏘아진다","왼쪽 누름을 하면 수령님이 화살을 삭제해주신다", 200 ,Lore)
        Lore.add(Component.text("${ChatColor.BLUE}"))
        Lore.add(Component.text("${ChatColor.BLUE}무기 오락 필수 장구류"))

        Singijeon.lore(Lore)
        return Singijeon

    } else {

        val Lore = ArrayList<Component>()
        val Singijeon = MakeWeapon(ItemStack(Material.BOW), "신기전","신기전","화살을 발사하면 100개의 화살이 발사된다","좌클릭을 하면 화살이 삭제된다", 200 ,Lore)
        Lore.add(Component.text("${ChatColor.BLUE}"))
        Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

        Singijeon.lore(Lore)
        return Singijeon

    }
}
