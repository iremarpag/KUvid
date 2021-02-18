package UI.BuildWindow;

import Domain.GameController;
import UI.GameScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameBuild {
    private int atom = 100;
    private int molecule = 100;
    private int reactionBlockers = 10;
    private int powerups = 20;
    private int L = 10;
    private boolean isNormal = true;
    private boolean isSpinning = false;

    public GameBuild(int atom, int molecule, int reactionBlockers, int powerups, int L) {
        this.atom = atom;
        this.molecule = molecule;
        this.reactionBlockers = reactionBlockers;
        this.powerups = powerups;
        this.L = L;
    }



    public void createGameWindow() {
//Creating Frame
        JFrame f = new JFrame("KUVID");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400,400);




        JPanel difficultyPanel = new JPanel();

        ButtonGroup diff = new ButtonGroup();
        JRadioButton r1 = new JRadioButton("Easy");
        JRadioButton r2 = new JRadioButton("Medium");
        JRadioButton r3 = new JRadioButton("Hard");
        diff.add(r1);
        diff.add(r2);
        diff.add(r3);
        difficultyPanel.add(r1);
        difficultyPanel.add(r2);
        difficultyPanel.add(r3);
        r2.setSelected(true);
        difficultyPanel.setVisible(false);

        JPanel structurePanel = new JPanel();

        ButtonGroup struct = new ButtonGroup();
        JRadioButton linear = new JRadioButton("düz");
        JRadioButton normal = new JRadioButton("düz değil");
        struct.add(linear);
        struct.add(normal);
        structurePanel.add(linear);
        structurePanel.add(normal);
        normal.setSelected(true);
        difficultyPanel.setVisible(false);

        ButtonGroup movement = new ButtonGroup();
        JRadioButton m1 = new JRadioButton("spinning");
        JRadioButton m2 = new JRadioButton("stationary");
        movement.add(m1);
        movement.add(m2);
        structurePanel.add(m1);
        structurePanel.add(m2);
        structurePanel.setVisible(false);
        m1.setEnabled(false);
        m2.setEnabled(false);

        linear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m1.setEnabled(true);
                m2.setEnabled(true);
            }});

        normal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m1.setEnabled(false);
                m2.setEnabled(false);
            }});


        JPanel panelL = new JPanel();
        JLabel labelL = new JLabel("L:                           ");
        JTextField textL = new JTextField(6);
        textL.setText("10");
        panelL.add(labelL);
        panelL.add(textL);
        panelL.setVisible(false);

//panel for atom input
        JPanel panelAtom = new JPanel();
        JLabel labelAtom = new JLabel("Atoms:                     ");
        JTextField textAtom = new JTextField(6);
        textAtom.setText("100");
        panelAtom.add(labelAtom);
        panelAtom.add(textAtom);
        panelAtom.setVisible(false);



//panel for molecule input
        JPanel panelMolecule = new JPanel();
        JLabel labelMolecule = new JLabel("Molecules:               ");
        JTextField textMolecule = new JTextField(6);
        textMolecule.setText("100");
        panelMolecule.add(labelMolecule);
        panelMolecule.add(textMolecule);
        panelMolecule.setVisible(false);

//panel for reactionBlockers input
        JPanel panelReactionBlockers = new JPanel();
        JLabel labelReactionBlockers = new JLabel("Reaction Blockers:  ");
        JTextField textReactionBlockers = new JTextField(6);
        textReactionBlockers.setText("10");
        panelReactionBlockers.add(labelReactionBlockers);
        panelReactionBlockers.add(textReactionBlockers);
        panelReactionBlockers.setVisible(false);

//panel for powerups input
        JPanel panelPowerups = new JPanel();
        JLabel labelPowerups = new JLabel("Powerups:                " + "");
        JTextField textPowerups = new JTextField(6);
        textPowerups.setText("20");
        panelPowerups.add(labelPowerups);
        panelPowerups.add(textPowerups);
        panelPowerups.setVisible(false);

        JPanel startPanel = new JPanel();
        JButton startButton = new JButton("Start Game");
        startPanel.add(startButton);
        startPanel.setVisible(false);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    atom = Integer.parseInt(textAtom.getText());
                    molecule = Integer.parseInt(textMolecule.getText());
                    reactionBlockers = Integer.parseInt(textReactionBlockers.getText());
                    powerups = Integer.parseInt(textPowerups.getText());
                    L = Integer.parseInt(textL.getText());
                    isNormal = normal.isSelected();
                    isSpinning = m1.isSelected();
                    GameScreen screen = new GameScreen();

                    screen.setL(L);
                    screen.setAtom(atom);
                    screen.setMolecule(molecule);
                    screen.setBlocker(reactionBlockers);
                    screen.setPowerup(powerups);
                    screen.setNormal(isNormal);
                    screen.setSpinning(isSpinning);
                    screen.run();

                }catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(null, " At least one of the inputs is not a number.");
                }

            }
        });

//panel for start button
        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load Game");
        buttonPanel.add(loadButton);


        JButton newGameButton = new JButton("New Game");
        buttonPanel.add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startPanel.setVisible(true);
                structurePanel.setVisible(true);
                panelAtom.setVisible(true);
                panelMolecule.setVisible(true);
                panelPowerups.setVisible(true);
                panelReactionBlockers.setVisible(true);
                difficultyPanel.setVisible(true);
                panelL.setVisible(true);
                buttonPanel.setVisible(false);

            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    GameScreen screen = new GameScreen();

                    screen.setL(10);
                    screen.setAtom(10);
                    screen.setMolecule(10);
                    screen.setBlocker(10);
                    screen.setPowerup(10);
                    screen.run();
                    screen.load = true;
                    screen.getGamePlay().game.load("save.txt");

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
                                     });




//Adding Panels
        f.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;

        f.add(difficultyPanel,gbc);
        f.add(structurePanel,gbc);
        f.add(panelL,gbc);
        f.add( panelAtom,gbc);
        f.add( panelMolecule,gbc);
        f.add( panelReactionBlockers,gbc);
        f.add( panelPowerups,gbc);
        f.add( buttonPanel,gbc);
        f.add(startPanel,gbc);
        f.setVisible(true);
    }
/*
private void checkInputs(String input, int i) {
try { i = Integer.parseInt(input);}
catch (NumberFormatException e) {
JOptionPane.showMessageDialog(null, ""+ i + " is not a number.");
}
}*/
    public int getAtom(){
        return atom;
    }

    public int getMolecule(){
        return molecule;
    }
    public int getPowerup(){
        return powerups;
    }
    public int getBlockers(){
        return reactionBlockers;
    }
    public int getL(){
        return L;
    }




}
