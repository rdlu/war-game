package Jogo

object Dado {
  // joga o dado, sorteando um número de 1 a 6
  def JogarDado () : Int = (1 + (Math.random * 6)).asInstanceOf[Int];
}
