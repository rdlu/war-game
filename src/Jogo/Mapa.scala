/* Trabalho prático de Modelos de Linguagens de Programação: Jogo War
   Anderson Foscarini 180181
   Paula Burguêz 180663
   Ricardo Chagas Rapacki 180659
 */

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
    
    if (Continentes(ECont.EUROPA.id).FoiConquistado){
      if (Continentes(ECont.OCEANIA.id).FoiConquistado){
        if (Continentes(ECont.AFRICA.id).FoiConquistado) atingido = true
        if (Continentes(ECont.AMERICANORTE.id).FoiConquistado) atingido = true
        if (Continentes(ECont.AMERICASUL.id).FoiConquistado) atingido = true
        if (Continentes(ECont.ASIA.id).FoiConquistado) atingido = true        
      }      
    }
   
    atingido    
  }
  
  // Objetivo: América do Sul e Ásia
  private def ObjetivoAmsulAsia () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(ECont.ASIA.id).FoiConquistado)
      if (Continentes(ECont.AMERICASUL.id).FoiConquistado)
        atingido = true   
    
    atingido
  }
  
  // Objetivo: América do Sul, Europa e outro
  private def ObjetivoAmsulEurOutro () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(ECont.AMERICASUL.id).FoiConquistado){
      if (Continentes(ECont.EUROPA.id).FoiConquistado){
        if (Continentes(ECont.AFRICA.id).FoiConquistado) atingido = true
        if (Continentes(ECont.AMERICANORTE.id).FoiConquistado) atingido = true
        if (Continentes(ECont.ASIA.id).FoiConquistado) atingido = true
        if (Continentes(ECont.OCEANIA.id).FoiConquistado) atingido = true
      }
    }
    
    atingido
  }
  
  // Objetivo: 18 territórios com no mínimo 2 exércitos em cada
  private def Objetivo18territorios () : Boolean = TerritoriosConquistados(18, 2)
  
  private def ObjetivoAfricaAsia () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(ECont.AFRICA.id).FoiConquistado)
      if (Continentes(ECont.ASIA.id).FoiConquistado) atingido = true
    
    atingido
  }  
  
  // Objetivo: África e América do Norte
  private def ObjetivoAfricaAmnorte () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(ECont.AFRICA.id).FoiConquistado)
      if (Continentes(ECont.AMERICANORTE.id).FoiConquistado) atingido = true    
    
    atingido
  }    
  
  // Objetivo: 24 territórios
  private def Objetivo24territorios () : Boolean = TerritoriosConquistados(24, 1)

  // Objetivo: América do Norte e Oceania
  private def ObjetivoAmnorteOceania () : Boolean = {
    var atingido: Boolean = false
    
    if (Continentes(ECont.AMERICANORTE.id).FoiConquistado)
      if (Continentes(ECont.OCEANIA.id).FoiConquistado) atingido = true    
    
    atingido
  }   
  
  // verifica se um determinado objetivo foi atingido
  def ObjetivoAtingido (Objetivo: EObj.Value) : Boolean = Objetivo match {
    case EObj.EUR_OCE_OUTRO => ObjetivoEurOceanOutro 
    case EObj.AMESUL_ASIA => ObjetivoAmsulAsia 
    case EObj.AMESUL_EUR_OUTRO => ObjetivoAmsulEurOutro
    case EObj.TERRITORIOS18 => Objetivo18territorios 
    case EObj.AFR_ASIA => ObjetivoAfricaAsia 
    case EObj.AFR_AMENORTE => ObjetivoAfricaAmnorte 
    case EObj.TERRITORIOS24 => Objetivo24territorios
    case EObj.AMENORTE_OCE => ObjetivoAmnorteOceania
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