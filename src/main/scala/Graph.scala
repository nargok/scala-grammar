object Graph {

  // つながっている点の有無
  val matrix: Array[Array[Int]] = Array(
    Array(0, 1, 1, 0, 0),
    Array(1, 0, 0, 1, 0),
    Array(1, 0, 0, 1, 1),
    Array(0, 1, 1, 0, 1),
    Array(0, 0, 1, 1, 0),
  )

  // 隣接する点のリスト
  val list: Map[Char, Seq[Char]] = Map(
    'A' -> Seq('B', 'C'),
    'B' -> Seq('A', 'D'),
    'C' -> Seq('A', 'D', 'E'),
    'D' -> Seq('B', 'C', 'E'),
    'E' -> Seq('C', 'D'),
  )
}

// 隣接するリストの辺の情報 vis.jsと同じか
//case class Edge(from: Char, to: Char)
//
//val edges = Seq(
//  Edge('A', 'B'),
//  Edge('C', 'B'),
//  Edge('D', 'E'),
//)
