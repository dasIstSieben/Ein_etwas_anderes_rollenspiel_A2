import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private Gamemanager gamemanager;

    Images images = new Images ();

    public  JFrame frame;


    public static JLayeredPane layeredPane;

    public  JLabel hintergrund;

    public  JLabel character;

    public  JTextArea textfeld;



    public JButton btn1;
    public JButton btn2;
    public JButton btn3;
    public JButton btn4;
    public JButton btnWeiter;

    private int tuerEntscheidung = 0;

    public int getTuerEntscheidung(){

        return tuerEntscheidung;
    }


    public void initialize(Gamemanager p) {

        images.loadingImages();

        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(50, 50, 1000, 600);

        layeredPane = new JLayeredPane();
        layeredPane.setVisible(true);
        frame.add(layeredPane);

        hintergrund = new JLabel("hintergrund");
        layeredPane.add(hintergrund);
        layeredPane.setLayer(hintergrund, 0);
        hintergrund.setOpaque(true);
        //nur zu Testzwecken eingefärbt
        hintergrund.setBackground(Color.ORANGE);
        hintergrund.setBounds(270, 1, 1000, 600);

        character = new JLabel("charakter");
        character.setVisible(true);
        layeredPane.add(character);
        layeredPane.setLayer(character, 1);
        character.setBounds(1, 1, 300, 600);
        character.setIcon(new ImageIcon(images.CharakterFaultier));
        character.setOpaque(true);



        textfeld = new JTextArea(10,60);
        textfeld.setVisible(true);
        textfeld.setFont(new Font("Verdana",Font.PLAIN,13));
        layeredPane.add(textfeld);
        layeredPane.setLayer(textfeld,2);
        textfeld.setBounds(5, 300, 990, 180);
        textfeld.setEditable(false);
        textfeld.setLineWrap(true);
        textfeld.setWrapStyleWord(true);


        btnWeiter = new JButton("Start");
        btnWeiter.addActionListener(this);
        layeredPane.add(btnWeiter);
        layeredPane.setLayer(btnWeiter,3);
        btnWeiter.setVisible(true);
        btnWeiter.setBounds(400, 495, 200, 50);

        btn1 = new JButton("");
        btn1.addActionListener(this);
        layeredPane.add(btn1);
        layeredPane.setLayer(btn1,2);
        btn1.setVisible(false);
        btn1.setBounds(30, 495, 200, 50);

        btn2 = new JButton("Faultier");
        btn2.addActionListener(this);
        layeredPane.add(btn2);
        layeredPane.setLayer(btn2,2);
        btn2.setVisible(false);
        btn2.setBounds(270, 495, 200, 50);

        btn3 = new JButton("Mensch");
        btn3.addActionListener(this);
        layeredPane.add(btn3);
        layeredPane.setLayer(btn3,2);
        btn3.setVisible(false);
        btn3.setBounds(510, 495, 200, 50);

        btn4 = new JButton("Antwort 4");
        btn4.addActionListener(this);
        layeredPane.add(btn4);
        layeredPane.setLayer(btn4,2);
        btn4.setVisible(false);
        btn4.setBounds(750, 495, 200, 50);



        gamemanager = p;


    }
    //Änderung des Textes auf dem Buttons
    public void changeButtonLable(String a,String b,String c, String d){
        btn1.setText(a);
        btn2.setText(b);
        btn3.setText(c);
        btn4.setText(d);

    }

    public void changeButtonVisibility(boolean a,boolean b,boolean c,boolean d, boolean e) {
        btn1.setVisible(a);
        btn2.setVisible(b);
        btn3.setVisible(c);
        btn4.setVisible(d);
        btnWeiter.setVisible(e);

    }


    public void actionPerformed(ActionEvent e) {

        //Buttonevent um den Text (String) im Textfeld zu zeigen
        if(e.getSource()==btnWeiter){
            textfeld.setText(gamemanager.userInput(1));
        }




        if (e.getSource() == btn1) {

            //Enstcheidung Tür
            if(gamemanager.getGamestate() == 8){

                textfeld.setText(Text.Chap1_Klopfen);
                //hintergrund.setIcon(new ImageIcon(images.TuerOmaZu));
                changeButtonVisibility(false,false,false,false,true);
                tuerEntscheidung = 1;


            }

            //Entscheidung Zocken
            if(gamemanager.getGamestate() == 10){
                textfeld.setText(Text.Chap2_Auswahl1);
                changeButtonVisibility(false,false,false,false, true);

            }

            //Entscheidung Auto ( nur Bobbycar(btn 2) führt zum Weiterlaufen des Spiels)
            if(gamemanager.getGamestate() == 11){

                textfeld.setText(Text.Chap3_Auswahl1);
                gamemanager.setGamestate (10);
                btnWeiter.setText("Na gut, dann nehme ich...");
                changeButtonVisibility(false,false,false,false,true);

            }
            //Ensteidung langeweile
            if(gamemanager.getGamestate()== 15){

                textfeld.setText(Text.Chap4_Auswahl1);
                changeButtonVisibility(false,false,false,false, true);
            }

            //Entscheidung Flucht
            if(gamemanager.getGamestate()== 17){

                //gui.hintergund.setIcon (new ImageIcon(WaldEnde));
                textfeld.setText(Text.Chap5_Auswahl1);
                changeButtonVisibility(false,false,false,false,true);
            }






        }else if(e.getSource()==btn2){

            //Entscheidung Charakter
            if(gamemanager.getGamestate() == 2){

                textfeld.setText(Text.CharakterFaultier);
                character.setIcon(new ImageIcon(images.CharakterFaultier));
                btnWeiter.setText("Weiter");
                changeButtonVisibility(false,false,false,false,true);

            }

            //Enstcheidung Tür
            if(gamemanager.getGamestate() == 8){

                textfeld.setText(Text.Chap1_Auswahl2);
                //hintergrund.setIcon(new ImageIcon(images.TuerGeistZu));
                changeButtonVisibility(false,false,false,false,true);
                tuerEntscheidung =2;

            }

            //Enstscheidung Zocken
            if(gamemanager.getGamestate() == 10){

                //TODO Minispiel einrichten
                textfeld.setText(Text.Chap2_Auswahl2);
                changeButtonVisibility(false,false,false,false,true);

            }

            //Entscheidung Auto ( nur Bobbycar(btn 2) führt zum Weiterlaufen des Spiels)
            if(gamemanager.getGamestate() == 11){
                //
                textfeld.setText(Text.Chap3_Auswahl2);
                changeButtonVisibility(false,false,false,false,true);

            }

            //Enstcheidung Langeweile
            if(gamemanager.getGamestate()== 15){

                textfeld.setText(Text.Chap4_Auswahl2);
                changeButtonVisibility(false,false,false,false,true);
            }

            //Entscheidung Flucht
            if(gamemanager.getGamestate()== 17){

                textfeld.setText(Text.Chap5_Auswahl2);
                changeButtonVisibility(false,false,false,false,true);
            }







        }else if(e.getSource()==btn3){

            //Entscheidung Charakter
            if(gamemanager.getGamestate() == 2){


                textfeld.setText(Text.CharakterMensch);
                hintergrund.setIcon(new ImageIcon(images.CharakterHeld));
                btnWeiter.setText("Weiter");
                changeButtonVisibility(false,false,false,false,true);

            }

            //Entschidung tür
            if(gamemanager.getGamestate() == 8){

                textfeld.setText(Text.Chap1_Auswahl3);
                //hintergrund.setIcon(new ImageIcon(images.TuerHuenchenPaketZu));
                changeButtonVisibility(false,false,false,false,true);
                tuerEntscheidung = 3;

            }

            //Entscheidung Zocken
            if(gamemanager.getGamestate() == 12){

                textfeld.setText(Text.Chap3_Auswahl3);
                changeButtonVisibility(true,false,false,false,true);

            }

            //Entscheidung Auto ( nur Btn 2 führt zum Weiterlaufen des Spiels)
            if(gamemanager.getGamestate() == 11){


                textfeld.setText(Text.Chap3_Auswahl3);
                gamemanager.setGamestate (10);
                btnWeiter.setText("Na gut, dann nehme ich...");
                changeButtonVisibility(false,false,false,false,true);

            }

            //Entscheidung Langeweile
            if(gamemanager.getGamestate()== 15){

                textfeld.setText(Text.Chap4_Auswahl3);
                changeButtonVisibility(false,false,false,false,true);
            }

            //Entscheidung Flucht
            if(gamemanager.getGamestate()== 17){

                textfeld.setText(Text.Chap5_Auswahl2);
                changeButtonVisibility(false,false,false,false,true);
            }







        }else if(e.getSource()==btn4){


            if(gamemanager.getGamestate() == 8){

                textfeld.setText(Text.Chap1_Auswahl4);
                //hintergrund.setIcon(new ImageIcon(images.TuerBaelleZU));
                changeButtonVisibility(false,false,false,false,true);
                tuerEntscheidung = 4;

            }

            //Entscheidung Auto ( nur Bobbycar(btn 2) führt zum Weiterlaufen des Spiels)
            if(gamemanager.getGamestate() == 11){

                textfeld.setText(Text.Chap3_Auswahl4);
                gamemanager.setGamestate (10);
                btnWeiter.setText("Na gut, dann nehme ich...");
                changeButtonVisibility(false,false,false,false,true);

            }

            //Entscheidung Langeweile
            if(gamemanager.getGamestate()== 15){

                textfeld.setText(Text.Chap4_Auswahl4);
                changeButtonVisibility(false,false,false,false,true);
            }

            //Entscheidung Flucht
            if(gamemanager.getGamestate()== 17){

                textfeld.setText(Text.Chap5_Auswahl2);
                changeButtonVisibility(false,false,false,false,true);
            }

        }


    }



}



    
