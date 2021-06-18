package ibm.java.academy.cerfiticationsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.Voucher;
import ibm.java.academy.cerfiticationsapp.repository.VoucherJpaRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
public class VoucherController {

    @Autowired
    private VoucherJpaRepository voucherJpaRepository;

    @GetMapping("/all-vouchers")
    public List<Voucher> getVouchers(){
        return voucherJpaRepository.findAll();
    }

    @PostMapping(path = "/add-voucher", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Voucher addVoucher(@RequestBody Voucher voucher) {
        return voucherJpaRepository.save(voucher);
    }
    
}