package Principal

import java.awt.Color
import scala.Array
import javax.swing.{JButton, JTextField, JLabel, JFrame}
import java.awt.event.{MouseEvent, MouseListener, InputMethodEvent}
import Jogo.Jogador
import collection.mutable.ListBuffer

class JanelaNovoJogo extends JFrame {
  val botaoOK = new JButton("OK")
  //!!! essa foi ninja, cria array de 6 elementos jah inicializados
  val inputs: Array[JTextField] = Array.fill[JTextField](6)(new JTextField(""))
  val cores: Array[Color] = Array(Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.PINK,Color.ORANGE)
  val instance = this

  def inicializa() = {
    setTitle("Risk-War: Informe o nome dos jogadores")
    getContentPane.setLayout(null)

    cores.zipWithIndex map {
      case (color, index) => {
        val label = new JLabel("Jogador " + (1 + index) + ": ")
        label.setBounds(20, 20 + (20 * index), 80, 16)
        label.setForeground(color)
        add(label)

      }
    }
    //equivalente a for((input,index)<-inputs.zipWithIndex
    inputs.zipWithIndex.map {
      case(input,index) => {
        input.setBounds(120, 20 + (20 * index), 100, 16)
        add(input)
        inputs :+ input
      }
    }

    botaoOK.setBounds(120,200,100,30)
    botaoOK.addMouseListener(new MouseListener {
      def mouseReleased(e: MouseEvent) {}

      def mouseEntered(e: MouseEvent) {}

      def mouseExited(e: MouseEvent) {}

      def mousePressed(e: MouseEvent) {}

      def mouseClicked(e: MouseEvent) {
        //tamanho de arrays em scala eh imutavel, listbuffer eh dinamico, mal acostumado com C#,Ruby,PHP,etc que sao sempre dinamicas
        //perdi maior tempo ateh descobrir isso, tava tentando adicionar a uma array
        val jogadores: ListBuffer[Jogador] = ListBuffer()
        val th = instance
        for ((label,cor)<-th.inputs zip th.cores) yield {
          val text = label.getText.trim
          if (!text.equals("")) {
            jogadores.append(new Jogador(text,cor))
          }
        }

        if(jogadores.size > 1) {
          val j: JanelaMapa = new JanelaMapa(jogadores)
          j.initialize()
          j.setVisible(true)
          th.setVisible(false)
        }
      }
    })
    add(botaoOK)
    setLocationRelativeTo(null)
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setSize(360,360)
    //popup.pack()
    setVisible(true)
    //popup.repaint()
  }

}
