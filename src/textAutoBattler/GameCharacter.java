package textAutoBattler;

import static textAutoBattler.Utilities.TAB;

public class GameCharacter {

    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int magicPower;
    private Weapon weapon;
    private boolean alive;


    GameCharacter(String name, int health, int attackPower, int magicPower, boolean alive, Weapon weapon) {
        setName(name);
        setHealth(health);
        setAttackPower(attackPower);
        setAlive(alive);
        setWeapon(weapon);
        setMaxHealth(health);
    }

    public void setName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("Uncorrect name!");
        }
        this.name = name;
    }

    public void setAttackPower(int attackPower) {
        if (attackPower < 0){
            this.attackPower = 0;
        }
        this.attackPower = attackPower;
    }

    public void setHealth(int health) {
        if (health < 0 )
            health = 0;

        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        if (maxHealth < 0 )
            maxHealth = 0;
        this.maxHealth = maxHealth;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMagicPower() { return magicPower; }

    public int getMaxHealth() { return maxHealth; }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() { return weapon; }

    public String getName() {
        return name;
    }

    public Boolean getAlive() {
        return alive;
    }


    // random only players attack power
    private static int damageRand(int damage) {
        //damage dispersion is +- 20% of attack power
        int dispersion = (damage / 5);
        int min = damage - dispersion;
        int max = (damage + dispersion) - min;
        return (int) ((Math.random() * ++max) + min); // number in range [min; max]
    }

    public void heal(double percent) {
        // Input double as percent, 0.1 = 10%
        if (percent > 1) percent = 1;
        if (percent < 0) percent = 0;

        setHealth((int) (health + maxHealth * percent));
    }

    public void resurrection() {
        alive = true;
        setHealth(maxHealth);
    }

    public static String attack(GameCharacter attacker, GameCharacter target) {

        String log = "";

        if (target.getHealth() <= 0) {
            target.setAlive(false);
            return log;
        }

        if (attacker.getHealth() <= 0) {
            attacker.setAlive(false);
            return log;
        }

        int damage;
        String typeOfDamage;
        double weaponQualityBuff = Weapon.getQualityBuff()[attacker.weapon.getQuality().ordinal()];

        // if mage damage > physic damage
        if ((attacker.magicPower + attacker.weapon.getMagicDamage())
                > attacker.attackPower + attacker.weapon.getPhysicDamage()) {

            damage = (int) (damageRand(attacker.magicPower) + attacker.weapon.getMagicDamage() * weaponQualityBuff);
            typeOfDamage = "magic";
        } else {

            damage = (int) (damageRand(attacker.attackPower) + attacker.weapon.getPhysicDamage() * weaponQualityBuff);
            typeOfDamage = "physic";
        }
        // if attackPower == magicPower can add armor with physic and magic resists and choose higher damage

        target.setHealth(target.getHealth()-damage);
        log = (TAB + attacker.getName() + " make " + damage + " damages " + target.getName() + ". Type of damage " + typeOfDamage);

        return log;
    }

}