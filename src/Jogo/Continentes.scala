
package Jogo

object Continentes {
  val AFRICA = new Continente("Africa")
  val NAMERICA = new Continente("América do Norte")
  val SAMERICA = new Continente("América do Sul")
  val ASIA = new Continente("Ásia")
  val EUROPA = new Continente("Europa")
  val OCEANIA = new Continente("Oceania")
  //Enumeration do scala nao itera do jeito desejado, entao criei o values
  val values: Array[Continente] = Array(NAMERICA,SAMERICA,EUROPA,AFRICA,ASIA)
}

object Territorios {
  //America do Norte
  val ALASKA = new Territorio("Alaska",Continentes.NAMERICA,50,130)
  val ALBERTA = new Territorio("Alberta",Continentes.NAMERICA,160,170)
  val CAMERICA = new Territorio("America Central",Continentes.NAMERICA,213,284)
  val EASTUS = new Territorio("America Oriental",Continentes.NAMERICA,248,237)
  val GREENLAND = new Territorio("Groenlandia",Continentes.NAMERICA,367,76)
  val NWTERRITORY = new Territorio("Polar",Continentes.NAMERICA,147,124)
  val ONTARIO = new Territorio("Ontario",Continentes.NAMERICA,220,166)
  val QUEBEC  = new Territorio("Quebec",Continentes.NAMERICA,296,173)
  val WESTUS = new Territorio("America Ocidental",Continentes.NAMERICA,179,224)
  //America do Sul
  val ARGENTINA = new Territorio("Argentina",Continentes.SAMERICA,302,451)
  val BRASIL = new Territorio("Brasil",Continentes.SAMERICA,352,376)
  val PERU = new Territorio("Peru",Continentes.SAMERICA,293,391)
  val VENEZUELA = new Territorio("Venezuela",Continentes.SAMERICA,286,329)
  //EUropa
  val GBRITAIN = new Territorio("Grã Bretanha",Continentes.EUROPA,457,170)
  val ICELAND = new Territorio("Islandia",Continentes.EUROPA,465,115)
  val NEUROPE = new Territorio("Europa Setentrional",Continentes.EUROPA,531,185)
  val SCANDINAVIA  = new Territorio("Escandinavia",Continentes.EUROPA,537,124)
  val SEUROPE = new Territorio("Europa Meridional",Continentes.EUROPA,552,211)
  val UKRAINE = new Territorio("Ucrania",Continentes.EUROPA,602,157)
  val WEUROPE = new Territorio("Europa Ocidental",Continentes.EUROPA,474,221)
  //Africa
  val CONGO = new Territorio("Congo",Continentes.AFRICA,541,349)
  val EAFRICA = new Territorio("Africa Oriental",Continentes.AFRICA,588,308)
  val EGYPT = new Territorio("Egito",Continentes.AFRICA,550,260)
  val MADAGASCAR = new Territorio("Madagascar",Continentes.AFRICA,617,389)
  val NAFRICA = new Territorio("Africa Setentrional",Continentes.AFRICA,472,295)
  val SAFRICA  = new Territorio("Africa Meridional",Continentes.AFRICA,550,401)
  //Asia
  val AFEGANISTHAN = new Territorio("Afeganistao",Continentes.ASIA,663,214)
  val CHINA = new Territorio("China",Continentes.ASIA,751,239)
  val INDIA = new Territorio("India",Continentes.ASIA,695,274)
  val IRKUTSK = new Territorio("Irkutsk",Continentes.ASIA,792,171)
  val JAPAN = new Territorio("Japao",Continentes.ASIA,892,251)
  val KAMCHATKA = new Territorio("Kamchatka",Continentes.ASIA,916,128)
  val MIDDLEAST = new Territorio("Ira",Continentes.ASIA,603,249)
  val MONGOLIA = new Territorio("Mongolia",Continentes.ASIA,791,209)
  val SEASIA = new Territorio("Siam",Continentes.ASIA,758,293)
  val SIBERIA = new Territorio("Siberia",Continentes.ASIA,755,110)
  val URAL = new Territorio("Ural",Continentes.ASIA,691,161)
  val YAKUTSK = new Territorio("Yakutsk",Continentes.ASIA,845,111)
  //Oceania
  val EAUSTRALIA = new Territorio("Australia Oriental",Continentes.OCEANIA,912,433)
  val INDONESIA = new Territorio("Indonesia",Continentes.OCEANIA,799,369)
  val NEWGUINEA = new Territorio("Nova Guine",Continentes.OCEANIA,893,366)
  val WAUSTRALIA  = new Territorio("Australia Ocidental",Continentes.OCEANIA,835,440)

  val values: Array[Territorio] = Array(
    ALASKA,ALBERTA,CAMERICA,EASTUS,GREENLAND,NWTERRITORY,ONTARIO,QUEBEC,WESTUS,
    ARGENTINA,BRASIL,PERU,VENEZUELA,
    GBRITAIN,ICELAND,NEUROPE,SCANDINAVIA,SEUROPE,UKRAINE,WEUROPE,
    CONGO,EAFRICA,EGYPT,MADAGASCAR,NAFRICA,SAFRICA,
    AFEGANISTHAN,CHINA,INDIA,IRKUTSK,JAPAN,KAMCHATKA,MIDDLEAST,MONGOLIA,SEASIA,SIBERIA,URAL,YAKUTSK,
    EAUSTRALIA,INDONESIA,NEWGUINEA,WAUSTRALIA
  )
}
