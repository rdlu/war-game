/* Trabalho prático de Modelos de Linguagens de Programação: Jogo War
   Anderson Foscarini 180181
   Paula Burguêz 180663
   Ricardo Chagas Rapacki 180659
 */

package Jogo

class Continente (Quantidade: Int) {
  private var Territorios: Array[Int] = new Array(Quantidade)
  
  // retorna a lista de territórios
  def ListaTerritorios () : Array[Int] = {
    Territorios
  }
  
  // armazena a quantidade de exércitos em uma determinada região
  def PosicionarExercitos(r: Int, e: Int) : Unit = {
    Territorios.update(r, e)
  }
  
  // adiciona uma quantidade de exércitos em uma determinada região
  def AcrescentarExercitos(r: Int, e: Int) : Unit = {
    Territorios.update(r, Territorios.apply(r) + e)
  }
      
  // retira uma quantidade de exércitos em uma determinada região
  def RetirarExercitos(r: Int, e: Int) : Boolean = {    
    if (Territorios(r) >= e){
      Territorios.update(r, Territorios(r) - e)
      true
    }
    else false
  }
  
  // inicializa o continente com nenhum exército
  def InicializaContinente () : Unit = {
    for (t<-0 to Quantidade - 1 by 1){      
      Territorios.update(t, 0)
    }
  }
  
  // verifica se o continente está conquistado
  def FoiConquistado () : Boolean = {   
    var conquistado: Boolean = true
    
    for (t<-0 to (Quantidade - 1) by 1)
      if (Territorios.apply(t) == 0)
        conquistado = false        
    
    conquistado
  }  
  
  // retorna a quantidade de territórios conquistados com um mínimo de exércitos
  def QtdeTerritorios (MinExercitos: Int) : Int = {
    var quantidade = 0
    
    for (t<-0 to Quantidade - 1 by 1)
      if (Territorios.apply(t) >= MinExercitos)
        quantidade = quantidade + 1
    
    quantidade
  }
  
  // retorna a quantidade de exércitos de uma determinada região
  def QtdeExercitos (r: Int) : Int = {
    Territorios.apply(r)
  }
}