package Jogo

import scala.collection.mutable.Set

class Continente (nome: String) {
  private val Territorios: Set[Territorio] = Set()
  
  // retorna a lista de territórios
  def getTerritorios: Set[Territorio] = Territorios

  override def toString() = nome

  //sobrecarrega << para adicionar o territorio ao continente
  def <<(t:Territorio) :Continente = {
    if (t.continente.equals(this)) Territorios += t
    this
  }
  
  // verifica se o continente foi conquistado
  def foiConquistado(jogador: Jogador) : Boolean = {
    if (Territorios.size == possuidos(jogador)) true
    else false
  }

  //numero de territorios que um determinado jogador possui
  def possuidos(jogador:Jogador):Int = Territorios.count(_.isDominador(jogador))
}