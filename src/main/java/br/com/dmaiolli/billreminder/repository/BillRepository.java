package br.com.dmaiolli.billreminder.repository;

import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.model.BillType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByBillTypeAndIsNotPaid(BillType billType);
}
