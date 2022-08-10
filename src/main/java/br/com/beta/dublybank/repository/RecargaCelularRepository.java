package br.com.beta.dublybank.repository;

import br.com.beta.dublybank.model.RecargaCelular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecargaCelularRepository extends JpaRepository<RecargaCelular,Integer> {
}
