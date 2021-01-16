package textAutoBattler;

import java.util.Vector;

public class Game {

    private GameCharacter[] gameCharacterList;
    private Weapon[] weaponList;

    private GameCharacter gameCharacterOne;
    private GameCharacter gameCharacterTwo;

    private Weapon characterOneWeapon;
    private Weapon characterTwoWeapon;

    Game() {
        init();
    }

    private void init() {
        initWeapons(Utilities.parseFile((Game.class.getResourceAsStream(Utilities.resourcesPath + Utilities.weaponPath)),  Utilities.delimeter));
        initCharacters(Utilities.parseFile(((Game.class.getResourceAsStream(Utilities.resourcesPath + Utilities.charactersPath))), Utilities.delimeter));
    }

    public GameCharacter getGameCharacterOne() {
        return gameCharacterOne;
    }
    public GameCharacter getGameCharacterTwo() {
        return gameCharacterTwo;
    }

    public Weapon getCharacterTwoWeapon() { return characterTwoWeapon; }
    public Weapon getCharacterOneWeapon() { return characterOneWeapon; }

    public void setGameCharacterOne(GameCharacter gameCharacterOne) { this.gameCharacterOne = gameCharacterOne; }
    public void setGameCharacterTwo(GameCharacter gameCharacterTwo) { this.gameCharacterTwo = gameCharacterTwo; }

    public void setCharacterOneWeapon(Weapon characterOneWeapon) { this.characterOneWeapon = characterOneWeapon; }
    public void setCharacterTwoWeapon(Weapon characterTwoWeapon) { this.characterTwoWeapon = characterTwoWeapon; }

    public GameCharacter[] getGameCharacterList() { return gameCharacterList;  }
    public Weapon[] getWeaponList() { return weaponList; }

    // names for JComboBox
    public String[] getNamesList(Object[] list) {
        if (list == null) {
            throw new IllegalArgumentException("Object[] list can't be null!");
        }

        int count = list.length;
        int type;

        if (list instanceof GameCharacter[])
            type = 0;
        else if (list instanceof Weapon[])
            type = 1;
        else
            throw new IllegalArgumentException(list.getClass().getSimpleName() + " not allowed!");

        String[] names = new String[count];
        for (int i = 0; i < count; i++) {
            switch (type) {
                case 0 -> names[i] = gameCharacterList[i].getName();
                case 1 -> names[i] = weaponList[i].getName();
            }
        }
        return names;
    }

    // parse from file
    private void initWeapons(Vector<String[]> weapons) {
        int countWeapons = weapons.size();

        if (countWeapons == 0) {
            throw new IllegalArgumentException("Count weapons < 0");
        }
        weaponList = new Weapon[countWeapons];

        int i = 0;
        for (String[] weapon: weapons) {
            String name     = weapon[0];
            String quality  = weapon[1];
            String type     = weapon[2];
            int attackPower = Integer.parseInt(weapon[3]);
            int magicPower  = Integer.parseInt(weapon[4]);
            weaponList[i] = new Weapon(name, Weapon.qualityWeapon.valueOf(quality), Weapon.typeWeapon.valueOf(type), attackPower, magicPower);
            i++;
        }
    }
    // parse from file
    private void initCharacters(Vector<String[]> character) {
        int countCharacter = character.size();

        if (countCharacter == 0) {
            throw new IllegalArgumentException("Count character < 0");
        }
        gameCharacterList = new GameCharacter[countCharacter];

        int i = 0;
        for (String[] player: character) {
            String name      = player[0];
            int health       = Integer.parseInt(player[1]);
            int attackPower  = Integer.parseInt(player[2]);
            int magicPower   = Integer.parseInt(player[3]);
            gameCharacterList[i] = new GameCharacter(name, health, attackPower,magicPower, true, new Weapon());
            i++;
        }
    }

    public static String battle(GameCharacter oneGameCharacter, GameCharacter twoGameCharacter) {
        StringBuilder log = new StringBuilder();

        for (int i = 1; oneGameCharacter.getAlive() && twoGameCharacter.getAlive(); i++) {
            log.append(TextMessages.getRoundText(i)).append("\n");
            log.append(TextMessages.getHealthText(oneGameCharacter, twoGameCharacter)).append("\n");

            log.append(GameCharacter.attack(oneGameCharacter, twoGameCharacter)).append("\n");
            log.append(GameCharacter.attack(twoGameCharacter, oneGameCharacter)).append("\n");
        }
        if (oneGameCharacter.getAlive()) {
            log.append(TextMessages.getWinnerText(oneGameCharacter)).append("\n\n");
        } else {
            log.append(TextMessages.getWinnerText(twoGameCharacter)).append("\n\n");
        }
        return log.toString();
    }
}