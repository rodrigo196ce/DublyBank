package br.com.beta.dublybank.repository;

import br.com.beta.dublybank.enums.StatusEmprestimo;
import br.com.beta.dublybank.model.Conta;
import br.com.beta.dublybank.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Integer> {

    @Query("select e from Emprestimo e where e.id = :id")
    Emprestimo findByIdE(@Param("id")Integer id);

    @Query("select e from Emprestimo e where e.conta = :conta and e.status = :status")
    List<Emprestimo> findByEmprestimoStatusByUser(@Param("conta")Conta conta, @Param("status")StatusEmprestimo statusEmprestimo);



}
