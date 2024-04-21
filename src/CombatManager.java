import java.io.Serializable;

public class CombatManager implements Serializable {
    private static final long serialVersionUID = 1L; 
    private Player player;
    private Monster currentMonster;

    public CombatManager(Player player) {
        this.player = player;
    }

    public void engageMonster(Monster monster) {
        this.currentMonster = monster;
        System.out.println("Combat started between " + player.getStatus() + " and " + monster.getDescription());

        while (!monster.isDefeated() && !player.isDefeated()) {
            playerAttack();
            if (monster.isDefeated()) {
                handleMonsterDefeat();
                break;
            }
            monsterAttack();
        }
        if (player.isDefeated()) {
            System.out.println("Player has been defeated by " + monster.getName());
            handlePlayerDefeat();
        }
    }

    private void playerAttack() {
        int damageDealt = player.calculateDamage(); // Calculate damage based on player's stats and equipment
        currentMonster.takeDamage(damageDealt);
        System.out.println("Player deals " + damageDealt + " damage to " + currentMonster.getName());
    }

    private void monsterAttack() {
        int damageReceived = currentMonster.getAttack();
        player.takeDamage(damageReceived);
        System.out.println(currentMonster.getName() + " attacks! Player takes " + damageReceived + " damage.");
    }

    private void handleMonsterDefeat() {
        System.out.println("Monster " + currentMonster.getName() + " defeated");
        player.gainExperience(currentMonster.getExp());
        // Handle additional rewards or next steps after defeating the monster
    }

    private void handlePlayerDefeat() {
        // Handle player defeat (e.g., game over, respawn, etc.)
    }

    public void equipWeapon(Equipment weapon) {
        if (weapon != null && weapon.isEquipable()) {
            if (player.getEquippedWeapon() != null) {
                unequipWeapon();  // If already equipped, unequip current before equipping new one
            }
            player.setEquippedWeapon(weapon);
            applyWeaponEffects(weapon);
            System.out.println(weapon.getName() + " has been equipped.");
        } else {
            System.out.println("This item cannot be equipped as a weapon.");
        }
    }
    
    public void unequipWeapon() {
        Equipment weapon = player.getEquippedWeapon();
        if (weapon != null) {
            removeWeaponEffects(weapon);
            player.setEquippedWeapon(null);
            System.out.println(weapon.getName() + " has been unequipped.");
        }
    }
    
    
    private void applyWeaponEffects(Equipment weapon) {
        // Increase attack power or other stats
        player.adjustAttackPower(weapon.getAttackIncrease());
    }

    private void removeWeaponEffects(Equipment weapon) {
        // Decrease attack power or revert other stats
        player.adjustAttackPower(-weapon.getAttackIncrease());
    }
}
