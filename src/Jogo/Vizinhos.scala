package Jogo

import scala.collection.mutable.Set

object Vizinhos {
  def from(t: Territorio): Set[Territorio] = t match {
    //America do Norte
    case Territorios.ALASKA => Set(Territorios.ALBERTA, Territorios.NWTERRITORY, Territorios.KAMCHATKA)
    case Territorios.ALBERTA => Set(Territorios.ALASKA, Territorios.NWTERRITORY, Territorios.QUEBEC, Territorios.WESTUS)
    case Territorios.CAMERICA => Set(Territorios.EASTUS, Territorios.WESTUS, Territorios.VENEZUELA)
    case Territorios.EASTUS => Set(Territorios.CAMERICA, Territorios.ONTARIO, Territorios.QUEBEC, Territorios.WESTUS)
    case Territorios.GREENLAND => Set(Territorios.NWTERRITORY, Territorios.ONTARIO, Territorios.QUEBEC, Territorios.ICELAND)
    case Territorios.NWTERRITORY => Set(Territorios.ALASKA, Territorios.ALBERTA, Territorios.ICELAND, Territorios.ONTARIO)
    case Territorios.ONTARIO => Set(Territorios.ALBERTA, Territorios.EASTUS, Territorios.GREENLAND, Territorios.NWTERRITORY, Territorios.QUEBEC, Territorios.WESTUS)
    case Territorios.QUEBEC => Set(Territorios.EASTUS, Territorios.ONTARIO, Territorios.GREENLAND)
    case Territorios.WESTUS => Set(Territorios.ALBERTA, Territorios.CAMERICA, Territorios.EASTUS, Territorios.ONTARIO)
    //America do Sul
    case Territorios.ARGENTINA => Set(Territorios.BRASIL, Territorios.PERU)
    case Territorios.BRASIL => Set(Territorios.ARGENTINA, Territorios.PERU, Territorios.VENEZUELA, Territorios.NAFRICA)
    case Territorios.PERU => Set(Territorios.ARGENTINA,Territorios.BRASIL,Territorios.VENEZUELA)
    case Territorios.VENEZUELA => Set(Territorios.BRASIL,Territorios.PERU, Territorios.CAMERICA)
    //Africa
    //Caso nao identificado
    case _ => Set()
  }
}
