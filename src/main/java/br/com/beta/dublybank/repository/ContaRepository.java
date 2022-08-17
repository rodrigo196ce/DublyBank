package br.com.beta.dublybank.repository;

import br.com.beta.dublybank.model.Conta;
import br.com.beta.dublybank.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta,Integer> {
}
