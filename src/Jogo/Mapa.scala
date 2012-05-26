package Jogo

class Mapa {      
  private var Continentes: Array[Continente] = Array((new Continente(6)), (new Continente(9)), (new Continente(4)), (new Continente(12)), (new Continente(7)), (new Continente(4)))

  // inicializa o mapa
  def InicializarMapa() : Unit = {
      Continentes = Array((new Continente(6)), (new Continente(9)), (new Continente(4)), (new Continente(12)), (new Continente(7)), (new Continente(4)))        
      for (c<-Continentes)
        c.InicializaContinente
  }
  
  // retorna a lista de continentes
  def ListaContinentes() : Array[Continente] = {
    Continentes
  }
  
  // verifica se um número de territórios foi conquistado com um mínimo de exércitos
  private def TerritoriosConquistados (MinTerrit: Int, MinExercitos: Int) : Boolean = {
    var quantidade = 0
    var atingido: Boolean = false
    
    for (c<-0 to Continentes.size - 1 by 1){
      quantidade = quantidade + Continentes.apply(c).QtdeTerritorios(MinExercitos)
      if (quantidade >= MinTerrit) atingido = true
    }
    
    atingido
  }
  
  // verifica se os objetivos foram atingidos
  
  // Objetivo: Europa, Oceania e outro
  private def ObjetivoEurOceanOutro () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(NomesContinentes.EUROPA.id).FoiConquistado){
      if (Continentes(NomesContinentes.OCEANIA.id).FoiConquistado){
        if (Continentes(NomesContinentes.AFRICA.id).FoiConquistado) atingido = true
        if (Continentes(NomesContinentes.AMERICANORTE.id).FoiConquistado) atingido = true
        if (Continentes(NomesContinentes.AMERICASUL.id).FoiConquistado) atingido = true
        if (Continentes(NomesContinentes.ASIA.id).FoiConquistado) atingido = true
      }      
    }
   
    atingido    
  }
  
  // Objetivo: América do Sul e Ásia
  private def ObjetivoAmsulAsia () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(NomesContinentes.ASIA.id).FoiConquistado)
      if (Continentes(NomesContinentes.AMERICASUL.id).FoiConquistado)
        atingido = true   
    
    atingido
  }
  
  // Objetivo: América do Sul, Europa e outro
  private def ObjetivoAmsulEurOutro () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(NomesContinentes.AMERICASUL.id).FoiConquistado){
      if (Continentes(NomesContinentes.EUROPA.id).FoiConquistado){
        if (Continentes(NomesContinentes.AFRICA.id).FoiConquistado) atingido = true
        if (Continentes(NomesContinentes.AMERICANORTE.id).FoiConquistado) atingido = true
        if (Continentes(NomesContinentes.ASIA.id).FoiConquistado) atingido = true
        if (Continentes(NomesContinentes.OCEANIA.id).FoiConquistado) atingido = true
      }
    }
    
    atingido
  }
  
  // Objetivo: 18 territórios com no mínimo 2 exércitos em cada
  private def Objetivo18territorios () : Boolean = TerritoriosConquistados(18, 2)
  
  private def ObjetivoAfricaAsia () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(NomesContinentes.AFRICA.id).FoiConquistado)
      if (Continentes(NomesContinentes.ASIA.id).FoiConquistado) atingido = true
    
    atingido
  }  
  
  // Objetivo: África e América do Norte
  private def ObjetivoAfricaAmnorte () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(NomesContinentes.AFRICA.id).FoiConquistado)
      if (Continentes(NomesContinentes.AMERICANORTE.id).FoiConquistado) atingido = true
    
    atingido
  }    
  
  // Objetivo: 24 territórios
  private def Objetivo24territorios () : Boolean = TerritoriosConquistados(24, 1)

  // Objetivo: América do Norte e Oceania
  private def ObjetivoAmnorteOceania () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(NomesContinentes.AMERICANORTE.id).FoiConquistado)
      if (Continentes(NomesContinentes.OCEANIA.id).FoiConquistado) atingido = true
    
    atingido
  }   
  
  // verifica se um determinado objetivo foi atingido
  def ObjetivoAtingido (Objetivo: LabelsObjetivos.Value) : Boolean = Objetivo match {
    case LabelsObjetivos.EUR_OCE_OUTRO => ObjetivoEurOceanOutro
    case LabelsObjetivos.AMESUL_ASIA => ObjetivoAmsulAsia
    case LabelsObjetivos.AMESUL_EUR_OUTRO => ObjetivoAmsulEurOutro
    case LabelsObjetivos.TERRITORIOS18 => Objetivo18territorios
    case LabelsObjetivos.AFR_ASIA => ObjetivoAfricaAsia
    case LabelsObjetivos.AFR_AMENORTE => ObjetivoAfricaAmnorte
    case LabelsObjetivos.TERRITORIOS24 => Objetivo24territorios
    case LabelsObjetivos.AMENORTE_OCE => ObjetivoAmnorteOceania
    case _ => false
  }  
  
  // adiciona exércitos em determinado território de determinado continente
  def AcrescentarExercitos(c: Int, r: Int, e: Int) : Unit = {
    Continentes.apply(c).AcrescentarExercitos(r, e)
  }
  
  // posiciona exércitos em determinado território de determinado continente
  def PosicionarExercitos(c: Int, r: Int, e: Int) : Unit = {
    Continentes.apply(c).PosicionarExercitos(r, e)
  }
  
  // retira exércitos em determinado território de determinado continente
  def RetirarExercitos(c: Int, r: Int, e: Int) : Unit = {
    Continentes.apply(c).RetirarExercitos(r, e)
  }
  
  // retorna a quantidade de exércitos em determinado território de determinado continente
  def QtdeExercitos(c: Int, r: Int) : Int = {
    Continentes.apply(c).QtdeExercitos(r)
  }
}