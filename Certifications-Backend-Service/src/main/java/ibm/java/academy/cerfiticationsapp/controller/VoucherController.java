package ibm.java.academy.cerfiticationsapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.model.Voucher;
import ibm.java.academy.cerfiticationsapp.repository.VoucherJpaRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
public class VoucherController {

    @Autowired
    private VoucherJpaRepository voucherJpaRepository;

    @GetMapping(path = "/all-vouchers")
    public List<Voucher> getVouchers(){
        return voucherJpaRepository.findAll();
    }

    @PostMapping(path = "/add-voucher", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Voucher addVoucher(@RequestBody Voucher voucher) {
        return voucherJpaRepository.save(voucher);
    }

    @GetMapping(path = "/vouchers/{id}/certification")
    @ResponseBody
    public Optional<Certification> getCertificationFromVoucherById(@PathVariable("id") Long id){
        Optional<Voucher> voucherById = voucherJpaRepository.findById(id);
        if(voucherById.isPresent()){
            return Optional.of(voucherById.get().getCertification());
        }
        return Optional.empty();
    }

    @GetMapping(path = "/vouchers/{id}/user")
    @ResponseBody
    public Optional<User> getUserFromVoucherById(@PathVariable("id") Long id){
        Optional<Voucher> voucherById = voucherJpaRepository.findById(id);
        if(voucherById.isPresent()){
            return Optional.of(voucherById.get().getUser());
        }
        return Optional.empty();
    }
    

}