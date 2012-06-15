package Principal

import javax.swing._
import Jogo._
import java.awt.event.{MouseEvent, MouseListener, ActionListener}
import java.awt.{Component, Font, Color}
import collection.mutable.{ListBuffer, Map}

class JanelaMapa(jogadores: ListBuffer[Jogador]) extends JFrame {
  private var Painel: JMeuPainel = new JMeuPainel
  var Jogo: Jogo = new Jogo(jogadores,this)

  //paineis de info do territtorio
  private val painelInfo: Array[JLabel] = Array(new JLabel("Territorio: "),new JLabel("Líder: "),new JLabel("Qtd Tropas: "))
  val painelOrigem: Array[JLabel] = Array(new JLabel("Origem: "), new JLabel("Tropas: 0"))
  val comboBox = new JComboBox()
  var origemSelecionada: Territorio = null
  var destinoSelecionado: Territorio = null
  val painelDestino: Array[JLabel] = Array(new JLabel("Destino: "),new JLabel("Líder: "),new JLabel("Tropas: "))
  val attackButton = new JButton("Atacar!")
  //linha inferior para informações da fase atual
  val linhaFase = new JLabel("Jogo Risk WAR")
  //um map (chave->valor) com todos labels
  private var LabelsTerritorios: Map[Territorio,JLabel] = Map()


  def initialize(): Unit = {
    //inicializa os labels
    Territorios.values.foreach(inicializaLabel)

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE)
    setTitle("Risk-War")
    getContentPane.setLayout(null)

    setSize(1010, 765 + 40)
    setLocation(50, 50)

    // adiciona painel com a imagem do mapa
    Painel = new JMeuPainel
    setContentPane(Painel)

    setLocationRelativeTo(null)
    this.setVisible(false)
    setLayout(null)


    //adiciona os labels
    for ((territorio,label)<-LabelsTerritorios) yield  {
      add(label)
    }

    inicializaPainelInfo
    inicializaPainelOrigem
    inicializaPainelDestino
    Jogo.inicializa
    atualizaLabelsTerritorios
  }


  private def inicializaLabel(territorio:Territorio) = {
    val label = new LabelTerritorio("0")
    label.setFont(new Font("Times New Roman",Font.BOLD,14))
    label.setForeground(JogadorNeutro.getColor)
    label.setBackground(Color.WHITE)
    label.territorio = territorio
    label.addMouseListener(new MouseListener {
       def mouseReleased(e: MouseEvent) {}

       def mouseEntered(e: MouseEvent) {
         val t = e.getComponent.asInstanceOf[LabelTerritorio].territorio
         atualizaPainelInfo(t)
       }

       def mouseExited(e: MouseEvent) {}

       def mousePressed(e: MouseEvent) {}

       def mouseClicked(e: MouseEvent) {
         val territorio = e.getComponent.asInstanceOf[LabelTerritorio].territorio
         atualizaPainel(territorio)
       }
     })
    label.setBounds(territorio.x+5,territorio.y-10,32,32)
    LabelsTerritorios += territorio -> label
  }

  private def inicializaPainelInfo = {
    for ((label,index)<-painelInfo.zipWithIndex) yield {
      label.setForeground(JogadorNeutro.getColor)
      label.setBounds(30,600+index*20,300,16)
      add(label)
    }

    linhaFase.setFont(new Font("Times New Roman",Font.BOLD,20))
    linhaFase.setBounds(0,this.getHeight-100,this.getWidth,24)
    linhaFase.setHorizontalAlignment(SwingConstants.CENTER)
    linhaFase.setForeground(Color.DARK_GRAY)

    add(linhaFase)
  }

  private def atualizaPainelInfo(t:Territorio) = {
    painelInfo(0).setText("Território: "+t)
    painelInfo(0).setForeground(t.getDominador.getColor)
    painelInfo(1).setText("Líder: "+t.getDominador)
    painelInfo(1).setForeground(t.getDominador.getColor)
    painelInfo(2).setText("Tropas: "+t.getTropas)
    painelInfo(2).setForeground(t.getDominador.getColor)
  }

  private def inicializaPainelOrigem = {
    resetPainelOrigem
    for (linha<-painelOrigem) add(linha)
    add(comboBox)
  }

  def resetPainelOrigem = {
    for ((linha,index)<-painelOrigem.zipWithIndex) yield {
        //linha.setText("")
        linha.setForeground(JogadorNeutro.getColor)
        linha.setBounds(350,600+index*20,300,16)
    }
    comboBox.removeAll()
    comboBox.addItem("0")
    comboBox.setBounds(350,580,100,16)
    origemSelecionada = null
  }

  def atualizaPainelOrigem(t:Territorio)  {
    painelOrigem(0).asInstanceOf[JLabel].setText("Origem: "+t)
    painelOrigem(1).asInstanceOf[JLabel].setText("Tropas: "+t.getTropas)
    painelOrigem(0).setForeground(t.getDominador.getColor)
    painelOrigem(1).setForeground(t.getDominador.getColor)

    comboBox.removeAllItems()
    for (i <- 1 until t.getTropas) {
      comboBox.addItem(i.toString)
    }
    origemSelecionada = t
    checaSelecoes()
  }

  private def inicializaPainelDestino = {
    resetPainelDestino
    for (linha<-painelDestino) add(linha)
    this.add(attackButton)
  }

  def resetPainelDestino  {
    for ((linha,index)<-painelDestino.zipWithIndex) yield {
        //linha.asInstanceOf[JLabel].setText("")
        linha.setForeground(JogadorNeutro.getColor)
        linha.setBounds(700,600+index*20,300,16)
    }
    attackButton.setBounds(700,580,200,16)
    attackButton.setEnabled(false)
    destinoSelecionado = null
  }

  def atualizaPainel(t:Territorio) = {
    if (Jogo.fase == 2 && Jogo.jogadorAtual.equals(t.getDominador)) {
      atualizaPainelOrigem(t)
    } else if (Jogo.fase == 2 && !Jogo.jogadorAtual.equals(t.getDominador)) {
      atualizaPainelDestino(t)
    }
  }


  def atualizaPainelDestino(t: Territorio) {
    painelDestino(0).setText("Destino: "+t)
    painelDestino(1).setText("Lider: "+t.getDominador)
    painelDestino(2).setText("Tropas: "+t.getTropas)
    painelDestino(0).setForeground(t.getDominador.getColor)
    painelDestino(1).setForeground(t.getDominador.getColor)
    painelDestino(2).setForeground(t.getDominador.getColor)

    destinoSelecionado = t
    checaSelecoes()
  }

  def checaSelecoes() {
    if (origemSelecionada != null && destinoSelecionado != null && origemSelecionada.isVizinho(destinoSelecionado)) {
      if (origemSelecionada.getDominador.equals(destinoSelecionado.getDominador)) attackButton.setText("Mover")
      else attackButton.setText("Atacar!")
      attackButton.setEnabled(true)
    }
  }



  def atualizaLabelsTerritorios {
    LabelsTerritorios.map {
      case (territorio,label) => {
        label.setForeground(territorio.getDominador.getColor)
        label.setText(territorio.getTropas.toString)
      }
    }

  }
}
