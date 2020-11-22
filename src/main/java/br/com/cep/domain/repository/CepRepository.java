package br.com.cep.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cep.domain.model.Cep;

@Repository
public interface CepRepository extends JpaRepository<Cep, String> {

	@Query("select c from Cep c join fetch c.cidade ci where ci.ibge = ?1 and ci.uf = ?2")
	public List<Cep> findByCidade(String ibge, String uf);
		
}


