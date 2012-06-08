package Principal

import javax.swing._
import Jogo._
import collection.mutable.Map
import java.awt.event.{MouseEvent, MouseListener, ActionListener}
import java.awt.{Component, Font, Color}

class JanelaMapa extends JFrame {
  private val MenuArquivo: JMenu = new JMenu
  private val jMenuBar1: JMenuBar = new JMenuBar
  private val jMenuItem1: JMenuItem = new JMenuItem
  private var Painel: JMeuPainel = new JMeuPainel
  var Jogo: Jogo = new Jogo(Array(JogadorNeutro))

  //paineis de info do territtorio
  private val painelInfo: Array[JLabel] = Array(new JLabel("Territorio: "),new JLabel("Líder: "),new JLabel("Qtd Tropas: "))
  val painelOrigem: Array[Component] = Array(new JComboBox(),new JLabel("Origem: "), new JLabel("Tropas: 0"))
  val painelDestino: Array[Component] = Array(new JButton("Atacar!"),new JLabel("Destino: "),new JLabel("Líder: "),new JLabel("Tropas: "))
  //linha inferior para informações da fase atual
  val linhaFase = new JLabel("Jogo Risk WAR")
  //um map (chave->valor) com todos labels
  private var LabelsTerritorios: Map[Territorio,JLabel] = Map()


  def initialize(): Unit = {

    //inicializa os labels
    Territorios.values.foreach(inicializaLabel)

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Risk-War")
    getContentPane().setLayout(null)


    MenuArquivo.setText("Jogo")
    MenuArquivo.setMinimumSize(new java.awt.Dimension(40, 20))

    jMenuItem1.setText("Novo Jogo")
    MenuArquivo.add(jMenuItem1)

    jMenuBar1.add(MenuArquivo)

    jMenuItem1.addActionListener(new ActionListener() {
      override def actionPerformed(evt: java.awt.event.ActionEvent): Unit = {
        jMenuItem1
      }
    })
   // adiciona menus no JFrame
    setJMenuBar(jMenuBar1)
    pack()

    setSize(1010, 765 + 40)
    setLocation(50, 50)

    // adiciona painel com a imagem do mapa
    Painel = new JMeuPainel
    setContentPane(Painel)

    setLocationRelativeTo(null)
    setVisible(true)
    setLayout(null)


    //adiciona os labels
    for ((territorio,label)<-LabelsTerritorios) yield  {
      add(label)
    }

    inicializaPainelInfo
    inicializaPainelOrigem
    inicializaPainelDestino

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

       def mouseClicked(e: MouseEvent) {}
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
  }

  def resetPainelOrigem = {
    for ((linha,index)<-painelOrigem.zipWithIndex) yield {
      if (linha.isInstanceOf[JLabel]) {
        //linha.asInstanceOf[JLabel].setText("")
        linha.asInstanceOf[JLabel].setForeground(JogadorNeutro.getColor)
        linha.asInstanceOf[JLabel].setBounds(350,600+index*20,300,16)
      }

      if (linha.isInstanceOf[JComboBox]) {
        linha.asInstanceOf[JComboBox].removeAllItems()
        linha.asInstanceOf[JComboBox].addItem("0")
      }
    }
  }

  def atualizaPainelOrigem(t:Territorio) = {
    painelOrigem(0).asInstanceOf[JLabel].setText("Origem: "+t)
    painelOrigem(1).asInstanceOf[JLabel].setText("Tropas: "+t.getTropas)
    painelOrigem(0).setForeground(t.getDominador.getColor)
    painelOrigem(1).setForeground(t.getDominador.getColor)
  }

  private def inicializaPainelDestino = {
    resetPainelDestino
    for (linha<-painelDestino) add(linha)
  }

  def resetPainelDestino = {
    for ((linha,index)<-painelDestino.zipWithIndex) yield {
      if (linha.isInstanceOf[JLabel]) {
        //linha.asInstanceOf[JLabel].setText("")
        linha.asInstanceOf[JLabel].setForeground(JogadorNeutro.getColor)
        linha.asInstanceOf[JLabel].setBounds(700,600+index*20,300,16)
      }

      if (linha.isInstanceOf[JButton]) {
        linha.asInstanceOf[JButton].setEnabled(false)
      }
    }
  }


}
