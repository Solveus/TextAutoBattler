package textAutoBattler;

import static textAutoBattler.Utilities.TAB;

public class TextMessages {

    public static String getWinnerText(GameCharacter winner) {
        return (winner.getName() + " has win in this battle!");
    }

    public static String getRoundText(int number) {
        return ("Round " + number);
    }

    public static String getHealthText(GameCharacter player, GameCharacter enemy) {
        return(TAB + player.getName() + " health: " + player.getHealth() + "; " + enemy.getName() + " health: " + enemy.getHealth());
    }

    public static String getChooseText(String subject) {
        return ("Choose " + subject + ":");
    }

    public static String getYourPlayerText(int player, Game game) {
        if (player < 0 || player > game.getGameCharacterList().length)
            throw new IllegalArgumentException("Uncorrect index of player!");
        return ("Your characher is " + game.getGameCharacterList()[player].getName());
    }

    public static String getYourWeaponText(int weapon, Game game) {
        if (weapon < 0 || weapon > game.getWeaponList().length)
            throw new IllegalArgumentException("Uncorrect index of weapon!");
        return ("Your weapon is " + game.getWeaponList()[weapon].getName() + ".");
    }
}
