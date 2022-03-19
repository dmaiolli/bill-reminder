package br.com.dmaiolli.billreminder.repository;

import br.com.dmaiolli.billreminder.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query(nativeQuery = true,
        value = "SELECT EXISTS(SELECT * FROM TB_USERS WHERE ID = :id )")
    boolean userAccountExistsById(@Param("id") Long id);
}
