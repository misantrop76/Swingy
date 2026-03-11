package fr.swingy.rpg.model.entity;

import javax.validation.constraints.Min;

public abstract class Player extends Character
{
	@Min(value = 0, message = "lvl cannot be negatif")
	protected int lvl;
	@Min(value = 0, message = "xp cannot be negatif")
	protected int xp;
	@Min(value = 0, message = "prevPov cannot be negatif")
	protected int prevPos;
	@Min(value = 0, message = "hpMax cannot be negatif")
	protected int hpMax;

	public Player (String name, int hp, int attack, int defence)
	{
		super(name, hp, attack, defence);
		this.hpMax = hp;
		this.lvl = 1;
		this.xp = 0;
		this.artefact = null;
	}

	public void updateLvl()
	{
		this.xp -= this.getXpMax();
		this.hpMax += 10;
		this.hp = this.getHpMax();
		this.defence++;
		this.attack++;
		this.lvl++;
	}

	@Override
	public void setHp(int hp)
	{
		this.hp = hp;
		if (this.hp < 0)
			this.hp = 0;
		if (this.hp > this.getHpMax())
			this.hp = this.getHpMax();
	}

	@Override
	public int getDefence()
	{
		if (this.artefact != null)
			return (this.artefact.getDefenceBonus() + this.defence);
		return (this.defence);
	}

	public void setDefence(int defence)
	{
		this.defence = defence;
	}

	@Override
	public int getAttack()
	{
		if (this.artefact != null)
			return (this.artefact.getAttackBonus() + this.attack);
		return (this.attack);
	}

	public void setAttack(int attack)
	{
		this.attack = attack;
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
		if (this.hp > this.hpMax)
			this.hp = this.hpMax;
	}

	public int getXpMax()
	{
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
