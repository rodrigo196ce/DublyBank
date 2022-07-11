package br.com.beta.dublybank.repository;

import br.com.beta.dublybank.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Integer> {

}
