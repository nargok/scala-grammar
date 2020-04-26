trait Greeter {
  def greet(): Unit
}

trait Robot {
  // trait自身がミックスインされるインスタンスは、Greeterというトレイトでなくてはならない
  // 抽象traitを指定して、実装を後から追加する = 依存性の注入 (Dependency Injection)
  self: Greeter => // 自分型アノテーション

  def start(): Unit = greet()
  override final def toString = "Robot"

}

trait HelloGreeter extends Greeter {
  def greet(): Unit = println("Hello")
}