package fr.swingy.rpg.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.swingy.rpg.model.artefacts.Artefact;
import fr.swingy.rpg.model.artefacts.Attack;
import fr.swingy.rpg.model.artefacts.Health;
import fr.swingy.rpg.model.artefacts.Relic;
import fr.swingy.rpg.model.artefacts.Shield;
import fr.swingy.rpg.model.dto.PlayerViewData;
import fr.swingy.rpg.model.entity.Berserker;
import fr.swingy.rpg.model.entity.Mage;
import fr.swingy.rpg.model.entity.Paladin;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Rogue;
import fr.swingy.rpg.model.entity.Warrior;

public class HeroDAO
{
	private final Connection conn;

	public HeroDAO(Connection conn)
	{
		this.conn = conn;
	}

	public void saveHero(Player hero) throws SQLException 
	{
		String sql = "INSERT INTO heroes(name, heroClass, level, xp, xpMax, hp, hpMax, attack, defence, artefactName, artefactLvl) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		int	hpMax = hero.getHpMax();
		int attack = hero.getAttack();
		int defence = hero.getDefence();
		String artefactName = null;
		int	artefactLvl = 0;

		if (hero.getArtefact() != null)
		{
			hpMax -= hero.getArtefact().getHpBonus();
			attack -= hero.getArtefact().getAttackBonus();
			defence -= hero.getArtefact().getDefenceBonus();
			artefactName = hero.getArtefact().getName();
			artefactLvl = hero.getArtefact().getlvl();
		}

		pstmt.setString(1, hero.getName());
		pstmt.setString(2, hero.getClassName());
		pstmt.setInt(3, hero.getLvl());
		pstmt.setInt(4, hero.getXp());
		pstmt.setInt(5, hero.getXpMax());
		pstmt.setInt(6, hero.getHp());
		pstmt.setInt(7, hpMax);
		pstmt.setInt(8, attack);
		pstmt.setInt(9, defence);
		pstmt.setString(10, artefactName);
		pstmt.setInt(11, artefactLvl);

		pstmt.executeUpdate();
	}

	public List<PlayerViewData> loadHeroes() throws SQLException
	{
		List<PlayerViewData> heroes = new ArrayList<>();

		String sql = "SELECT * FROM heroes ORDER BY level";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())
		{
			PlayerViewData hero = new PlayerViewData
			(
				rs.getString("name"),
				rs.getInt("level"),
				0,
				rs.getInt("hp"),
				0,
				rs.getInt("xp"),
				rs.getInt("defence"),
				rs.getInt("attack"),
				rs.getString("heroClass"),
				null,
				0,
				rs.getInt("id")
			);
			heroes.add(hero);
		}
		return heroes;
	}

    public void deleteHero(int id) throws SQLException
	{

		String sql = "DELETE FROM heroes WHERE id=?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ps.executeUpdate();
	}

    public int countHeroes() throws SQLException
	{

		String sql = "SELECT COUNT(*) FROM heroes";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return rs.getInt(1);
	}

	public Player getPlayer(int id) throws SQLException
	{

		String sql = "SELECT * FROM heroes WHERE id=?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		Player player;
		String name = rs.getString("name");

		switch (rs.getString("heroClass"))
		{
			case "Warrior" -> player = new Warrior(name);
			case "Mage" -> player = new Mage(name);
			case "Rogue" -> player = new Rogue(name);
			case "Paladin" -> player = new Paladin(name);
			case "Berserker" -> player = new Berserker(name);
			default -> player = new Berserker(name);
		}

		player.setHpMax(rs.getInt("hpMax"));
		player.setXp(rs.getInt("xp"));
		player.setLvl(rs.getInt("level"));
		player.setDefence(rs.getInt("defence"));
		player.setAttack(rs.getInt("attack"));
		if (rs.getString("artefactName") != null)
		{
			Artefact artefact;
			int lvl = rs.getInt("artefactLvl");

			switch (rs.getString("artefactName"))
			{
				case "⚔️" -> artefact = new Attack(lvl, "⚔️");
				case "❤️" -> artefact = new Health(lvl, "❤️");
				case "⛨" -> artefact = new Shield(lvl, "⛨");
				case "💎" -> artefact = new Relic(lvl, "💎");
				default -> artefact = new Relic(lvl, "💎");
			}
			player.setArtefact(artefact);
			player.setHp(rs.getInt("hp"));
		}
		return player;
	}
}