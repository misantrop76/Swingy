package fr.swingy.rpg.model.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import fr.swingy.rpg.model.artefacts.Artefact;

public abstract class Character
{
	@Min(value = 0, message = "previousHp connot be negatif")
	protected int previousHp;

	@Size(max=10, message = "name is too long")
	@NotBlank(message = "name cannot be blank")
	protected String name;
	@Min(value = 0, message = "hp cannot be negatif")
	protected int hp;
	@Min(value = 0, message = "attack cannot be negatif")
	protected int attack;
	@Min(value = 0, message = "defence cannot be negatif")
	protected int defence;
	@Min(value = 0, message = "pos cannot be negatif")
	protected int pos;
	protected Artefact artefact;

	public Character(String name, int hp, int attack, int defence)
	{
		this.previousHp = hp;
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.artefact = null;
	}

	public boolean isAlive()
	{
		return hp > 0;
	}

	public void setArtefact(Artefact artefact)
	{
		this.artefact = artefact;
	}

	public Artefact getArtefact()
	{
		return (this.artefact);
	}

	public String getName()
	{
		return (this.name);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPos(int pos)
	{
		this.pos = pos;
	}

	public int getPos()
	{
		return (this.pos);
	}

	public int getHp()
	{
		return (this.hp);
	}

	public void setHp(int hp)
	{
		this.hp = hp;
		if (hp < 0)
			this.hp = 0;
	}

	public void setPreviousHp()
	{
		previousHp = hp;
	}

	public int getPreviousHp()
	{
		return (this.previousHp);
	}

	public int getAttack()
	{
		return (this.attack);
	}

	public int getDefence()
	{
		return (this.defence);
	}
}
