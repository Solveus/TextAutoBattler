package textAutoBattler;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Application {
    private String appTitle = "Text AutoBattler";
    private static final int WIDTH = 1280;
    private static final int HEIGTH = 720;

    // game fields
    private Game game;
    private GameCharacter characterOne;
    private GameCharacter characterTwo;

    private Font fontSansBold13;

    // indexes selected indexes from ComboBox
    private int characterOneIndex = 0;
    private int weaponOneIndex    = 0;
    private int characterTwoIndex = 0;
    private int weaponTwoIndex    = 0;

    private JFrame frame;
    // panels
    private JPanel mainPane;
    private JPanel firstPane;
    private JScrollPane secondPane;
    private JTextArea textArea;
    private JSeparator separator;
    // combo boxes
    private JComboBox<String> characterOneComboBox;
    private JComboBox<String> weaponOneComboBox;
    private JComboBox<String> characterTwoComboBox;
    private JComboBox<String> weaponTwoComboBox;
    // characters fields
    private JLabel labelChoosePlayer;
    private JLabel labelPlayerName;
    private JLabel labelPlayerHealth;
    private JLabel labelPlayerAttack;
    private JLabel labelPlayerMagic;
    private JLabel playerOneName;
    private JLabel playerOneHealth;
    private JLabel playerOneAttack;
    private JLabel playerOneMagic;
    private JLabel playerTwoName;
    private JLabel playerTwoHealth;
    private JLabel playerTwoAttack;
    private JLabel playerTwoMagic;
    // weapon fields
    private JLabel labelChooseWeapon;
    private JLabel labelWeaponName;
    private JLabel labelWeaponQuality;
    private JLabel labelWeaponType;
    private JLabel labelWeaponPhysicDamage;
    private JLabel labelWeaponMagicDamage;
    private JLabel weaponOneName;
    private JLabel weaponOneQuality;
    private JLabel weaponOneType;
    private JLabel weaponOnePhysicDamage;
    private JLabel weaponOneMagicDamage;
    private JLabel weaponTwoName;
    private JLabel weaponTwoQuality;
    private JLabel weaponTwoType;
    private JLabel weaponTwoPhysicDamage;
    private JLabel weaponTwoMagicDamage;
    // button and menu
    private JButton btnStartBattle;
    private JButton btnClear;

    private JMenu menuFile;
    private JMenuBar menuBar;

    private JMenuItem itemFileExit;
    private JMenuItem itemFileClearLog;


    Application() {
        game = new Game();
        createGUI();
    }

    private JMenuBar createMenu() {
        menuBar = new JMenuBar();

        menuFile = new JMenu("File");
        menuFile.setFont(fontSansBold13);

        itemFileExit = new JMenuItem("Exit");
        itemFileClearLog = new JMenuItem("Clear log");

        menuFile.add(itemFileClearLog);
        menuFile.add(itemFileExit);

        menuBar.add(menuFile);

        return menuBar;
    }

    private void createGUI() {
        /// frame ///
        frame = new JFrame(appTitle);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(WIDTH, HEIGTH));

        frame.setResizable(false);

        /// utilits ///
        separator = new JSeparator();
        fontSansBold13 = new Font(Font.DIALOG, Font.BOLD, 13);

        /// main pane ///
        mainPane = new JPanel(new MigLayout());
        mainPane.setBackground(Color.lightGray);

        /// left  pane ///
        firstPane = new JPanel(new MigLayout("fillx"));
        firstPane.setBackground(Color.WHITE);
        firstPane.setPreferredSize(new Dimension(WIDTH/2, HEIGTH));

        // player 1
        labelChoosePlayer   = new JLabel(TextMessages.getChooseText("character 1"));
        labelChoosePlayer.setFont(fontSansBold13);

        initLabels();

        characterOneComboBox = new JComboBox<>(game.getNamesList(game.getGameCharacterList()));
        characterOneComboBox.setSelectedIndex(0);

        playerOneName   = new JLabel (game.getGameCharacterList()[0].getName());
        playerOneHealth = new JLabel(String.valueOf(game.getGameCharacterList()[0].getMaxHealth()));
        playerOneAttack = new JLabel(String.valueOf(game.getGameCharacterList()[0].getAttackPower()));
        playerOneMagic  = new JLabel(String.valueOf(game.getGameCharacterList()[0].getMagicPower()));

        firstPane.add(labelChoosePlayer, "");
        firstPane.add(characterOneComboBox, "span, grow");

        firstPane.add(labelPlayerName);
        firstPane.add(playerOneName, "wrap");

        firstPane.add(labelPlayerHealth);
        firstPane.add(playerOneHealth, "wrap");

        firstPane.add(labelPlayerAttack);
        firstPane.add(playerOneAttack, "wrap");

        firstPane.add(labelPlayerMagic);
        firstPane.add(playerOneMagic, "wrap");

        firstPane.add(separator, "span");

        // weapon
        labelChooseWeapon = new JLabel(TextMessages.getChooseText("weapon 1"));
        labelChooseWeapon.setFont(fontSansBold13);

        weaponOneComboBox = new JComboBox<>(game.getNamesList(game.getWeaponList()));
        weaponOneComboBox.setSelectedIndex(0);

        weaponOneName = new JLabel(game.getWeaponList()[0].getName());
        weaponOneQuality = new JLabel(String.valueOf(game.getWeaponList()[0].getQuality()));
        weaponOneType = new JLabel(String.valueOf(game.getWeaponList()[0].getType()));
        weaponOnePhysicDamage = new JLabel(String.valueOf(game.getWeaponList()[0].getPhysicDamage()));
        weaponOneMagicDamage = new JLabel(String.valueOf(game.getWeaponList()[0].getMagicDamage()));

        firstPane.add(labelChooseWeapon, "newline");
        firstPane.add(weaponOneComboBox, "span, grow");

        firstPane.add(labelWeaponName,"");
        firstPane.add(weaponOneName, "wrap");

        firstPane.add(labelWeaponQuality,"");
        firstPane.add(weaponOneQuality, "wrap");

        firstPane.add(labelWeaponType,"");
        firstPane.add(weaponOneType, "wrap");

        firstPane.add(labelWeaponPhysicDamage,"");
        firstPane.add(weaponOnePhysicDamage, "wrap");

        firstPane.add(labelWeaponMagicDamage, "");
        firstPane.add(weaponOneMagicDamage, "wrap");

        firstPane.add(new JSeparator(), "span, grow");

        // player 2
        labelChoosePlayer    = new JLabel(TextMessages.getChooseText("character 2"));
        labelChoosePlayer.setFont(fontSansBold13);

        initLabels();

        characterTwoComboBox = new JComboBox<>(game.getNamesList(game.getGameCharacterList()));
        characterTwoComboBox.setSelectedIndex(0);
        characterOneComboBox.setBackground(Color.LIGHT_GRAY);

        playerTwoName        = new JLabel (game.getGameCharacterList()[0].getName());
        playerTwoHealth      = new JLabel(String.valueOf(game.getGameCharacterList()[0].getMaxHealth()));
        playerTwoAttack      = new JLabel(String.valueOf(game.getGameCharacterList()[0].getAttackPower()));
        playerTwoMagic       = new JLabel(String.valueOf(game.getGameCharacterList()[0].getMagicPower()));

        firstPane.add(labelChoosePlayer, "");
        firstPane.add(characterTwoComboBox, "span, grow");

        firstPane.add(labelPlayerName, "");
        firstPane.add(playerTwoName, "wrap");

        firstPane.add(labelPlayerHealth, "");
        firstPane.add(playerTwoHealth, "wrap");

        firstPane.add(labelPlayerAttack, "");
        firstPane.add(playerTwoAttack, "wrap");

        firstPane.add(labelPlayerMagic, "");
        firstPane.add(playerTwoMagic, "wrap");

        firstPane.add(separator, "span");

        // weapon
        labelChooseWeapon = new JLabel(TextMessages.getChooseText("weapon 2"));
        labelChooseWeapon.setFont(fontSansBold13);

        weaponTwoComboBox = new JComboBox<>(game.getNamesList(game.getWeaponList()));
        weaponTwoComboBox.setSelectedIndex(0);

        weaponTwoName            = new JLabel(game.getWeaponList()[0].getName());
        weaponTwoQuality         = new JLabel(String.valueOf(game.getWeaponList()[0].getQuality()));
        weaponTwoType            = new JLabel(String.valueOf(game.getWeaponList()[0].getType()));
        weaponTwoPhysicDamage    = new JLabel(String.valueOf(game.getWeaponList()[0].getPhysicDamage()));
        weaponTwoMagicDamage     = new JLabel(String.valueOf(game.getWeaponList()[0].getMagicDamage()));

        firstPane.add(labelChooseWeapon, "newline");
        firstPane.add(weaponTwoComboBox, "span, grow");

        firstPane.add(labelWeaponName,"");
        firstPane.add(weaponTwoName, "wrap");

        firstPane.add(labelWeaponQuality,"");
        firstPane.add(weaponTwoQuality, "wrap");

        firstPane.add(labelWeaponType,"");
        firstPane.add(weaponTwoType, "wrap");

        firstPane.add(labelWeaponPhysicDamage,"");
        firstPane.add(weaponTwoPhysicDamage, "wrap");

        firstPane.add(labelWeaponMagicDamage, "");
        firstPane.add(weaponTwoMagicDamage, "wrap");

        firstPane.add(new JSeparator(), "span");

        // buttons //
        btnStartBattle = new JButton("Start Battle!");
        btnStartBattle.setBorderPainted(false);
        btnStartBattle.setFocusPainted(false);
        btnStartBattle.setBackground(Color.LIGHT_GRAY);

        btnClear = new JButton("Clear log");
        btnClear.setBorderPainted(false);
        btnClear.setFocusPainted(false);
        btnClear.setBackground(Color.LIGHT_GRAY);


        firstPane.add(separator, "span");
        firstPane.add(btnStartBattle, "grow");
        firstPane.add(btnClear, "span, grow");

        /// right pane ///
        textArea = new JTextArea();
        textArea.setFont(new Font( Font.DIALOG, Font.PLAIN, 15));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(setPadding(5));
        textArea.setEditable(false);

        secondPane = new JScrollPane(textArea);
        secondPane.setBackground(Color.ORANGE);
        secondPane.setPreferredSize(new Dimension(WIDTH/2, HEIGTH));
        secondPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        secondPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        mainPane.add(firstPane, "");
        mainPane.add(secondPane, "dock east");

        frame.setJMenuBar(createMenu());
        frame.setContentPane(mainPane);

        addListeners();

        frame.setVisible(true);
    }

    private void initLabels() {
        labelPlayerName         = new JLabel("Player name:");
        labelPlayerHealth       = new JLabel("Player health:");
        labelPlayerAttack       = new JLabel("Player attack: ");
        labelPlayerMagic        = new JLabel("Player magic: ");
        labelWeaponName         = new JLabel("Weapon name: ");
        labelWeaponQuality      = new JLabel("Weapon quality: ");
        labelWeaponType         = new JLabel("Weapon type: ");
        labelWeaponPhysicDamage = new JLabel("Weapon physic damage: ");
        labelWeaponMagicDamage  = new JLabel("Weapon magic damage: ");
    }

    private void addListeners() {
        // buttons
        btnStartBattle.addActionListener(e -> startBattle());
        btnClear.addActionListener(e -> clearLog());

        // menu
        itemFileExit.addActionListener(e -> System.exit(0));
        itemFileClearLog.addActionListener(e -> clearLog());

        // combo boxes
        characterOneComboBox.addActionListener(e -> {
            characterOneIndex = characterOneComboBox.getSelectedIndex();

            playerOneName.setText(game.getGameCharacterList()[characterOneIndex].getName());
            playerOneHealth.setText(String.valueOf(game.getGameCharacterList()[characterOneIndex].getMaxHealth()));
            playerOneAttack.setText(String.valueOf(game.getGameCharacterList()[characterOneIndex].getAttackPower()));

        });

        weaponOneComboBox.addActionListener(e -> {
            weaponOneIndex = weaponOneComboBox.getSelectedIndex();

            weaponOneName.setText(game.getWeaponList()[weaponOneIndex].getName());
            weaponOneQuality.setText(String.valueOf(game.getWeaponList()[weaponOneIndex].getQuality()));
            weaponOneType.setText(String.valueOf(game.getWeaponList()[weaponOneIndex].getType()));
            weaponOnePhysicDamage.setText(String.valueOf(game.getWeaponList()[weaponOneIndex].getPhysicDamage()));
            weaponOneMagicDamage.setText(String.valueOf(game.getWeaponList()[weaponOneIndex].getMagicDamage()));

        });

        characterTwoComboBox.addActionListener(e -> {
            characterTwoIndex = characterTwoComboBox.getSelectedIndex();

            playerTwoName.setText(game.getGameCharacterList()[characterTwoIndex].getName());
            playerTwoHealth.setText(String.valueOf(game.getGameCharacterList()[characterTwoIndex].getMaxHealth()));
            playerTwoAttack.setText(String.valueOf(game.getGameCharacterList()[characterTwoIndex].getAttackPower()));
        });

        weaponTwoComboBox.addActionListener(e -> {
            weaponTwoIndex = weaponTwoComboBox.getSelectedIndex();

            weaponTwoName.setText(game.getWeaponList()[weaponTwoIndex].getName());
            weaponTwoQuality.setText(String.valueOf(game.getWeaponList()[weaponTwoIndex].getQuality()));
            weaponTwoType.setText(String.valueOf(game.getWeaponList()[weaponTwoIndex].getType()));
            weaponTwoPhysicDamage.setText(String.valueOf(game.getWeaponList()[weaponTwoIndex].getPhysicDamage()));
            weaponTwoMagicDamage.setText(String.valueOf(game.getWeaponList()[weaponTwoIndex].getMagicDamage()));
        });
    }

    private void clearLog() {
        textArea.setText("");
    }

    private EmptyBorder setPadding(int px) {
        if (px < 0 ) px = 0;
        return new EmptyBorder(px, px, px, px);
    }
    private void startBattle() {
        // init character 1
        game.setGameCharacterOne((game.getGameCharacterList()[characterOneIndex]));
        characterOne = game.getGameCharacterOne();
        characterOne.setWeapon(game.getWeaponList()[weaponOneIndex]);
        characterOne.resurrection();

        // init character 2
        game.setGameCharacterTwo((game.getGameCharacterList()[characterTwoIndex]));
        characterTwo = game.getGameCharacterTwo();
        characterTwo.setWeapon(game.getWeaponList()[weaponTwoIndex]);
        characterTwo.resurrection();

        // print combat logs
        textArea.append((characterOne.getName() + " VS " + characterTwo.getName() + "\n"));
        textArea.append((Game.battle(characterOne, characterTwo)));
    }



    public static void main(String[] args) {

        Application app = new Application();
    }
}
