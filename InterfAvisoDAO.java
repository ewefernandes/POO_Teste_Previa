package edu.curso.dao;

import java.util.List;

import edu.curso.model.Aviso;

public interface InterfAvisoDAO {

	public void salvar(Aviso aviso);
	public List<Aviso> lerTodos();
	public List<Aviso> pesquisarPorNome(String titulo);
}
