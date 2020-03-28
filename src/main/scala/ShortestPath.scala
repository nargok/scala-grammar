case class Edge(from: Char, to: Char, distance: Int)

object ShortestPath {
  /**
   * 頂点
   */
  val vertexes = 'A' to 'G'

  /**
   * 変
   */
  val edges = Seq(
    Edge('A', 'B', 1),
    Edge('A', 'C', 8),
    Edge('B', 'C', 6),
    Edge('B', 'D', 6),
    Edge('B', 'E', 6),
    Edge('C', 'D', 7),
    Edge('D', 'D', 2),
    Edge('E', 'F', 6),
    Edge('E', 'G', 8),
    Edge('F', 'G', 5),
  )

  def solveByBellmanFord(start: Char, goal: Char): Unit = {
    // 各頂点までの距離の初期化
    var distances = vertexes.map(v => (v -> Int.MaxValue)).toMap
    distances = distances + (start -> 0) // start地点のdistanceを0にする

    var isUpdated = true
    while (isUpdated) {
      isUpdated = false
      edges.foreach { e =>
        if (distances(e.from) != Int.MaxValue
          && distances(e.to) > distances(e.from) + e.distance) {
          distances = distances + (e.to -> (distances(e.from) + e.distance))
          isUpdated = true
        }
      }
    }

    println(distances) // start地点からの各ポイントまでの距離をprint
    println(distances(goal)) // start地点からgoal地点までの距離をprint
  }
}
