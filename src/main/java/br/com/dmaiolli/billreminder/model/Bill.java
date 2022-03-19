package br.com.dmaiolli.billreminder.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TB_BILL")
@DynamicUpdate
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private BillType billType;

    @Enumerated(EnumType.STRING)
    private Responsavel responsible;

    private boolean isRecurrent;

    private boolean isPaid;

    @ManyToOne
    private UserAccount userAccount;

    public Bill(Date dueDate, BillType billType, Responsavel responsible, boolean isRecurrent, UserAccount userAccount) {
        this.dueDate = dueDate;
        this.billType = billType;
        this.responsible = responsible;
        this.isRecurrent = isRecurrent;
        this.userAccount = userAccount;
    }

    public static class Builder {
        private Long id;
        private Date dueDate;
        private BillType billType;
        private Responsavel responsible;
        private Boolean isRecurrent;
        private Boolean isPaid;
        private UserAccount userAccount;

        public Bill.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Bill.Builder withDueDate(Date date) {
            this.dueDate = date;
            return this;
        }

        public Bill.Builder withBillType(BillType billType) {
            this.billType = billType;
            return this;
        }

        public Bill.Builder withResponsible(Responsavel responsible) {
            this.responsible = responsible;
            return this;
        }

        public Bill.Builder withIsRecurrent(Boolean isRecurrent) {
            this.isRecurrent = isRecurrent;
            return this;
        }

        public Bill.Builder withIsPaid(Boolean isPaid) {
            this.isPaid = isPaid;
            return this;
        }

        public Bill.Builder withUser(UserAccount userAccount) {
            this.userAccount = userAccount;
            return this;
        }

        public Bill build() {
            return new Bill(dueDate, billType, responsible, isRecurrent, userAccount);
        }
    }

}
