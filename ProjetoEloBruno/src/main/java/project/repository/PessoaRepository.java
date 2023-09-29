package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	List<Pessoa> findAll();
}
