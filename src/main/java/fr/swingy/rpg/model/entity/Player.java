package fr.swingy.rpg.model.entity;

import fr.swingy.rpg.model.entity.Character;

public abstract class Player extends Character
{
	protected int lvl;
	protected int xp;
	protected int prevPos;
	protected int hpMax;

	public Player (String name, int hp, int attack, int defence, String icon)
	{
		super(name, hp, attack, defence, icon);
		this.hpMax = hp;
		this.lvl = 1;
		this.xp = 0;
		this.artefact = null;
	}

	public void updateLvl()
	{
		this.xp -= this.getXpMax();
		this.hpMax += 10;
		this.hp = this.hpMax;
		this.defence++;
		this.attack++;
		this.lvl++;
	}

	@Override
	public int getDefence()
	{
		if (this.artefact != null)
			return (this.artefact.getDefenceBonus() + this.defence);
		return (this.defence);
	}

	@Override
	public int getAttack()
	{
		if (this.artefact != null)
			return (this.artefact.getAttackBonus() + this.defence);
		return (this.attack);
	}

	public int getHpMax()
	{
		if (this.artefact != null)
			return (this.hpMax + this.artefact.getHpBonus());
		return (this.hpMax);
	}

	public void setHpMax(int hpMax)
	{
		this.hpMax = hpMax;
	}

	public int getXpMax()
	{
		int lvl = this.lvl;
		return (lvl * 1000 + ((lvl - 1) * (lvl - 1)) * 450);
	}

	public int getXp()
	{
		return (this.xp);
	}

	public void setXp(int xp)
	{
		this.xp = xp;
	}

	public int getLvl()
	{
		return (this.lvl);
	}

	public void setLvl(int lvl)
	{
		this.lvl = lvl;
	}

	public void setPrevPos(int prevPos)
	{
		this.prevPos = prevPos;
	}

	public int getPrevPos()
	{
		return (this.prevPos);
	}

	public abstract String getClassName();
}
