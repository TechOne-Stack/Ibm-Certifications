package ibm.java.academy.cerfiticationsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.model.Skill;
import ibm.java.academy.cerfiticationsapp.model.Voucher;
import ibm.java.academy.cerfiticationsapp.repository.CertificationJpaRepository;
import ibm.java.academy.cerfiticationsapp.service.CertificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CertificationController {

    @Autowired 
    private CertificationService certificationService;
    @Autowired 
    private CertificationJpaRepository certificationJpaRepository;

    @DeleteMapping("/certifications/{id}")
    @ResponseBody
    public void deleteCertifications(@PathVariable("id") Long id) {
        certificationService.deleteCertification(id);
    }
    
    @GetMapping(value="/certifications")
    @ResponseBody
    public List<Certification> getCertifications() {
        return certificationJpaRepository.findAll();
    }

    @GetMapping(value="/certifications/{id}/skills")
    @ResponseBody
    public List<Skill> getCertifiationSkills(@PathVariable("id") Long id) {
        return certificationService.getCertificationSkills(id);
    }

    @GetMapping(value="/certifications/{id}/vouchers")
    @ResponseBody
    public List<Voucher> getCertifiationVouchers(@PathVariable("id") Long id) {
        return certificationService.getCertificationVouchers(id);
    }

    
}

