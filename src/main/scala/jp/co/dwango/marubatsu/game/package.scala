package jp.co.dwango.marubatsu

import jp.co.dwango.marubatsu.board.{CellState, Empty, Batsu => BatsuState, Maru => MaruState}

// package object パッケージ内での共通処理を実装する 処理自体をimportしないでつかえる
package object game {

  def toWinner(cellState: CellState): Winner = cellState match {
    case MaruState => Maru
    case BatsuState => Batsu
    case Empty => NoWinner
  }
}
