package com.erikaskarda.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erikaskarda.cursomc.domain.Cidade;

@Repository 
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
