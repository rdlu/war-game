/* Trabalho prático de Modelos de Linguagens de Programação: Jogo War
   Anderson Foscarini 180181
   Paula Burguêz 180663
   Ricardo Chagas Rapacki 180659
 */

package Principal

import Jogo._

object Main {
  def main(args: Array[String]): Unit = {   
    var j: JanelaMapa = new JanelaMapa()
    j.Inicializacao
    j.setVisible(true) 
  }
}


