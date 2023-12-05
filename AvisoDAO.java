package edu.curso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.curso.model.Aviso;

public class AvisoDAO implements InterfAvisoDAO{

	public static final String JDBC_URL= "jdbc:mariadb://localhost:3306/aviso";
	public static final String JDBC_USER="root";
	private Connection con;
	
	public AvisoDAO() {
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvar(Aviso aviso) {
		
		String sql = "INSERT INTO avisoteste (tituloAviso, descricaoAviso, dataPublicacao) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, aviso.getTituloAviso());
			stmt.setString(2, aviso.getDescricaoAviso());
			stmt.setDate(3, Date.valueOf(aviso.getDataPublicacao()));
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Aviso> lerTodos() {
		return pesquisarPorNome("");
	}

	@Override
	public List<Aviso> pesquisarPorNome(String titulo) {
		
		List<Aviso> lista = new ArrayList<>();
		String sql = "SELECT * FROM avisoteste WHERE tituloAviso LIKE ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + titulo + "%");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) { 
				Aviso aviso = new Aviso();
				aviso.setTituloAviso(rs.getString("tituloAviso"));
				aviso.setDescricaoAviso(rs.getString("descricaoAviso"));
				lista.add(aviso);
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return lista;
	}

}
