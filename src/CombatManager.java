public class CombatManager {
    private Player player;

    public CombatManager(Player player) {
        this.player = player;
    }

    /**
     * Initiates combat between the player and a specific monster.
     * @param monster The monster that the player will engage.
     */
    public void engageMonster(Monster monster) {
        System.out.println("Combat started between " + player.getStatus() + " and " + monster.getDescription());
        while (!monster.isDefeated() && !player.isDefeated()) {
            playerAttack(monster);
            if (monster.isDefeated()) {
                break;
            }
            monsterAttack(monster);
        }
        if (player.isDefeated()) {
            System.out.println("Player has been defeated by " + monster.getName());
            // Handle player defeat (e.g., game over, respawn, etc.)
        } else {
            System.out.println("Monster " + monster.getName() + " defeated");
            player.gainExperience(monster.getExp()); // Assume monsters give experience
            // Handle additional rewards or next steps after defeating the monster
        }
    }

    /**
     * Handles the player attacking the monster.
     * @param monster The monster being attacked.
     */
    private void playerAttack(Monster monster) {
        int damageDealt = player.calculateDamage(); // This method should consider equipped items, buffs, etc.
        monster.takeDamage(damageDealt);
        System.out.println("Player deals " + damageDealt + " damage to " + monster.getName());
        System.out.println(monster.getName() + " HP left: " + monster.getHp());
    }

    /**
     * Handles the monster attacking the player.
     * @param monster The attacking monster.
     */
    private void monsterAttack(Monster monster) {
        int damageReceived = monster.getAttack();
        player.takeDamage(damageReceived);
        System.out.println(monster.getName() + " attacks! Player takes " + damageReceived + " damage.");
        System.out.println("Player HP left: " + player.getCurrentHealth());
    }
}