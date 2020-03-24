import java.util.Random

object RPG extends App {
  val random = new Random
  val monsterCount = 5
  val hero = new Hero(200, 30)
  var monsters = for (i <- 1 to monsterCount) yield new Monster(random.nextInt(120), random.nextInt(120), false)

  println(
    s"""あなたは冒険中の ${hero}であり、
       |${monsterCount}匹のモンスターが潜んでいる洞窟を抜けなければならない。
       |ルール
       |1を入力してEnterを押すと攻撃, それ以外を入力すると逃げる
       |一度でもダメージを受けるとモンスターの体力と攻撃力が判明する
       |---
       |未知のモンスターが現れた""".stripMargin)

  while (!monsters.isEmpty) {
    val monster = monsters.head
    val input = scala.io.StdIn.readLine("攻撃[1] or 逃走[0] > ")

    if (input == "1") {
      hero.attack(monster)
      println(s"あなたは${hero.attackDamage}のダメージを与え、${monster.attackDamage}のダメージを受けた")
    } else {
      if(hero.escape(monster)) {
        println("あなたは、モンスターから逃走に成功した。")
      } else {
        println(s"あなたは、モンスターから逃走に失敗し、${monster.attackDamage}のダメージを受けた。")
      }
    }

    if (!hero.isAlive) {
      println(
        """----
          |(ゲームオーバー): あなたは無残にも殺されてしまった。
          |""".stripMargin)
      System.exit(0)
    } else if (!monster.isAlive || monster.isAwayFromHero) {
      if(!monster.isAwayFromHero) {
        println("モンスターは倒れた。そしてあなたは、モンスターの武器を奪った。")
        if (monster.attackDamage > hero.attackDamage) hero.attackDamage = monster.attackDamage
      }
      monsters = monsters.tail
      println(s"残りのモンスターは${monsters.length}匹となった。")
      if (monsters.length > 0) {
        println(
          """----
            |新たな未知のモンスターがあらわれた
            |""".stripMargin
        )
      }
    }
  }
  println(
    s"""---
       |(ゲームクリア)あなたは困難を乗り越えた。新たな冒険に祝福を。
       |(結果) ${hero}""".stripMargin
  )
  System.exit(0)
}

abstract class Creature(var hitPoint: Int, var attackDamage: Int) {
  def isAlive(): Boolean = this.hitPoint > 0
}

class Hero(_hitPoint: Int, _attachDamage: Int) extends Creature(_hitPoint, _attachDamage) {
  def attack(monster: Monster): Unit = {
    monster.hitPoint = monster.hitPoint - this.attackDamage
    this.hitPoint = this.hitPoint - monster.attackDamage
  }

  def escape(monster: Monster): Boolean = {
    val isEscaped = RPG.random.nextInt(2) == 1
    if (!isEscaped) {
      this.hitPoint = this.hitPoint - monster.attackDamage
    } else {
      monster.isAwayFromHero = true
    }
    isEscaped
  }

  override def toString: String = s"Hero(体力:${hitPoint}, 攻撃力:${attackDamage})"
}


class Monster(_hitPoint: Int, _attackDamage: Int, var isAwayFromHero: Boolean)
  extends Creature(_hitPoint, _attackDamage){

  override def toString: String = s"Monster(体力:${hitPoint}, 攻撃力:${attackDamage}, ヒーローから離れている:${isAwayFromHero})"
}