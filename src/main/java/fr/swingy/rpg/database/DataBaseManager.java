package fr.swingy.rpg.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager
{
	private static final String URL = "jdbc:sqlite:swingy.db";

	public static Connection createDataBase()
	{
		try
		{
			Connection conn = DriverManager.getConnection(URL);
			String sql = "CREATE TABLE IF NOT EXISTS heroes ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT,"
				+ "heroClass TEXT,"
				+ "level INTEGER,"
				+ "xp INTEGER,"
				+ "xpMax INTEGER,"
				+ "hp INTEGER,"
				+ "hpMax INTEGER,"
				+ "attack INTEGER,"
				+ "defence INTEGER,"
				+ "artefactName STRING,"
				+ "artefactLvl INTEGER"
				+ ");";

			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			return (conn);
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}
}