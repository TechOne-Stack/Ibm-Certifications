package ibm.java.academy.cerfiticationsapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.model.Skill;
import ibm.java.academy.cerfiticationsapp.repository.CertificationJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.SkillJpaRepository;
import ibm.java.academy.cerfiticationsapp.wrapper.updateCertificateWraper;

@Controller
public class CertificationController {

    @Autowired
    CertificationJpaRepository certificationJpaRepository;

    @Autowired
    SkillJpaRepository skillJpaRepository;

    @PostMapping(path = "/update-certificate", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Certification updateCertificate(@RequestBody updateCertificateWraper param) {
        Certification newCertification = param.getCertification();
        List<Long> skills = param.getSkills();

        System.out.println(newCertification);
        System.out.println(skills);

        Optional<Certification> certOpt = certificationJpaRepository.findById(newCertification.getId());
        Certification oldCertification = certOpt.get();

        oldCertification.setName(newCertification.getName());
        oldCertification.setUrl(newCertification.getUrl());
        oldCertification.setCurrency(newCertification.getCurrency());
        oldCertification.setPrice(newCertification.getPrice()  != null ? newCertification.getPrice() : new BigDecimal(0.0));

        

        List<Skill> skillList = new ArrayList<Skill>();
        for(Long index : skills){
            Optional<Skill> newSkill  = skillJpaRepository.findById(index);
            skillList.add(newSkill.get());
        }
        oldCertification.setSkills(skillList);

        certificationJpaRepository.save(oldCertification);
        return newCertification;
    }

}
