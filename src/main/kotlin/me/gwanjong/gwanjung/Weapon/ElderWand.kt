package me.gwanjong.gwanjung.Weapon

import me.gwanjong.gwanjung.tool.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun ElderWand(): ItemStack {

    val Lore = ArrayList<Component>()
    val ElderWand = MakeWeapon(ItemStack(Material.STICK), "딱총나무 지팡이","마법 지팡이","좌클릭시 전방으로 미사일을 발사한다","우클릭시 전방으로 화염구를 발사한다", 60,Lore)
    Lore.add(Component.text("해리 포터 시리즈의 죽음의 성물 중 하나"))
    Lore.add(Component.text("지팡이의 재료는 딱총나무와 세스트럴의 꼬리털이라고 한다."))
    Lore.add(Component.text("다른 지팡이에 비하여 월등한 능력을 가진 것으로 묘사되며, 최강의 지팡이로 거론되는 죽음의 성물 중 하나다."))
    Lore.add(Component.text("${ChatColor.BLUE}"))
    Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

    ElderWand.lore(Lore)
    return ElderWand

}

