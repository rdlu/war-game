package Jogo

import collection.mutable.ListBuffer
import Principal.JanelaMapa
import sun.misc.Sort
import util.Sorting

//Define as regras do jogo e executa as fases da jogada
class Jogo (var jogadores: ListBuffer[Jogador],val janela: JanelaMapa) {
  var fase = 0
  //nesse caso tive que forçar o tipo pois a inferencia detectou errado
  var jogadorAtual:Jogador = JogadorNeutro
  var jogadorCount = 0

  def inicializa:Jogo = {
    //randomiza a ordem de quem vai jogar http://stackoverflow.com/questions/11040399/scala-listbuffer-or-equivalent-shuffle/
    jogadores = scala.util.Random.shuffle(jogadores)
    //randomiza os paises a serem distribuidos aos jogadores
    val gruposPaises = scala.util.Random.shuffle(Territorios.values).grouped(qtdPaisesIniciais).toList
    //distribui a quantidade de tropas iniciais e paises
    for ((jogador,paises)<-jogadores.zip(gruposPaises)) yield {
      jogador.reforcos(qtdTropasIniciais)
      //faco uma matriz de agrupamentos de tropas e obtenho a matriz transposta para saber o numero de tropas por territ.
      val qtdPorTerritorio = (1 to qtdTropasIniciais).grouped(qtdPaisesIniciais).toList.transpose
      for ((territorio,qtd)<-paises.zip(qtdPorTerritorio)) yield {
        territorio.tropas(qtd.size,jogador)
      }
    }
    jogadorAtual = jogadores.apply(0)
    fase = 2
    janela.linhaFase.setForeground(jogadorAtual.getColor)
    janela.linhaFase.setText(jogadorAtual.getNome+" você já pode mover suas tropas ou atacar territórios")
    this
  }

  private def qtdTropasIniciais:Int = jogadores.length match {
    case 2 => 40
    case 3 => 35
    case 4 => 30
    case 5 => 25
    case 6 => 20
  }

  private def qtdPaisesIniciais:Int = jogadores.length match {
    case 2 => 15
    case 3 => 10
    case 4 => 10
    case 5 => Territorios.values.length / 5
    case 6 => Territorios.values.length / 6
  }

  private def proximoJogador:Jogador = {
    jogadorCount = jogadorCount+1 % jogadores.length
    jogadores.apply(jogadorCount)
  }

  // joga o dado, sorteando um número de 1 a 6
  private def JogarDado () : Int = (1 + (Math.random * 6)).asInstanceOf[Int];

  private def ataque(origem: Territorio, destino: Territorio, qtd: Int): (ListBuffer[Int], ListBuffer[Int], Boolean) = {
    if (origem.isVizinho(destino) && qtd > 0) {
      //default qtd de dados = qtd de exercitos
      var dadosAtacante: ListBuffer[Int] = ListBuffer.fill(qtd)(JogarDado())
      var dadosDefensor: ListBuffer[Int] = if (qtd > 1) ListBuffer.fill(qtd - 1)(JogarDado()) else ListBuffer.fill(1)(JogarDado())
      dadosAtacante = dadosAtacante.sortWith(_ < _)
      dadosDefensor = dadosDefensor.sortWith(_ < _)

      return (dadosAtacante, dadosDefensor,true)
    }
    (ListBuffer.empty[Int], ListBuffer.empty[Int], false)
  }

  private def mover(origem:Territorio,destino:Territorio,qtd:Int) {
    if (origem.isVizinho(destino) && qtd > 0 && origem.getDominador.equals(destino.getDominador)) {
      destino.tropas(qtd,origem)
    }
  }



}
