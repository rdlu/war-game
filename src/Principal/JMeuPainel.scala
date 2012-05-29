/* Trabalho prático de Modelos de Linguagens de Programação: Jogo War
   Anderson Foscarini 180181
   Paula Burguêz 180663
   Ricardo Chagas Rapacki 180659
 */

package Principal

import javax.swing._;
import java.awt._;

class JMeuPainel extends JPanel {    
    private var im: Image = new ImageIcon ( getClass ( ).getResource ( "/Principal/risk.png" ) ).getImage ( );
    
    // carrega a imagem do mapa no painel
    override def paintComponent ( g: Graphics ): Unit = {  
        super.paintComponent ( g );  
        g.drawImage ( im , 0, 0, this );  
    }
}