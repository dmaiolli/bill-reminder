package br.com.dmaiolli.billreminder.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "TB_USERS")
public class UserAccount {

    @Id
    private Long id;

    @Column(name = "account_name")
    private String name;

    @Column(name = "created_at")
    private Timestamp registrationDate = Timestamp.from(Instant.now());

    @OneToMany(mappedBy = "userAccount")
    private List<Bill> bills;

    public UserAccount(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserAccount build() {
            return new UserAccount(id, name);
        }
    }
}
