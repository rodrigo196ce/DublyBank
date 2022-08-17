package br.com.beta.dublybank.repository;

import br.com.beta.dublybank.model.Emprestimo;
import br.com.beta.dublybank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    // Retorna apenas o User sem nenhum relacionamento.
    @Query("select u from User u where u.username = :username")
    User findUser(@Param("username")String username);

    // Retorna o User e a Conta.
    @Query("select u from User u join fetch u.conta where u.username = :username")
    User findUserAndConta(@Param("username")String username);

    @Query("select u from User u join fetch u.conta where u.cpf = :chave or " +
            "u.email = :chave or u.conta.chavePixAleatoria = :chave")
    User findUserContainChavePix(@Param("chave")String chave);

    @Query("select u from User u where u.telefone = :telefone")
    User findValueTelefone(@Param("telefone")String telefone);


}
