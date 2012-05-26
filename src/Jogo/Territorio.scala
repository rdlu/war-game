package Jogo

import scala.collection.mutable.Set

//Exception diferenciada
case class TerritorioException(s:String) extends Exception(s)

/*
* Territorio: classe que controla TUDO que acontece dentro de um pais
* */
//val cria automaticamente um getter (variavel somente leitura)
class Territorio(val nome : String, val continente: Continente) {
  private var tropas : Int = 0
  //0 eh pais neutro
  private var dominador: Jogador = JogadorNeutro
  private var vizinhos: Set[Territorio] = Set()

  override def toString(): String = nome + " (" + continente.toString + ")"

  def getNome: String = nome

  //adicionar tropas do crescimento vegetativo
  def tropas(qtd:Int, jogador: Jogador): Territorio = {
    if (qtd > 0) {
      //dominador deve ser setado antes, devido a validacao
      setDominador(jogador)
      tropas += qtd
    }
    else throw new TerritorioException("Tentativa de adicionar tropas negativas")
    this
  }

  //mover tropas de outro territorio
  def tropas(qtd: Int, t:Territorio): Territorio = {
    if (!isVizinho(t)) throw new TerritorioException("Nao se pode mover tropas de paises nao vizinhos")
    else tropas(t.removeTropas(qtd),t.getDominador)
    this
  }

  private def setDominador(jogador: Jogador) = {
    if (jogador.equals(dominador) ||(dominador.equals(JogadorNeutro) && tropas == 0)) dominador = jogador
    else throw new TerritorioException("Territorio jah dominado, remova as tropas inimigas antes")
  }

  def isDominador(jogador:Jogador):Boolean = dominador.equals(jogador)

  def removeTropas(qtd:Int): Int = {
    if (qtd>tropas) throw new TerritorioException("Tentativa de remover mais tropas do que as possiveis")
    else tropas -= qtd
    if (tropas==0) dominador = JogadorNeutro
    qtd
  }

  def possibilidadeMovimentacao: Int = tropas - 1

  def possbilidadeAtaque: Int = if(tropas > 3) 3 else tropas-1

  def getTropas: Int = tropas

  def getDominador: Jogador  = dominador

  def getVizinhos: Set[Territorio] = vizinhos

  def setVizinhos(set: Set[Territorio]): Territorio = {
    vizinhos = set
    this
  }

  def pushVizinho(t:Territorio): Territorio = {
    vizinhos += t
    this
  }

  def isVizinho(t:Territorio): Boolean = vizinhos.contains(t)
}
