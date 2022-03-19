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

}
