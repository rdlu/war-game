package Jogo

import java.awt.Color

//Exception diferenciada
case class JogadorException(s:String) extends Exception(s)

class Jogador(nome: String, color: Color) {
  private var reforcos : Int = 0

  def getColor:Color = color
  def getNome:String = nome
  override def toString:String = nome

  def reforcos(qtd:Int):Jogador = {
    if (qtd+reforcos < 0) throw new JogadorException("Jogador nao pode possuir reforcos em negativo")
    this.reforcos += qtd
    this
  }

}

object JogadorNeutro extends Jogador("Jogador Neutro", Color.gray)