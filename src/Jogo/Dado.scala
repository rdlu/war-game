/* Trabalho prático de Modelos de Linguagens de Programação: Jogo War
   Anderson Foscarini 180181
   Paula Burguêz 180663
   Ricardo Chagas Rapacki 180659
 */

package Jogo

class Dado() {
  
  // joga o dado, sorteando um número de 1 a 6
  def JogarDado () : Int = (1 + (Math.random * 6)).asInstanceOf[Int];
}
