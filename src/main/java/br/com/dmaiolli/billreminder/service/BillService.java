package br.com.dmaiolli.billreminder.service;

import br.com.dmaiolli.billreminder.exception.BillAlreadyPaidException;
import br.com.dmaiolli.billreminder.exception.BillNotFoundException;
import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.model.BillType;
import br.com.dmaiolli.billreminder.repository.BillRepository;
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
        return billRepository.findAllByBillTypeAndIsPaidFalse(billType);
    }

    public List<Bill> findAllBillIsNotPaid() {
        return billRepository.findAllIsNotPaid();
    }

    public Bill findById(Long id) {
        Optional<Bill> optionalBill = billRepository.findById(id);

        if(optionalBill.isEmpty()) {
            throw new BillNotFoundException();
        }

        return optionalBill.get();
    }

    public Bill setBillPaid(Long id) throws BillAlreadyPaidException{
        Bill billToSetPaid = this.findById(id);

        if(billToSetPaid.isPaid()) {
            throw new BillAlreadyPaidException();
        }

        billToSetPaid.setPaid(true);

        return billRepository.save(billToSetPaid);
    }

    public void registerNewBill(Bill bill) {
        billRepository.save(bill);
    }

}
