package me.gwanjong.gwanjung.Weapon.Hyperion

import me.gwanjong.gwanjung.cultural_language
import me.gwanjong.gwanjung.tool.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


fun Hyperion(): ItemStack {

    if(cultural_language) {
        val Lore = ArrayList<Component>()
        val Hyperion = MakeWeapon(ItemStack(Material.IRON_SWORD), "폭발적 축지법 칼","한손칼","오른쪽 누름을 하면 8브로크 앞으로 축지법이 사용되고 터짐을 일으킨다","들고 있으면 수령님의 은혜를 받아 폭발데미지를 입지 않는다", 100,Lore)
        Lore.add(Component.text("수령님이 개발하신 위대한 무기"))
        Lore.add(Component.text("${ChatColor.BLUE}"))
        Lore.add(Component.text("${ChatColor.BLUE}무기 오락 필수 장구류"))

        Hyperion.lore(Lore)
        return Hyperion


    } else {
        val Lore = ArrayList<Component>()
        val Hyperion = MakeWeapon(ItemStack(Material.IRON_SWORD), "Hyperion","한손검","우클릭시 앞으로 8칸 이동하고 폭발을 일으킨다","들고 있으면 폭발데미지를 입지 않는다", 100,Lore)
        Lore.add(Component.text("하이픽셀 스카이블럭의 최종 무기 중 하나"))
        Lore.add(Component.text("${ChatColor.BLUE}"))
        Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

        Hyperion.lore(Lore)
        return Hyperion
    }


}