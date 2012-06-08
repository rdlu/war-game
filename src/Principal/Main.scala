package Principal

import Jogo._

object Main {
  def main(args: Array[String]): Unit = {
    val j: JanelaMapa = new JanelaMapa()
    j.initialize()
    j.setVisible(true) 
  }
}


