package Enigma;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public final class Jogo extends JFrame{
    
  JLabel P00, P01, P02, P03, P10, P11, P12, P13, P20, P21, P22, P23, P30, P31, P32, P33, Todo, msg; 
  JLabel[] labels = {P00, P01, P02, P10, P11, P12, P20, P21, P22, P23}; 
  JButton jogar, sair, w, a, s, d;  
  public int count = 0;
  public int inicio = 0;
  public char spriteType = 'P';
   
  Jogo(){                  
    getContentPane().setLayout(null);
    initMenu();       
  }   
     
  public void inicializaComponentes(){
           
    s = new JButton();    
    add(s); 
    
    w = new JButton(); 
    add(w); 
    
    a = new JButton();
    add(a);  
    
    d = new JButton();
    add(d);
    
    for(int i=0; i<3; i++){            
      for(int j=0; j<3; j++){
        labels[(i*3)+j] = new JLabel();
        labels[(i*3)+j].setBounds((i*133),(j*100),200,200);
        add(labels[(i*3)+j]);
      }
    }  
  }
  
  public int iniciaJogo(){
    return 1;
  }

  public char setSpriteType(int valor) {
    if (valor == 100) {
      return 'D';
          
    } else {
      return 'P';
    }
  }
  
  public void setSprites(int valores[][]) {
    String sprite;
    
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        sprite = String.valueOf(valores[i][j]);
        
        if (valores[i][j] != 0) {
          sprite = "recursos/" + spriteType + sprite + ".png";    
            
        } else {
          sprite = "recursos/P0.png";
        }        

        labels[(i*3)+j].setIcon(new javax.swing.ImageIcon(getClass().getResource(sprite)));   
        labels[(i*3)+j].updateUI();
      }      
    }
  }
  
  public void initMenu(){
    Todo = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/Enigma/recursos/Full.png")));
    Todo.setBounds(-101, -100, 600, 600);
    add(Todo);
      
    jogar = new JButton("Jogar");
    jogar.setBounds(8, 360, 175, 50);
    add(jogar);
    
    sair = new JButton("Sair");
    sair.setBounds(218, 360, 175, 50);
    add(sair);
  }
 
  void down(int valores[][]){
    count = easterEgg('d', count, valores);
    if (inicio == 1) {
      for (int i=0; i <= 2; i++){        
        for (int j=0; j <= 2; j++){
          if ((valores[i][j] == 0) && (j-1 != -1)){
            valores[i][j] = valores[i][j-1];
            valores[i][j-1] = 0;          
            setSprites(valores);
            ganhou(valores);

            i = 3;
            break;
          }                      
        }             
      }            
    }       
  }
   
  void up(int valores[][]){
    count = easterEgg('u', count, valores);
    if (inicio == 1) {
      for (int i=0; i <= 2; i++){        
        for (int j=0; j <= 1; j++){
          if ((valores[i][j] == 0) && (j+1 != 3)){
            valores[i][j] = valores[i][j+1];
            valores[i][j+1] = 0;
            setSprites(valores);
            ganhou(valores);

            i = 3;
            break;
          }                      
        }             
      }            
    }      
  }   
   
  void left(int valores[][]){
    count = easterEgg('l', count, valores);
    if (inicio == 1) {
      for (int i=0; i <= 1; i++){        
        for (int j=0; j <= 2; j++){
          if ((valores[i][j] == 0) && (i+1 != 3)){
            valores[i][j] = valores[i+1][j];
            valores[i+1][j] = 0;
            setSprites(valores);
            ganhou(valores);

            i = 3;
            break;
          }                      
        }             
      }           
    }           
  } 
   
  void right(int valores[][]){
    count = easterEgg('r', count, valores);
      
    if (inicio == 1) {
      for (int i=0; i <= 2; i++){        
        for (int j=0; j <= 2; j++){
          if ((valores[i][j] == 0) && (i-1 != -1)){
            valores[i][j] = valores[i-1][j];
            valores[i-1][j] = 0;
            setSprites(valores);
            ganhou(valores);

            i = 3;
            break;            
          }                      
        }             
      }          
    }     
  }

  int easterEgg(char key, int count, int valores[][]){
    if ((count == 0 || count == 1) && key == 'u') {           
      return count+1;
        
    } else if((count == 2 || count == 3) && key == 'd') {
      return count+1;
        
    } else if((count == 4 || count == 6) && key == 'l') {
      return count+1;
        
    } else if((count == 5 || count == 7) && key == 'r') {
        
      return count+1;
        
    } else if(count == 8 && key == 'b') {
        
      return count+1;
        
    } else if(count == 9 && key == 'a') {
      Todo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Enigma/recursos/Full2.png")));             
      Todo.updateUI();
      
      return 100;                         
        
    } else {
      return -1;
      
    }   
  };
   
  public void tratarEventos(int valores[][]){  
       
    s.addKeyListener(new KeyAdapter() {           
      @Override
      
      public void keyPressed( KeyEvent e) {
          switch (e.getKeyCode()) {
              case KeyEvent.VK_DOWN:
                  down(valores);
                  break;
                  
              case KeyEvent.VK_UP:
                  up(valores);
                  break;
                  
              case KeyEvent.VK_LEFT:
                  left(valores);
                  break;
                  
              case KeyEvent.VK_RIGHT:
                  right(valores);
                  break;
                  
              case KeyEvent.VK_B:
                  count = easterEgg('b', count, valores);
                  break;
                  
              case KeyEvent.VK_A:
                  count = easterEgg('a', count, valores);
                  spriteType = setSpriteType(count); 
                  break;
                  
              default:
                  break;
          }
      }
    });
       
    sair.addActionListener((ActionEvent e) -> {
        System.exit(0);
     });
        
    jogar.addActionListener((ActionEvent e) -> {
        inicio = iniciaJogo();
        Todo.setBounds(0, 0, 0, 0);
        jogar.setEnabled(false);
        jogar.setFocusable(false);
        sair.setFocusable(false);
        setSprites(valores);
     });                   
  }   

  public void ganhou(int valores[][]){
    if (valores[2][2]==0 && valores[0][0]==1 && valores[0][1]==4 && valores[0][2]==7 && valores[1][0]==2 && valores[1][1]==5 && valores[1][2]==8 && valores[2][0]==3 && valores[2][1]==6){
      JOptionPane.showMessageDialog(rootPane, "Você Ganhou!", "GG WP", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public int[][] geraValores(int arr[][]) {
    Random rand = new Random();
      
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        int verif = 0;
        do {
          arr[i][j] = rand.nextInt(9);
          verif = verificaDuplicata(arr, i, j);
                
        } while (verif == 1);
      }
    }
    
    return arr;
  }
  
  public int verificaDuplicata(int arr[][], int l, int m) {
    for (int i=0; i<=l; i++) {
      int aux = m;
      if (i<l) {
        aux = 2;
      }     
      
      for (int j=0; j<=aux; j++) {
        if ((arr[l][m] == arr[i][j]) && (l!=i || m!=j)) {
          return 1;
        }         
      }
    }
    
    return 0;
  }
  
  public static void main(String[] args) {                                  
  Jogo tela = new Jogo();
  tela.inicializaComponentes();
  tela.setTitle("Complete a figura");
  tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  tela.setSize(415,560);
  tela.setBounds(500,500,405,550);
  Dimension teletela = Toolkit.getDefaultToolkit().getScreenSize();
  tela.setLocation((teletela.width-tela.getSize().width)/2, (teletela.height-tela.getSize().height)/2);
  tela.setResizable(false);
  tela.setVisible(true);  
  int valores[][] = new int[3][3];
  valores = tela.geraValores(valores);
  tela.tratarEventos(valores);  
  } 
} 