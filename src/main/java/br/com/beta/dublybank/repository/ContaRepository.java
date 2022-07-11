package br.com.beta.dublybank.repository;

import br.com.beta.dublybank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta,Integer> {
}
