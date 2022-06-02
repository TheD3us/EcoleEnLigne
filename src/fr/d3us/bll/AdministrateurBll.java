package fr.d3us.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.d3us.bo.Administrateur;
import fr.d3us.dal.AdministrateurDAO;


@Service
public class AdministrateurBll {
	@Autowired
	private AdministrateurDAO dao;
	
	public List<Administrateur> selectAll() {
		return dao.findAll();
	}
	
	public Administrateur selectById(int id) {
		return dao.findById(id).get();
	}
	
	public void update(Administrateur a) {
		dao.save(a);
	}
	
	public void insert(Administrateur a) {
		dao.save(a);
	}
	
	public void delete(int id) {
		dao.deleteById(id);
	}
	
	public boolean exist(int id) {
		return dao.existsById(id);
	}
}
