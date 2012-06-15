package Principal

import Jogo._

object Main {
  def main(args: Array[String]): Unit = {
    val j = new JanelaNovoJogo
    j.inicializa()
    j.setVisible(true)
  }
}


