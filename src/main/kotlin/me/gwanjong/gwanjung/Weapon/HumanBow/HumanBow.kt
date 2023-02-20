package me.gwanjong.gwanjung.Weapon.HumanBow

import me.gwanjong.gwanjung.cultural_language
import me.gwanjong.gwanjung.tool.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


fun HumanBow(): ItemStack {

    if(cultural_language) {
        val Lore = ArrayList<Component>()
        val HumanBow = MakeWeapon(ItemStack(Material.BOW), "동무 미사일을 쏘는 무기","로케트","이 로케트를 혁명적이게 사용하면 탄두가 발사된 위치에 본인이 축지법을 써 이동한다","능력 사용 후 수령님의 은혜 시간 3초가 주어진다", 10000,Lore)
        Lore.add(Component.text("${ChatColor.BLUE}"))
        Lore.add(Component.text("${ChatColor.BLUE}무기 오락 필수 장구류"))
        HumanBow.lore(Lore)
        return HumanBow

    } else {
        val Lore = ArrayList<Component>()
        val HumanBow = MakeWeapon(ItemStack(Material.BOW), "인간활","활","이 활로 화살을 발사하면 화살이 발사된 위치에 자신이 텔레포트된다","능력 사용 후 무적타임 3초가 주어진다", 10000,Lore)
        Lore.add(Component.text("${ChatColor.BLUE}"))
        Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))
        HumanBow.lore(Lore)
        return HumanBow
    }
}



