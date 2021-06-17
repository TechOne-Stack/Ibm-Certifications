package ibm.java.academy.cerfiticationsapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.model.Voucher;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.VoucherJpaRepository;


@Service
public class VoucherService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private VoucherJpaRepository voucherJpaRepository;
    
    public Voucher assignVoucherToUser(Long voucherId, Long userId) {
        Voucher result = null;
        Optional<Voucher> voucherOpt = voucherJpaRepository.findById(voucherId);
        Optional<User> userOpt = userJpaRepository.findById(userId);
        if(voucherOpt.isPresent() && userOpt.isPresent()) {
            result = voucherOpt.get();
            result.setUser(userOpt.get());
            result = voucherJpaRepository.save(result);
        }
        return result;
    }

    public void deleteVoucher(Long id) {
        voucherJpaRepository.deleteById(id);
    }
}
