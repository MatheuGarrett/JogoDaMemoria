/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodamemoria;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Aluno
 */
public class JogoMemoria implements ActionListener {
    JPanel p;
    JFrame f;
    String[][] matchList = { {"a", "a"}, {"b", "b"},
            {"c", "c" }, {"d", "d"}, {"e", "e"},
            {"f", "f"}, {"g", "g" }};
    JButton[][] buttons;
    int i = 0;
    boolean flipping = true;
    int cardOne;
    int secIndex;


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JogoMemoria memo = new JogoMemoria();
                memo.tela();
            }
        });
    }
    public void cartasIguais(JPanel panel) {
        buttons = new JButton[3][]; 
        for (int i= 0; i< 3*4; i++) {
            if (i%4 == 0) buttons[i/4] = new JButton[4];
            buttons[i/4][i%4] = new JButton("-");
            buttons[i/4][i%4].addActionListener(this);
            panel.add(buttons[i/4][i%4]);
        }
    }
    public void atualizaLista(String a, String b, boolean add) {
        int i,j;
        String[][] lista;
        int oldLen = matchList.length;

        if (add) { 
            lista = new String[oldLen+1][];
            lista[0] = new String[2];
            lista[0][0] = new String(a);
            lista[0][1] = new String(b);
            for (int item=1; item<= oldLen; item++) {
                lista[item][0] = matchList[item-1][0];
                lista[item][1] = matchList[item-1][1];
            }
            matchList = lista;
        } else {
            lista = new String[oldLen-1][];
            lista[0] = new String[2];
            lista[0][0] = new String(a);
            lista[0][1] = new String(b);
            for (int item=0; item<= oldLen; item++) {
                if (a != lista[item][0]) {
                    lista[item][0] = matchList[item][0];
                    lista[item][1] = matchList[item][1];
                }
            }
            matchList = lista;
        }
    }
    public  void tela() {    
        JFrame frame = new JFrame("Jogo da Memoria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new JPanel(new GridLayout(3,4));
        p.setPreferredSize(new Dimension(500, 300));
        cartasIguais(p);    
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(p,BorderLayout.CENTER);

        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        int r,c;

        if (i<2) {
            for (r=0; r< 3; r++) {
                for (c=0; c< 4; c++) {
                        if ((e.getSource()== buttons[r][c]) && buttons[r][c].getText().equals("-")){
                        buttons[r][c].setText(matchList[(r*4+c)/2][(r*4+c)%2]);
                        i++; 
                        if (i==1) cardOne = (r*4+c)/2; 
                        else secIndex = (r*4+c)/2; 
                        return;
                    }
                }
            }
        } else { 
            for (r=0; r< 3; r++) {
                for (c=0; c< 4; c++) {
                    if (cardOne == secIndex) { 
                        if (!buttons[r][c].getText().equals("-")) 
                            buttons[r][c].setText("Acertou"); 
                    } else if ((!buttons[r][c].getText().equals("Acertou")) && (!buttons[r][c].getText().equals("-"))) {
                        buttons[r][c].setText("-"); 
                    }
                }
                i=0; 
            }
        }
    }
}    
