package fr.swingy.rpg.model.dto;

public class FightUpdateView
{
    public final boolean isPlayerAttacking;
    public final boolean isCritical;
    public final int damage;
    public final int playerHp;
    public final int enemyHp;

    public FightUpdateView(boolean isPlayerAttacking, boolean isCritical, int damage, int playerHp, int enemyHp)
    {
        this.isPlayerAttacking = isPlayerAttacking;
        this.isCritical = isCritical;
        this.damage = damage;
        this.playerHp = playerHp;
        this.enemyHp = enemyHp;
    }
}