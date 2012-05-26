package Jogo

// jogadores identificados como: jogador e adversário
class Jogo (){
  private var MapaJogador: Mapa = new Mapa
  private var MapaAdversario: Mapa = new Mapa  
  private var ObjetivoJogador: Int = 0
  private var ObjetivoAdversario: Int = 0
  
  // inicializa um novo jogo
  def NovoJogo() : Unit = {
    MapaJogador.InicializarMapa
    MapaAdversario.InicializarMapa    
  }
  
  // retorna o mapa do jogador
  def MapaJ () : Mapa = {
    MapaJogador
  }

  // retorna o mapa do adversário
  def MapaA () : Mapa = {
    MapaAdversario
  }    
  
  // retorna o índice do jogador
  def ObjJ () : Int = {
    ObjetivoJogador
  }
  
  // retorna o índice do jogador
  def ObjA () : Int = {
    ObjetivoAdversario
  }
  
  // verifica se o jogador atingiu o seu objetivo  
  def JogadorAtingiuObj () : Boolean = {
    MapaJogador.ObjetivoAtingido(LabelsObjetivos.apply(ObjetivoJogador))
  }
  
  // verifica se o adversário atingiu o seu objetivo
  def AdversarioAtingiuObj () : Boolean = {    
    MapaAdversario.ObjetivoAtingido(LabelsObjetivos.apply(ObjetivoAdversario))
  }  
  
  // sorteia um objetivo
  private def SorteiaObjetivo () : Int = 
    (Math.random * LabelsObjetivos.maxId).asInstanceOf[Int]
  
  // carrega os objetivos do jogador e adversário
  def CarregaObjetivos() : Unit = {
    ObjetivoJogador = SorteiaObjetivo
    ObjetivoAdversario = ObjetivoJogador
    
    while (ObjetivoAdversario == ObjetivoJogador)
      ObjetivoAdversario = SorteiaObjetivo
  }
  
  // verifica as possibilidades de ataque na África a partir de um continente e uma região
  def PossibilidadeAfrica (c: NomesContinentes.Value, r: Int) : Array[Int] = c match {
    case NomesContinentes.AFRICA => r match {
        case 0 => Array(1, 4, 5)
        case 1 => Array(0, 2, 3, 5)
        case 2 => Array(1, 4)
        case 3 => Array(1, 5)
        case 4 => Array(0, 1, 2)
        case 5 => Array(0, 1, 3)
        case _ => Array()
    }
    case NomesContinentes.AMERICASUL => r match {
        case 1 => Array(4)
        case _ => Array()
    }
    case NomesContinentes.ASIA => r match {
        case 6 => Array(1, 2)
        case _ => Array()          
    }      
    case NomesContinentes.EUROPA => r match {
        case 4 => Array(2, 4)        
        case 6 => Array(4)
        case _ => Array()
    }
    case _ => Array()
  }
  
  // verifica as possibilidades de ataque na América do Norte a partir de um continente e uma região
  def PossibilidadeAmNorte (c: NomesContinentes.Value, r: Int) : Array[Int] = c match {
    case NomesContinentes.AMERICANORTE => r match {
        case 0 => Array(1, 5)
        case 1 => Array(0, 5, 6, 8)
        case 2 => Array(3, 8)
        case 3 => Array(2, 6, 7, 8)
        case 4 => Array(5, 6, 7)
        case 5 => Array(0, 1, 4, 6)
        case 6 => Array(1, 3, 4, 5, 7, 8)
        case 7 => Array(3, 6)
        case 8 => Array(1, 2, 3, 6)
        case _ => Array()
    }
    case NomesContinentes.AMERICASUL => r match {
        case 3 => Array(2)
        case _ => Array()
    }
    case NomesContinentes.ASIA => r match {
        case 5 => Array(0)
        case _ => Array()
    }
    case NomesContinentes.EUROPA => r match {
        case 1 => Array(4)
        case _ => Array()
    }
    case _ => Array()
  }
  
  // verifica as possibilidades de ataque na América do Sul a partir de um continente e uma região
  def PossibilidadeAmSul (c: NomesContinentes.Value, r: Int) : Array[Int] = c match {
    case NomesContinentes.AMERICANORTE => r match {
        case 2 => Array(3)
        case _ => Array()
    }
    case NomesContinentes.AMERICASUL => r match {
        case 0 => Array(1, 2)
        case 1 => Array(0, 2, 3)
        case 2 => Array(0, 1, 3)
        case 3 => Array(1, 2)
        case _ => Array()
    }      
    case NomesContinentes.AFRICA => r match {
        case 4 => Array(1)
        case _ => Array()
    }
    case _ => Array()
  }  

  // verifica as possibilidades de ataque na Ásia a partir de um continente e uma região
  def PossibilidadeAsia (c: NomesContinentes.Value, r: Int) : Array[Int] = c match {
    case NomesContinentes.AFRICA => r match {
        case 1 => Array(6)
        case 2 => Array(6)
        case _ => Array()
    }
    case NomesContinentes.ASIA => r match {
        case 0 => Array(1, 2, 6, 10)
        case 1 => Array(0, 2, 7, 8)
        case 2 => Array(0, 1, 6, 8)
        case 3 => Array(4, 5, 7, 9, 11)
        case 4 => Array(5, 7)
        case 5 => Array(3, 4, 7, 11)
        case 6 => Array(0, 2)
        case 7 => Array(1, 3, 4, 5, 9)
        case 8 => Array(1, 2)
        case 9 => Array(1, 3, 7, 10, 11)
        case 10 => Array(0, 1, 9)
        case 11 => Array(3, 5, 9)
        case _ => Array()
    }
    case NomesContinentes.EUROPA => r match {
        case 4 => Array(6)
        case 5 => Array(0, 6, 10)
        case _ => Array()
    }
    case NomesContinentes.OCEANIA => r match {
        case 1 => Array(8)
        case _ => Array()
    }
    case _ => Array()
  }

  // verifica as possibilidades de ataque na Europa a partir de um continente e uma região
  def PossibilidadeEuropa (c: NomesContinentes.Value, r: Int) : Array[Int] = c match {
    case NomesContinentes.AFRICA => r match {
        case 2 => Array(4)
        case 4 => Array(4, 6)
        case _ => Array()
    }
    case NomesContinentes.AMERICANORTE => r match {
        case 4 => Array(1)
        case _ => Array()
    }
    case NomesContinentes.ASIA => r match {
        case 0 => Array(5)
        case 6 => Array(4, 5)
        case 10 => Array(5)
        case _ => Array()
    }
    case NomesContinentes.EUROPA => r match {
        case 0 => Array(1, 2, 3, 6)
        case 1 => Array(0, 3)
        case 2 => Array(0, 3, 4, 5, 6)
        case 3 => Array(0, 1, 2, 5)
        case 4 => Array(2, 5, 6)
        case 5 => Array(2, 3, 4)
        case 6 => Array(0, 2, 4)
        case _ => Array()
    }
    case _ => Array()
  }
  
  // verifica as possibilidades de ataque na Oceania a partir de um continente e uma região
  def PossibilidadeOceania (c: NomesContinentes.Value, r: Int) : Array[Int] = c match {
    case NomesContinentes.ASIA => r match {
        case 8 => Array(1)
        case _ => Array()
    }
    case NomesContinentes.OCEANIA => r match {
        case 0 => Array(2, 3)
        case 1 => Array(2, 3)
        case 2 => Array(0, 1, 3)
        case 3 => Array(0, 1, 2)
        case _ => Array()
    }
    case _ => Array()
  }
  
  // verifica as possibilidades de ataque em um determinado continente a partir de um continente e uma região
  def PossibilidadeAtaque (contJ: NomesContinentes.Value, regJ: Int, contA: NomesContinentes.Value) : Array[Int] = contA match {
    case NomesContinentes.AFRICA => PossibilidadeAfrica(contJ, regJ)
    case NomesContinentes.AMERICANORTE => PossibilidadeAmNorte(contJ, regJ)
    case NomesContinentes.AMERICASUL => PossibilidadeAmSul(contJ, regJ)
    case NomesContinentes.ASIA => PossibilidadeAsia(contJ, regJ)
    case NomesContinentes.EUROPA => PossibilidadeEuropa(contJ, regJ)
    case NomesContinentes.OCEANIA => PossibilidadeOceania(contJ, regJ)
    case _ => Array() 
  }
}
