package br.com.beta.dublybank.repository;

import br.com.beta.dublybank.model.Deposito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepositoRepository extends JpaRepository<Deposito,Integer> {

    @Query("select d from Deposito d join fetch d.conta where d.conta.user.username = :username")
    List<Deposito> findDepositoByUsername(@Param("username")String username, Sort sort);

}
