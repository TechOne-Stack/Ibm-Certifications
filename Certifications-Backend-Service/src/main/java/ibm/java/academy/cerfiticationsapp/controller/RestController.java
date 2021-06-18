package ibm.java.academy.cerfiticationsapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.repository.CertificationJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;

@Controller
public class RestController {

    @Autowired 
    UserJpaRepository userJpaRepository;
    
    @Autowired
    CertificationJpaRepository certificationJpaRepository;

    @GetMapping("/all-users")
    @ResponseBody
    public List<User> users() {
        return userJpaRepository.findAll();
    }

    @PostMapping(path = "/add-user", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userJpaRepository.save(user);
    }

    @DeleteMapping("/delete-user")
    @ResponseBody
    public void deleteUser(@RequestParam("id") Long id) {
        userJpaRepository.deleteAllById(Arrays.asList(id));
    }

    @PostMapping(path = "/update-certificate", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Certification updateCertificate(@RequestBody Certification newCertification) {
        Optional<Certification> certOpt = certificationJpaRepository.findById(newCertification.getId());
        Certification oldCertification = certOpt.get();
        oldCertification.setName(newCertification.getName());
        certificationJpaRepository.save(oldCertification);
        return newCertification;
    }

    
}
