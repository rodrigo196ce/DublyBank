package br.com.beta.dublybank.repository;

import br.com.beta.dublybank.model.Deposito;
import br.com.beta.dublybank.model.TransferenciaPix;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferenciaPixRepository extends JpaRepository<TransferenciaPix,Integer>{

    @Query("select t from TransferenciaPix t join fetch t.conta where t.conta.user.username = :username")
    List<TransferenciaPix> findTransferenciaByUsername(@Param("username")String username, Sort sort);

}
