package br.com.dmaiolli.billreminder.service;

import br.com.dmaiolli.billreminder.model.UserAccount;
import br.com.dmaiolli.billreminder.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public void registerNewAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    public boolean accountAlreadyExistsById(Long id) {
        return userAccountRepository.userAccountExistsById(id);
    }

}
