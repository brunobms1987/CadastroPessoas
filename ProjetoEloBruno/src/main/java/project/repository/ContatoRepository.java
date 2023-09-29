package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
	List<Contato> findAll();
}
