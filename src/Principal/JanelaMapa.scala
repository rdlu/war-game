/* Trabalho prático de Modelos de Linguagens de Programação: Jogo War
   Anderson Foscarini 180181
   Paula Burguêz 180663
   Ricardo Chagas Rapacki 180659
 */

package Principal

import java.awt.Font
import java.awt.Color
import java.awt.Component
import javax.swing._
import Jogo._
import java.awt.event.ActionListener;

class JanelaMapa extends JFrame {                        
    private var jMenu1: JMenu = new JMenu
    private var jMenu2: JMenu = new JMenu
    private var jMenuBar1: JMenuBar = new JMenuBar
    private var jMenuItem1: JMenuItem = new JMenuItem
    private var jMenuItem2: JMenuItem = new JMenuItem
    private var Painel: JMeuPainel = new JMeuPainel
    private var LContinentesJ: Array[Array[JLabel]] = Array(
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") )
    )

    private var LContinentesA: Array[Array[JLabel]] = Array(
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") ),
      Array( new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0") )
    )
    private var Continente, Regiao, Ataque, ExercitosPosicionar, ExercitosAtacar: JComboBox = new JComboBox
    private var botaoPosicionar, botaoPreparar, botaoFimJogada, botaoAtacar, botaoCancAtaque, btObjJ, btObjA: JButton = new JButton
    private var VezJ, VezA, ObjJ, ObjA, AvisoAtaque, ObjetivoA, ObjetivoJ, Vez: JLabel = new JLabel
    private var h: Int = 15
    private var w: Int = 20
    private var jogador: Boolean = true
    private var jaPosicionados: Boolean = false
    private var objetivosSorteados: Boolean = false
    private var objEscondidoJ: Boolean = false
    private var objEscondidoA: Boolean = false
    private var jogoAcabou: Boolean = false
    private var exercPosicionadosJ: Int = 0
    private var exercPosicionadosA: Int = 0
    private var Jogo: Jogo = new Jogo   
    private var ListaAtaque: List[(Int, Int)] = List()
       
    // inicializações necessárias para começar o jogo
    def Inicializacao() : Unit = {        
        initComponents
        
        // configurações do JFrame
        setSize(1075, 527 + 40 + 150)
        setLocation(50, 50)        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE)
        
        // adiciona painel com a imagem do mapa
        Painel = new JMeuPainel                      
        setContentPane(Painel)
        
        setLocationRelativeTo(null)
        setVisible(true)
        setLayout(null)
        
        NovoJogo           
        
        // inicializa interface
        InicializaTodasListas
        ImprimeLabels
        ConfiguraPainelCombos
        ConfiguraBotoes
        ConfiguraAvisos    
        EsconderAtaque
    }  
    
    // esconde os objetivos do jogador (1), do adversário (2) ou de ambos (0)
    private def EscondeObjetivos(n: Int) : Unit = {
      n match{
        case 0 => {
          ObjJ.setText("*************")
          ObjA.setText("*************")
        }
        case 1 => { 
            ObjJ.setText("*************")
        }
        case 2 => {
            ObjA.setText("*************")
        }
        case _ => {}
      }
      
      validate()
      repaint(1)
    }
    
    // configura os avisos do jogo
    private def ConfiguraAvisos(): Unit = {        
        // remove labels do JFrame    
        remove(ObjJ)    
        remove(ObjA)
        remove(VezJ)
        remove(VezA)        
        remove(Vez)
        remove(ObjetivoJ)
        remove(ObjetivoA)
        
        // configura labels
        VezJ = new JLabel("Jogador 1")
        VezA = new JLabel("Jogador 2")
        ObjJ = new JLabel("Sem")
        ObjA = new JLabel("Sem")
        AvisoAtaque = new JLabel("Selecione ataque:")
    
        Vez = new JLabel("É a vez do ")
        ObjetivoJ = new JLabel("Objetivo do Jogador 1: ")
        ObjetivoA = new JLabel("Objetivo do Jogador 2: ")
        
        var fonte: Font = new Font ("TimesRoman", Font.BOLD, 20)
                
        Vez.setFont(fonte)
        Vez.setForeground(Color.black)        
        VezJ.setFont(fonte)
        VezJ.setForeground(Color.red)        
        VezA.setFont(fonte)
        VezA.setForeground(Color.blue)        
            
        Vez.setBounds(470, 480, 100, 30)
        VezJ.setBounds(570, 480, 100, 30)
        VezA.setBounds(570, 480, 100, 30)                
        
        ConfiguraLabelJogador
        
        fonte = new Font ("TimesRoman", Font.BOLD, 15)        
        
        ObjetivoJ.setFont(fonte)
        ObjetivoJ.setForeground(Color.red)
        ObjetivoA.setFont(fonte)
        ObjetivoA.setForeground(Color.blue)
        AvisoAtaque.setFont(fonte)
        AvisoAtaque.setForeground(Color.black)
        
        ObjetivoJ.setBounds(180, 590, 200, 20)
        ObjetivoA.setBounds(180, 620, 200, 20)
        ObjJ.setBounds(380, 590, 200, 20)
        ObjA.setBounds(380, 620, 200, 20)
        AvisoAtaque.setBounds(830, 550, 200, 20)
        
        // adiciona labels no JFrame
        add(Vez)
        add(ObjetivoJ)
        add(ObjetivoA)        
        add(ObjJ)
        add(ObjA)
        add(AvisoAtaque)
    
        validate
        repaint(1)
    }
    
    // adiciona a label do jogador que deve jogar no momento
    private def ConfiguraLabelJogador() : Unit = {
      remove(VezJ)
      remove(VezA)
    
      if (jogador)                
        add(VezJ)
      else
        add(VezA)
    }
    
    // configura botões do jogo
    private def ConfiguraBotoes() : Unit = {
        // inicializa botões
        botaoPosicionar = new JButton("Posicionar exércitos")
        botaoPreparar = new JButton("Preparar para atacar")
        botaoAtacar = new JButton("Atacar!")
        botaoFimJogada = new JButton("Acabei!")
        botaoCancAtaque = new JButton("Acabei!")
        btObjJ = new JButton("Exibir/Esconder")
        btObjA = new JButton("Exibir/Esconder")
        
        // configura posição e evento de clique
        botaoPosicionar.setBounds(20 + 300, 550, 180, 20)
        botaoPosicionar.addActionListener(new ActionListener() {
            override def actionPerformed(e: java.awt.event.ActionEvent) {
                PosicionaExercitos
            }
        })              
       
        botaoPreparar.setBounds(20 + 590, 550, 180, 20)
        botaoPreparar.addActionListener(new ActionListener() {
            override def actionPerformed(e: java.awt.event.ActionEvent) {
                PrepararAtaque
            }
        })      
      
        botaoFimJogada.setBounds(670, 485, 80, 20)
        botaoFimJogada.addActionListener(new ActionListener() {
            override def actionPerformed(e: java.awt.event.ActionEvent) {
                FimJogada
            }
        })

        btObjJ.setBounds(20, 590, 150, 20)
        btObjJ.addActionListener(new ActionListener() {
            override def actionPerformed(e: java.awt.event.ActionEvent) {
                AjustarObjetivoJ
            }
        })
      
        btObjA.setBounds(20, 620, 150, 20)
        btObjA.addActionListener(new ActionListener() {
            override def actionPerformed(e: java.awt.event.ActionEvent) {
                AjustarObjetivoA
            }
        })    
      
        botaoAtacar.setBounds(830, 620, 90, 20)
        botaoAtacar.addActionListener(new ActionListener() {
            override def actionPerformed(e: java.awt.event.ActionEvent) {
                AtacarRegiao
            }
        })    
      
        botaoCancAtaque.setBounds(930, 620, 90, 20)
        botaoCancAtaque.addActionListener(new ActionListener() {
            override def actionPerformed(e: java.awt.event.ActionEvent) {
                EsconderAtaque
            }
        })    

        // adiciona botões no JFrame
        add(botaoPosicionar)
        add(botaoPreparar)
        add(botaoAtacar)
        add(botaoCancAtaque)
        add(botaoFimJogada)
        add(btObjJ)
        add(btObjA)        
       
        // desabilita botões porque o jogo não pode começar sem sortear os objetivos
        btObjJ.setEnabled(false)
        btObjA.setEnabled(false)
        botaoPosicionar.setEnabled(false)
        botaoPreparar.setEnabled(false)     
        botaoFimJogada.setEnabled(false)     
        
        validate
        repaint(1)
    }
    
    // configura combos do jogo
    private def ConfiguraPainelCombos() : Unit = {                               
        Ataque = new JComboBox
        Ataque.setBounds(830, 580, 200, 20)
        add(Ataque)
    
        Continente = new JComboBox
        Continente.addItemListener(new java.awt.event.ItemListener() {
            override def itemStateChanged(evt: java.awt.event.ItemEvent){
              if (evt.getStateChange == 1){
                PreencheComboRegioes
                Regiao.setSelectedIndex(0)      
              }
            }})          
        
        // inicializa combo dos continentes com os nomes da enumeração
        for (v<-ECont.values)
          Continente.addItem(v.toString)
        Continente.setSelectedIndex(0)
        Continente.setBounds(20, 550, 130, 20)
        add(Continente)              
        
        Regiao = new JComboBox
        Regiao.addItemListener(new java.awt.event.ItemListener() {
            override def itemStateChanged(evt: java.awt.event.ItemEvent){
              if (evt.getStateChange == 1){
                PreencheComboExercitosA              
                ExercitosAtacar.setSelectedIndex(0)
              }
            }})        
        PreencheComboRegioes        
        Regiao.setSelectedIndex(0)
        Regiao.setBounds(20 + 150, 550, 50, 20)       
        add(Regiao)
        
        ExercitosPosicionar = new JComboBox       
        PreencheComboExercitosP(10)       
        ExercitosPosicionar.setBounds(20 + 220, 550, 50, 20)       
        add(ExercitosPosicionar)

        ExercitosAtacar = new JComboBox       
        PreencheComboExercitosA
        ExercitosAtacar.setSelectedIndex(0)
        ExercitosAtacar.setBounds(20 + 510, 550, 50, 20)       
        add(ExercitosAtacar)                      
        
        validate
        repaint(1)
    }
    
    // preenche combo com o número de exércitos para atacar, de acordo com a posição do atacante
    private def PreencheComboExercitosA() : Unit = {                 
        ExercitosAtacar.removeAllItems
        ExercitosAtacar.addItem("0")  
        
        var cont: Int = Continente.getSelectedIndex
        var reg: Int = Regiao.getSelectedIndex     
        var n: Int = 0
        
        if (jogador) n = Jogo.MapaJ.QtdeExercitos(cont, reg)
        else n = Jogo.MapaA.QtdeExercitos(cont, reg)
        
        if (n > 0){
          if (n > 1)
            ExercitosAtacar.addItem("1")  
          if (n > 2)
            ExercitosAtacar.addItem("2")  
          if (n > 3)
            ExercitosAtacar.addItem("3")  
        }
    }
    
    // preenche combo com o número de exércitos para posicionar passado como parâmetro
    private def PreencheComboExercitosP(p: Int) : Unit = {                 
      ExercitosPosicionar.removeAllItems
                     
      for (i<-0 to p by 1)
          ExercitosPosicionar.addItem(i)     
      
      if (p > 0)
        ExercitosPosicionar.setSelectedIndex(1)
      else
        ExercitosPosicionar.setSelectedIndex(0)
    }
    
    // preenche combo com o número de regiões de acordo com o continente
    private def PreencheComboRegioes() : Unit = {                 
        var cont: Int = Continente.getSelectedIndex
        Regiao.removeAllItems        
        Regiao.addItem("1")
        Regiao.addItem("2")
        Regiao.addItem("3")
        Regiao.addItem("4")  
                
        cont match{
            case 0 => { // África (6)
                Regiao.addItem("5")
                Regiao.addItem("6") 
            }
            case 1 => { // América do Norte (9)
                Regiao.addItem("5")
                Regiao.addItem("6")
                Regiao.addItem("7")
                Regiao.addItem("8")
                Regiao.addItem("9")
            }
            case 3 => { // Ásia (12)
                Regiao.addItem("5")
                Regiao.addItem("6")
                Regiao.addItem("7")
                Regiao.addItem("8")
                Regiao.addItem("9")
                Regiao.addItem("10")
                Regiao.addItem("11")
                Regiao.addItem("12")
            }
            case 4 => { // Europa (7)
                Regiao.addItem("5")
                Regiao.addItem("6")
                Regiao.addItem("7")
            }
            case _ => {} // América do Sul e Oceania (4)
        }               
    }
   
    // configura posições e adiciona as labels no JFrame
    private def ImprimeLabels() : Unit = {       
        
        // Labels da África
        LContinentesJ.apply(0).apply(0).setBounds(550, 300, w, h)
        LContinentesA.apply(0).apply(0).setBounds(515, 300, w, h)        
        LContinentesJ.apply(0).apply(1).setBounds(560, 230, w, h)
        LContinentesA.apply(0).apply(1).setBounds(560, 250, w, h)        
        LContinentesJ.apply(0).apply(2).setBounds(555, 195, w, h)
        LContinentesA.apply(0).apply(2).setBounds(515, 195, w, h)        
        LContinentesJ.apply(0).apply(3).setBounds(630, 360, w, h)
        LContinentesA.apply(0).apply(3).setBounds(630, 380, w, h)        
        LContinentesJ.apply(0).apply(4).setBounds(480, 230, w, h)
        LContinentesA.apply(0).apply(4).setBounds(430, 230, w, h)        
        LContinentesJ.apply(0).apply(5).setBounds(540, 350, w, h)
        LContinentesA.apply(0).apply(5).setBounds(540, 390, w, h)       
        
        // Labels da América do Norte
        LContinentesJ.apply(1).apply(0).setBounds(30, 80, w, h)
        LContinentesA.apply(1).apply(0).setBounds(50, 60, w, h)        
        LContinentesJ.apply(1).apply(1).setBounds(110, 100, w, h)
        LContinentesA.apply(1).apply(1).setBounds(160, 100, w, h)       
        LContinentesJ.apply(1).apply(2).setBounds(110, 210, w, h)
        LContinentesA.apply(1).apply(2).setBounds(150, 220, w, h)        
        LContinentesJ.apply(1).apply(3).setBounds(160, 175, w, h)
        LContinentesA.apply(1).apply(3).setBounds(200, 175, w, h)        
        LContinentesJ.apply(1).apply(4).setBounds(370, 55, w, h)
        LContinentesA.apply(1).apply(4).setBounds(390, 30, w, h)        
        LContinentesJ.apply(1).apply(5).setBounds(140, 65, w, h)
        LContinentesA.apply(1).apply(5).setBounds(180, 65, w, h)        
        LContinentesJ.apply(1).apply(6).setBounds(220, 110, w, h)
        LContinentesA.apply(1).apply(6).setBounds(220, 90, w, h)
        LContinentesJ.apply(1).apply(7).setBounds(290, 110, w, h)
        LContinentesA.apply(1).apply(7).setBounds(290, 90, w, h)        
        LContinentesJ.apply(1).apply(8).setBounds(150, 130, w, h)
        LContinentesA.apply(1).apply(8).setBounds(150, 150, w, h)       
        
        // Labels da América do Sul
        LContinentesJ.apply(2).apply(0).setBounds(280, 420, w, h)
        LContinentesA.apply(2).apply(0).setBounds(250, 450, w, h)
        LContinentesJ.apply(2).apply(1).setBounds(320, 320, w, h)
        LContinentesA.apply(2).apply(1).setBounds(320, 350, w, h)
        LContinentesJ.apply(2).apply(2).setBounds(200, 320, w, h)
        LContinentesA.apply(2).apply(2).setBounds(225, 350, w, h)
        LContinentesJ.apply(2).apply(3).setBounds(210, 275, w, h)
        LContinentesA.apply(2).apply(3).setBounds(260, 265, w, h)                         
        
        // Labels da Ásia
        LContinentesJ.apply(3).apply(0).setBounds(640, 130, w, h)
        LContinentesA.apply(3).apply(0).setBounds(680, 130, w, h)
        LContinentesJ.apply(3).apply(1).setBounds(740, 170, w, h)
        LContinentesA.apply(3).apply(1).setBounds(810, 170, w, h)
        LContinentesJ.apply(3).apply(2).setBounds(740, 205, w, h)
        LContinentesA.apply(3).apply(2).setBounds(690, 180, w, h)
        LContinentesJ.apply(3).apply(3).setBounds(760, 95, w, h)
        LContinentesA.apply(3).apply(3).setBounds(800, 100, w, h)
        LContinentesJ.apply(3).apply(4).setBounds(900, 145, w, h)
        LContinentesA.apply(3).apply(4).setBounds(900, 175, w, h)
        LContinentesJ.apply(3).apply(5).setBounds(900, 60, w, h)
        LContinentesA.apply(3).apply(5).setBounds(900, 80, w, h)
        LContinentesJ.apply(3).apply(6).setBounds(600, 200, w, h)
        LContinentesA.apply(3).apply(6).setBounds(620, 220, w, h)
        LContinentesJ.apply(3).apply(7).setBounds(770, 130, w, h)
        LContinentesA.apply(3).apply(7).setBounds(820, 130, w, h)
        LContinentesJ.apply(3).apply(8).setBounds(820, 240, w, h)
        LContinentesA.apply(3).apply(8).setBounds(780, 230, w, h)
        LContinentesJ.apply(3).apply(9).setBounds(680, 40, w, h)
        LContinentesA.apply(3).apply(9).setBounds(710, 40, w, h)
        LContinentesJ.apply(3).apply(10).setBounds(650, 60, w, h)
        LContinentesA.apply(3).apply(10).setBounds(680, 80, w, h)
        LContinentesJ.apply(3).apply(11).setBounds(765, 60, w, h)
        LContinentesA.apply(3).apply(11).setBounds(820, 60, w, h)
        
        // Labels da Europa
        LContinentesJ.apply(4).apply(0).setBounds(460, 100, w, h)
        LContinentesA.apply(4).apply(0).setBounds(420, 100, w, h)
        LContinentesJ.apply(4).apply(1).setBounds(440, 55, w, h)
        LContinentesA.apply(4).apply(1).setBounds(440, 75, w, h)
        LContinentesJ.apply(4).apply(2).setBounds(490, 110, w, h)
        LContinentesA.apply(4).apply(2).setBounds(520, 110, w, h)
        LContinentesJ.apply(4).apply(3).setBounds(490, 70, w, h)
        LContinentesA.apply(4).apply(3).setBounds(530, 50, w, h)
        LContinentesJ.apply(4).apply(4).setBounds(505, 140, w, h)
        LContinentesA.apply(4).apply(4).setBounds(530, 150, w, h)
        LContinentesJ.apply(4).apply(5).setBounds(600, 70, w, h)
        LContinentesA.apply(4).apply(5).setBounds(600, 90, w, h)
        LContinentesJ.apply(4).apply(6).setBounds(445, 130, w, h)
        LContinentesA.apply(4).apply(6).setBounds(445, 150, w, h)                
        
        // Labels da Oceania
        LContinentesJ.apply(5).apply(0).setBounds(950, 380, w, h)
        LContinentesA.apply(5).apply(0).setBounds(950, 400, w, h)        
        LContinentesJ.apply(5).apply(1).setBounds(870, 260, w, h)
        LContinentesA.apply(5).apply(1).setBounds(800, 300, w, h)        
        LContinentesJ.apply(5).apply(2).setBounds(930, 300, w, h)
        LContinentesA.apply(5).apply(2).setBounds(960, 320, w, h)        
        LContinentesJ.apply(5).apply(3).setBounds(860, 375, w, h)
        LContinentesA.apply(5).apply(3).setBounds(890, 400, w, h)        
                
        AdicionaTodasListas        
        
        validate
        repaint(1)
    }

    // inicializa listas de labels
    private def InicializaTodasListas() : Unit = {       
        LContinentesJ.update(0,InicializaFontes(LContinentesJ.apply(0), Color.red))
        LContinentesJ.update(1,InicializaFontes(LContinentesJ.apply(1), Color.red))
        LContinentesJ.update(2,InicializaFontes(LContinentesJ.apply(2), Color.red))
        LContinentesJ.update(3,InicializaFontes(LContinentesJ.apply(3), Color.red))
        LContinentesJ.update(4,InicializaFontes(LContinentesJ.apply(4), Color.red))
        LContinentesJ.update(5,InicializaFontes(LContinentesJ.apply(5), Color.red))
        
        LContinentesA.update(0,InicializaFontes(LContinentesA.apply(0), Color.blue))
        LContinentesA.update(1,InicializaFontes(LContinentesA.apply(1), Color.blue))
        LContinentesA.update(2,InicializaFontes(LContinentesA.apply(2), Color.blue))
        LContinentesA.update(3,InicializaFontes(LContinentesA.apply(3), Color.blue))
        LContinentesA.update(4,InicializaFontes(LContinentesA.apply(4), Color.blue))
        LContinentesA.update(5,InicializaFontes(LContinentesA.apply(5), Color.blue))        
        
        InicializaListaLabels(LContinentesJ.apply(0), LContinentesJ.apply(0).size - 1)
        InicializaListaLabels(LContinentesJ.apply(1), LContinentesJ.apply(1).size - 1)
        InicializaListaLabels(LContinentesJ.apply(2), LContinentesJ.apply(2).size - 1)
        InicializaListaLabels(LContinentesJ.apply(3), LContinentesJ.apply(3).size - 1)
        InicializaListaLabels(LContinentesJ.apply(4), LContinentesJ.apply(4).size - 1)
        InicializaListaLabels(LContinentesJ.apply(5), LContinentesJ.apply(5).size - 1)
        
        InicializaListaLabels(LContinentesA.apply(0), LContinentesA.apply(0).size - 1)
        InicializaListaLabels(LContinentesA.apply(1), LContinentesA.apply(1).size - 1)
        InicializaListaLabels(LContinentesA.apply(2), LContinentesA.apply(2).size - 1)
        InicializaListaLabels(LContinentesA.apply(3), LContinentesA.apply(3).size - 1)
        InicializaListaLabels(LContinentesA.apply(4), LContinentesA.apply(4).size - 1)
        InicializaListaLabels(LContinentesA.apply(5), LContinentesA.apply(5).size - 1)
    }
    
    // adiciona as labels no JFrame
    private def AdicionaTodasListas() : Unit = {
	AdicionaListaLabels(LContinentesJ.apply(0))
        AdicionaListaLabels(LContinentesJ.apply(1))
        AdicionaListaLabels(LContinentesJ.apply(2))
        AdicionaListaLabels(LContinentesJ.apply(3))
        AdicionaListaLabels(LContinentesJ.apply(4))
        AdicionaListaLabels(LContinentesJ.apply(5))
        
        AdicionaListaLabels(LContinentesA.apply(0))
        AdicionaListaLabels(LContinentesA.apply(1))
        AdicionaListaLabels(LContinentesA.apply(2))
        AdicionaListaLabels(LContinentesA.apply(3))
        AdicionaListaLabels(LContinentesA.apply(4))
        AdicionaListaLabels(LContinentesA.apply(5))        
    }
    
    // adiciona no JFrame uma lista de labels
    private def AdicionaListaLabels(lista: Array[JLabel]) : Unit = {
        for (l<-lista)
            add(l)
    }
    
    // insere um elemento em um array, retornando outro com o tamanho do original mais um
    private def insereElemento(label: JLabel, array: Array[JLabel]) : Array[JLabel] = {
      var arrayConcatenado : Array[JLabel] = new Array(array.length +1)
      arrayConcatenado.update(0,label)
      for(i <- 0 until array.length)
        arrayConcatenado.update(i+1,array.apply(i))
      arrayConcatenado
    }
    
    // retorna o array sem o primeiro elemento
    private def tail(lista: Array[JLabel]) : Array[JLabel] = {
      var tailLista: Array[JLabel] = new Array(lista.length-1)
      var max: Int = lista.length - 1
      for (i <- 0 until max){         
            tailLista.update(i,lista.apply(i+1))
      }
      tailLista
    }
    
    // inicializa fontes de uma label
    private def inicializaFonte(label:JLabel,color:Color): JLabel = {
      var fonte: Font = new Font ("TimesRoman", Font.BOLD, 15)
      label.setFont(fonte)
      label.setForeground(color)
      label.setOpaque(true)
      label.setBackground(Color.white)
      var labelModificado: JLabel = label
      labelModificado
    }
    
    // inicializa fonte de todas as labels de um array 
    private def inicializaArray(lista: Array[JLabel],color:Color, f: (JLabel,Color) => JLabel): Array[JLabel] =
      if (lista.length == 0) lista
      else insereElemento(f(lista.apply(0),color), inicializaArray(tail(lista),color, f))
    
    // inicializa fonte de uma lista de labels
    private def InicializaFontes(lista: Array[JLabel], color:Color) : Array[JLabel] = {        
        inicializaArray(lista,color,inicializaFonte)
    }
    
    // inicializa lista de labels, atualizando texto para indicar nenhum exército
    private def InicializaListaLabels(l: Array[JLabel], a: Int): Unit = {
        for (i<-0 to a by 1)            
            l.apply(i).setText("0")
    }     
    
    // atualiza uma label com um número determinado de exércitos
    private def AtualizaLabel(l: Array[JLabel], r: Int, n: Int) : Unit = {        
      l.apply(r).setText(Integer.toString(n))
      
      validate
      repaint(1)
    }     
  
    // inicializa menu do JFrame
    private def initComponents() : Unit = {
        jMenuBar1 = new JMenuBar
        jMenu1 = new JMenu
        jMenuItem1 = new JMenuItem
        jMenuItem2 = new JMenuItem
        jMenu2 = new JMenu

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo War")
        getContentPane().setLayout(null)

        jMenu1.setText("Jogo")
        jMenu1.setMinimumSize(new java.awt.Dimension(40, 20))

        jMenuItem1.setText("Novo Jogo")
        jMenu1.add(jMenuItem1)
        
        jMenuItem2.setText("Sortear objetivos")
        jMenu1.add(jMenuItem2)

        jMenuBar1.add(jMenu1)

        jMenu2.setText("Sair")
        jMenu2.setMinimumSize(new java.awt.Dimension(40, 20))        
        
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            override def mouseClicked(evt: java.awt.event.MouseEvent) : Unit = {
                jMenu2(evt)
            }
        })        
      
        jMenuItem1.addActionListener(new ActionListener() {
            override def actionPerformed(evt: java.awt.event.ActionEvent) : Unit = {
               jMenuItem1(evt)
            }
        })        

        jMenuItem2.addActionListener(new ActionListener() {
            override def actionPerformed(evt: java.awt.event.ActionEvent) : Unit = {
               jMenuItem2(evt)
            }
        })
      
        // adiciona menus no JFrame
        jMenuBar1.add(jMenu2)

        setJMenuBar(jMenuBar1)

        pack()
    }

    // evento do submenu Sair
    private def jMenu2(evt: java.awt.event.MouseEvent) : Unit = {
        var retorno: Int = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION)
        if(retorno == JOptionPane.YES_OPTION)
            System.exit(0)
    }            
    
    // evento do submenu Novo Jogo
    private def jMenuItem1(evt: java.awt.event.ActionEvent) : Unit = {
        NovoJogo        
        JOptionPane.showMessageDialog(null, "Novo jogo iniciado!", "Novo jogo", JOptionPane.INFORMATION_MESSAGE)
    }
     
    // evento do submenu Sortear Objetivos
    private def jMenuItem2(evt: java.awt.event.ActionEvent) : Unit = {
        Jogo.CarregaObjetivos
        AjustarObjetivoJ
        AjustarObjetivoA
        btObjJ.setEnabled(true)
        btObjA.setEnabled(true)
        botaoPosicionar.setEnabled(true)
        JOptionPane.showMessageDialog(null, "Objetivos sorteados. Bom jogo!", "Objetivos", JOptionPane.INFORMATION_MESSAGE)        
        jMenuItem2.setEnabled(false)
    }  
    
    // inicializa variáveis e configurações para um novo jogo
    private def NovoJogo() : Unit = {       
        botaoFimJogada.setEnabled(false)
        Jogo.NovoJogo    
        InicializaTodasListas
        jogador = true
        jaPosicionados = false
        exercPosicionadosJ = 0
        exercPosicionadosA = 0        
        EsconderAtaque        
        objetivosSorteados = false
        objEscondidoJ = false
        objEscondidoA = false
        jogoAcabou = false
        jMenuItem2.setEnabled(true)
        btObjJ.setEnabled(false)
        btObjA.setEnabled(false)
        botaoPosicionar.setEnabled(false)
        botaoPreparar.setEnabled(false)        
        ConfiguraLabelJogador
        PreencheComboExercitosP(10)
        ImprimeLabels
        ConfiguraAvisos              
    }
    
    // posiciona exércitos na região do continente contida nas combos
    private def PosicionaExercitos() : Unit = {      
        if (jogador){
          if (Jogo.MapaA.QtdeExercitos(Continente.getSelectedIndex, Regiao.getSelectedIndex) == 0){
            // atualiza mapa
            Jogo.MapaJ.AcrescentarExercitos(Continente.getSelectedIndex, Regiao.getSelectedIndex, ExercitosPosicionar.getSelectedIndex)
            exercPosicionadosJ = exercPosicionadosJ + ExercitosPosicionar.getSelectedIndex;                         

            // atualiza labels
            remove(LContinentesJ.apply(Continente.getSelectedIndex).apply(Regiao.getSelectedIndex))
            AtualizaLabel(LContinentesJ.apply(Continente.getSelectedIndex), Regiao.getSelectedIndex, Jogo.MapaJ.QtdeExercitos(Continente.getSelectedIndex, Regiao.getSelectedIndex))        
            add(LContinentesJ.apply(Continente.getSelectedIndex).apply(Regiao.getSelectedIndex))
        
            // se o jogador já posicionou seus 10 exércitos iniciais, passa a vez para o adversário
            if (!jaPosicionados && exercPosicionadosJ == 10){
              jogador = false             
              exercPosicionadosJ = 0
            }                               
          } else
              JOptionPane.showMessageDialog(null, "Exércitos inimigos nesse território!", "Jogada inválida", JOptionPane.INFORMATION_MESSAGE)              
        }            
        else {
          if (Jogo.MapaJ.QtdeExercitos(Continente.getSelectedIndex, Regiao.getSelectedIndex) == 0){
            // atualiza mapa
            Jogo.MapaA.AcrescentarExercitos(Continente.getSelectedIndex, Regiao.getSelectedIndex, ExercitosPosicionar.getSelectedIndex)
            exercPosicionadosA = exercPosicionadosA + ExercitosPosicionar.getSelectedIndex;
                    
            // atualiza labels
            remove(LContinentesA.apply(Continente.getSelectedIndex).apply(Regiao.getSelectedIndex))
            AtualizaLabel(LContinentesA.apply(Continente.getSelectedIndex), Regiao.getSelectedIndex, Jogo.MapaA.QtdeExercitos(Continente.getSelectedIndex, Regiao.getSelectedIndex))        
            add(LContinentesA.apply(Continente.getSelectedIndex).apply(Regiao.getSelectedIndex))      
        
            // se o adversário já posicionou seus 10 exércitos iniciais, passa a vez para o jogador
            if (!jaPosicionados && exercPosicionadosA == 10){
              exercPosicionadosA = 0      
              jaPosicionados = true;
              botaoPreparar.setEnabled(true)
              ExercitosAtacar.setEnabled(true)
              jogador = true            
              ExercitosPosicionar.removeAllItems
              botaoFimJogada.setEnabled(true)
            }                                    
          } else{
            JOptionPane.showMessageDialog(null, "Exércitos inimigos nesse território!", "Jogada inválida", JOptionPane.INFORMATION_MESSAGE)
          }       
        }
    
        // FUNÇAO DE ORDEM MAIOR
        def apply(f: Int => Unit, v: Int) = f(v) 
        
        // verifica quantos exércitos devem ser posicionados ainda
        if (!jaPosicionados){
          if (jogador) apply(PreencheComboExercitosP, 10 - exercPosicionadosJ)
          else apply(PreencheComboExercitosP, 10 - exercPosicionadosA)
        } else {
          if (jogador) apply(PreencheComboExercitosP, 3 - exercPosicionadosJ)
          else apply(PreencheComboExercitosP, 3 - exercPosicionadosA)
        }
          
        PreencheComboExercitosA
        ConfiguraLabelJogador
        ImprimeLabels
    }
    
    // habilita painel de ataque
    private def MostrarAtaque(): Unit = {
      botaoAtacar.setEnabled(true)
      Ataque.setEnabled(true)
      botaoCancAtaque.setEnabled(true)
      Continente.setEnabled(false)
      Regiao.setEnabled(false)
      ExercitosPosicionar.setEnabled(false)
      ExercitosAtacar.setEnabled(false)
      botaoPosicionar.setEnabled(false)
      botaoPreparar.setEnabled(false)
    
      validate()
      repaint(1)
    }
    
    // desabilita painel de ataque
    private def EsconderAtaque(): Unit = {
      botaoAtacar.setEnabled(false)
      Ataque.setEnabled(false)      
      botaoCancAtaque.setEnabled(false)
      Continente.setEnabled(true)
      Regiao.setEnabled(true)
      ExercitosPosicionar.setEnabled(true)
      ExercitosAtacar.setEnabled(true)
      if (jaPosicionados){      
        botaoPosicionar.setEnabled(true)
        botaoPreparar.setEnabled(true)      
      }
      
      validate()
      repaint(1)     
    }    
    
    // verifica se o jogador perdeu exércitos, retornando o número atualizado
    private def ExercitosPerdidos(exercitos: Int, nroJ: Int, nroA: Int, j: Boolean) : Int = {
        if (j && (jogador && nroJ <= nroA) || (!jogador && nroJ < nroA) ||
            !j && ((jogador && nroJ > nroA) || (!jogador && nroJ <= nroA)))
          exercitos + 1
        else        
          exercitos      
    }
    
    // ataca determinada região
    private def AtacarRegiao() : Unit = {
      var ataque: Int = Ataque.getSelectedIndex            
      var r: String = ""
      var nroDados: Int  = 0
      var nAtaque: Int = ExercitosAtacar.getSelectedIndex
      var nDefesa: Int = 0
      var n: Int = 0
      
      // verifica quantos exércitos a defesa possui
      if (jogador) {
        ListaAtaque.apply(ataque) match {
          case (c, r) => nDefesa = Jogo.MapaA.ListaContinentes.apply(c).ListaTerritorios.apply(r)
          case _ => {}
        }
      } else {
        ListaAtaque.apply(ataque) match {
          case (c, r) => nDefesa = Jogo.MapaJ.ListaContinentes.apply(c).ListaTerritorios.apply(r)        
          case _ => {}
        }
      }
      
      // verifica quantos dados o atacante tem direito
      if (nDefesa > 3) nDefesa = 3
      if (nAtaque > nDefesa) n = nDefesa else n = nAtaque
      
      // pergunta o número de dados desejados
      while(r != null && (nroDados < 1 || nroDados > n)){        
        while(r == "")
          r = JOptionPane.showInputDialog("Número de dados escolhido pela atacante (de 1 a " + n + "):")
        
        if (r != null){          
          nroDados = Integer.parseInt(r)
          
          // verifica se o número está correto
          if (nroDados < 1 || nroDados > n){
            nroDados = 0        
            r = ""
          } 
        }
      }
      
      // se a ação não foi cancelada, ataca a região
      if (r != null){
        var exercitosJ: Int = 0
        var exercitosA: Int = 0        
        var continenteJ: Int = 0
        var regiaoJ: Int = 0
        var continenteA: Int = 0
        var regiaoA: Int = 0
        
        // verifica continente e região de cada jogador
        if (jogador){
          continenteJ = Continente.getSelectedIndex
          regiaoJ = Regiao.getSelectedIndex
          ListaAtaque.apply(ataque) match {
            case (c, r) => {
                continenteA = c
                regiaoA = r
            }
            case _ => {}
          }
        } else {
          continenteA = Continente.getSelectedIndex
          regiaoA = Regiao.getSelectedIndex
          ListaAtaque.apply(ataque) match {
            case (c, r) => {
                continenteJ = c
                regiaoJ = r
            }
            case _ => {}
          }          
        }        
      
        // inicializa dado e variáveis para o sorteio
        var dado: Dado = new Dado        
                
        var nroJ: Int = 0
        var nroA: Int = 0
        var nroDado: Int = 1
        
        while (nroDado != nroDados + 1){
          nroJ = dado.JogarDado
          nroA = dado.JogarDado
          JOptionPane.showMessageDialog(null, "Jogador 1: " + nroJ + " - Jogador 2: " + nroA, "Dado " + nroDado, JOptionPane.INFORMATION_MESSAGE)              
          
          // atualiza número de exércitos perdidos
          exercitosJ = ExercitosPerdidos(exercitosJ, nroJ, nroA, true)
          exercitosA = ExercitosPerdidos(exercitosA, nroJ, nroA, false)          
          nroDado = nroDado + 1
        }
        
        // decrementa exércitos perdidos de ambos os jogadores
        if (exercitosJ > 0){
          Jogo.MapaJ.RetirarExercitos(continenteJ, regiaoJ, exercitosJ)            
          remove(LContinentesJ.apply(continenteJ).apply(regiaoJ))
          AtualizaLabel(LContinentesJ.apply(continenteJ), regiaoJ, Jogo.MapaJ.QtdeExercitos(continenteJ, regiaoJ))        
          add(LContinentesJ.apply(continenteJ).apply(regiaoJ))           
        }
        
        if (exercitosA > 0){
          Jogo.MapaA.RetirarExercitos(continenteA, regiaoA, exercitosA)            
          remove(LContinentesA.apply(continenteA).apply(regiaoA))
          AtualizaLabel(LContinentesA.apply(continenteA), regiaoA, Jogo.MapaA.QtdeExercitos(continenteA, regiaoA))        
          add(LContinentesA.apply(continenteA).apply(regiaoA))                        
        }
        
        // verifica se há possibilidade de um novo ataque a mesma região com o mesmo número de exércitos
        if ((jogador && Jogo.MapaJ.ListaContinentes.apply(Continente.getSelectedIndex).ListaTerritorios.apply(Regiao.getSelectedIndex) < ExercitosAtacar.getSelectedIndex + 1)
              || (!jogador && Jogo.MapaA.ListaContinentes.apply(Continente.getSelectedIndex).ListaTerritorios.apply(Regiao.getSelectedIndex) < ExercitosAtacar.getSelectedIndex + 1)){
                // se não há, desabilita ataque
                EsconderAtaque
                PreencheComboExercitosA
              }
                
          
        validate
        repaint(1)
      }
    }
    
    // preenche uma lista com as opções de ataque de um determinado continente
    private def PreencherListaAtaque(ListaDeAtaques: List[(Int, Int)], c: ECont.Value, Lista: Array[Int]) : List[(Int, Int)] = {
        var ListaAdicional: List[(Int, Int)] = ListaDeAtaques
        for (l<-Lista)
          ListaAdicional = ListaAdicional ::: List((c.id, l))
        ListaAdicional
    }
    
    // preenche combo com as opções de ataque
    private def PreencherCombosAtaque () : Unit = {            
      Ataque.removeAllItems
      
      var Auxiliar: List[(Int, Int)] = List()
      var c: Int = Continente.getSelectedIndex
      var r: Int = Regiao.getSelectedIndex
      
      // verifica possibilidades de ataques de uma determinada região
      for (v<-ECont.values)
        Auxiliar = PreencherListaAtaque(Auxiliar, v, Jogo.PossibilidadeAtaque(ECont.apply(c), r, v))                                     
      
      ListaAtaque = List()      
      
      // verifica se o outro jogador possui exércitos nas possibilidades de ataque
      if (jogador){
        for (l<-Auxiliar){
          l match {
            case (cont, reg) =>
              if (Jogo.MapaA.ListaContinentes.apply(cont).ListaTerritorios.apply(reg) > 0){
                Ataque.addItem(ECont.apply(cont).toString + " - região " + (reg + 1))        
                ListaAtaque = PreencherListaAtaque(ListaAtaque, ECont.apply(cont), Array(reg))
              }
            case _ => {}
          }          
        }
      } else {
        for (l<-Auxiliar){
          l match {
            case (cont, reg) =>
              if (Jogo.MapaJ.ListaContinentes.apply(cont).ListaTerritorios.apply(reg) > 0){
                Ataque.addItem(ECont.apply(cont).toString + " - região " + (reg + 1))        
                ListaAtaque = PreencherListaAtaque(ListaAtaque, ECont.apply(cont), Array(reg))
              }
            case _ => {}
          }          
        }
      }      
    }
    
    // habilita painel de ataque
    private def PrepararAtaque () : Unit = {
      if (ExercitosAtacar.getSelectedIndex > 0){                
        PreencherCombosAtaque
        if (ListaAtaque.isEmpty) EsconderAtaque
        else{
          MostrarAtaque
          Ataque.setSelectedIndex(0)
        }         
      }                    
    }   
    
    // habilita ou desabilita visualização do objetivo do jogador
    private def AjustarObjetivoJ() : Unit = {      
      if (objEscondidoJ)
        ObjJ.setText(EObj.apply(Jogo.ObjJ).toString)
      else
        EscondeObjetivos(1)
      
      objEscondidoJ = !objEscondidoJ      
    }
    
    // habilita ou desabilita visualização do objetivo do adversário
    private def AjustarObjetivoA() : Unit = {
      if (objEscondidoA)
        ObjA.setText(EObj.apply(Jogo.ObjA).toString)
      else
        EscondeObjetivos(2)      
      
      objEscondidoA = !objEscondidoA
    }
    
    // verifica vitória de algum jogador
    private def Vitoria() : Boolean = {
      if (Jogo.JogadorAtingiuObj){
        jogoAcabou = true
        JOptionPane.showMessageDialog(null, "Parabéns jogador 1: você ganhou!", "VENCEDOR", JOptionPane.INFORMATION_MESSAGE)
      }
      
      if (Jogo.AdversarioAtingiuObj){
        jogoAcabou = true
        JOptionPane.showMessageDialog(null, "Parabéns jogador 2: você ganhou!", "VENCEDOR", JOptionPane.INFORMATION_MESSAGE)
      }      

      if (jogoAcabou){
        EsconderAtaque
        botaoPosicionar.setEnabled(false)
        botaoPreparar.setEnabled(false)                
        botaoFimJogada.setEnabled(false)
        jMenuItem2.setEnabled(false)
      }
      
      jogoAcabou
    }
  
    // configurações quando o jogador já realizou sua jogada
    private def FimJogada() : Unit = {
      exercPosicionadosJ = 0
      exercPosicionadosA = 0
      
      if (!Vitoria){
        jogador = !jogador
        EsconderAtaque
        ConfiguraLabelJogador
      
        if (jogador) PreencheComboExercitosP(3 - exercPosicionadosJ)
        else PreencheComboExercitosP(3 - exercPosicionadosA)      
        PreencheComboExercitosA
      }
      
      validate
      repaint(1)
    }
}
