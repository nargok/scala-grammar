class Dog(private val name: String) {
  def greet(): Unit = println(s"${name}だワン")
}

// object インスタンス化していることと同じ
object Taro extends Dog("タロウ")

// クラスと同じ名前のオブジェクトなので、コンパニオンオブジェクトと呼ぶ
object Dog {
  // ファクトリメソッド
  def apply(name: String) = new Dog(name)
  def apply(id: Int) = new Dog(s"番号付き犬${id}番")

  def printName(dog: Dog): Unit = println(dog.name)
}