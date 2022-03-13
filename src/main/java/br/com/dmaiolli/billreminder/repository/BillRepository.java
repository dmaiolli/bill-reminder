package br.com.dmaiolli.billreminder.repository;

import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.model.BillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByBillTypeAndIsPaidFalse(BillType billType);

    @Query(value = "select * from tb_bill bill where bill.is_paid = false", nativeQuery = true)
    List<Bill> findAllIsNotPaid();
}
