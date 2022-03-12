package br.com.dmaiolli.billreminder.service;

import br.com.dmaiolli.billreminder.exception.BillNotFoundException;
import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.model.BillType;
import br.com.dmaiolli.billreminder.repository.BillRepository;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public void newBill(Bill bill) {
        billRepository.save(bill);
    }

    public List<Bill> findByBillTypeAndBillIsNotPaid(BillType billType) {
        return billRepository.findAllByBillTypeAndIsNotPaid(billType);
    }

    public Bill findById(Long id) {
        Optional<Bill> optionalBill = billRepository.findById(id);

        if(optionalBill.isEmpty()) {
            throw new BillNotFoundException();
        }

        return optionalBill.get();
    }

    public void setBillPaid(Long id) {
        Bill billToSetPaid = this.findById(id);

        billToSetPaid.setPaid(true);

        billRepository.save(billToSetPaid);
    }

}