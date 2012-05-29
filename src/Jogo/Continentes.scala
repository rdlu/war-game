/* Trabalho prático de Modelos de Linguagens de Programação: Jogo War
   Anderson Foscarini 180181
   Paula Burguêz 180663
   Ricardo Chagas Rapacki 180659
 */

package Jogo

object Continentes extends Enumeration {
  val AFRICA = new Continente("Africa")
  val NAMERICA = new Continente("América do Norte")
  val SAMERICA = new Continente("América do Sul")
  val ASIA = new Continente("Ásia")
  val EUROPA = new Continente("Europa")
  val OCEANIA = new Continente("Oceania")
}

object Territorios extends Enumeration {
  //America do Norte
  val ALASKA = new Territorio("Alaska",Continentes.NAMERICA)
  val ALBERTA = new Territorio("Alberta",Continentes.NAMERICA)
  val CAMERICA = new Territorio("America Central",Continentes.NAMERICA)
  val EASTUS = new Territorio("America Oriental",Continentes.NAMERICA)
  val GREENLAND = new Territorio("Groenlandia",Continentes.NAMERICA)
  val NWTERRITORY = new Territorio("Polar",Continentes.NAMERICA)
  val ONTARIO = new Territorio("Ontario",Continentes.NAMERICA)
  val QUEBEC  = new Territorio("Quebec",Continentes.NAMERICA)
  val WESTUS = new Territorio("America Ocidental",Continentes.NAMERICA)
  //America do Sul
  val ARGENTINA = new Territorio("Argentina",Continentes.SAMERICA)
  val BRASIL = new Territorio("Brasil",Continentes.SAMERICA)
  val PERU = new Territorio("Peru",Continentes.SAMERICA)
  val VENEZUELA = new Territorio("Venezuela",Continentes.SAMERICA)
  //EUropa
  val GBRITAIN = new Territorio("Grã Bretanha",Continentes.EUROPA)
  val ICELAND = new Territorio("Islandia",Continentes.EUROPA)
  val NEUROPE = new Territorio("Europa Setentrional",Continentes.EUROPA)
  val SCANDINAVIA  = new Territorio("Escandinavia",Continentes.EUROPA)
  val SEUROPE = new Territorio("Europa Meridional",Continentes.EUROPA)
  val UKRAINE = new Territorio("Ucrania",Continentes.EUROPA)
  val WEUROPE = new Territorio("Europa Ocidental",Continentes.EUROPA)
  //Africa
  val CONGO = new Territorio("Congo",Continentes.AFRICA)
  val EAFRICA = new Territorio("Africa Oriental",Continentes.AFRICA)
  val EGYPT = new Territorio("Egito",Continentes.AFRICA)
  val MADAGASCAR = new Territorio("Madagascar",Continentes.AFRICA)
  val NAFRICA = new Territorio("Africa Setentrional",Continentes.AFRICA)
  val SAFRICA  = new Territorio("Africa Meridional",Continentes.AFRICA)
  //Asia
  val AFEGANISTHAN = new Territorio("Afeganistao",Continentes.ASIA)
  val CHINA = new Territorio("China",Continentes.ASIA)
  val INDIA = new Territorio("India",Continentes.ASIA)
  val IRKUTSK = new Territorio("Irkutsk",Continentes.ASIA)
  val JAPAN = new Territorio("Japao",Continentes.ASIA)
  val KAMCHATKA = new Territorio("Kamchatka",Continentes.ASIA)
  val MIDDLEAST = new Territorio("Ira",Continentes.ASIA)
  val MONGOLIA = new Territorio("Mongolia",Continentes.ASIA)
  val SEASIA = new Territorio("Siam",Continentes.ASIA)
  val SIBERIA = new Territorio("Siberia",Continentes.ASIA)
  val URAL = new Territorio("Ural",Continentes.ASIA)
  val YAKUTSK = new Territorio("Yakutsk",Continentes.ASIA)
  //Oceania
  val EAUSTRALIA = new Territorio("Australia Oriental",Continentes.OCEANIA)
  val INDONESIA = new Territorio("Indonesia",Continentes.OCEANIA)
  val NEWGUINEA = new Territorio("Nova Guine",Continentes.OCEANIA)
  val WAUSTRALIA  = new Territorio("Australia Ocidental",Continentes.OCEANIA)
}
