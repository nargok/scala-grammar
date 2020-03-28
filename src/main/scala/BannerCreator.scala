//シングルトンオブジェクト
// いつでも呼び出したいメソッドを定義するときに便利
object BannerCreator {
  private val decor = ".｡:･・.｡:*･★.｡:･・.｡:*･★.｡:･・.｡:*･★.｡:･・.｡:*･★.｡:･・.｡:*･★"

  def create(message: String) = {
    s"""${decor}
       |${message}
       |${decor}""".stripMargin
  }
}

object MessageContainer {
  var message = ""
}
