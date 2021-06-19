package ibm.java.academy.cerfiticationsapp.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.repository.CertificationJpaRepository;

@Controller
public class CertificationController {

    @Autowired
    CertificationJpaRepository certificationJpaRepository;

    @PostMapping(path = "/update-certificate", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Certification updateCertificate(@RequestBody Certification newCertification) {
        Optional<Certification> certOpt = certificationJpaRepository.findById(newCertification.getId());
        Certification oldCertification = certOpt.get();

        oldCertification.setName(newCertification.getName());
        oldCertification.setUrl(newCertification.getUrl());
        oldCertification.setCurrency(newCertification.getCurrency());
        oldCertification.setPrice(newCertification.getPrice()  != null ? newCertification.getPrice() : new BigDecimal(0.0));

        certificationJpaRepository.save(oldCertification);
        return newCertification;
    }

}
