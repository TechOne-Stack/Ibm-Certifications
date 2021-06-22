package ibm.java.academy.cerfiticationsapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.Skill;
import ibm.java.academy.cerfiticationsapp.repository.SkillJpaRepository;

@Controller
public class SkillController {
    @Autowired
    SkillJpaRepository skillJpaRepository;

    @PostMapping(path = "/update-skill", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Skill updateSkill(@RequestBody Skill skill) {
   
        Optional<Skill> skillOpt = skillJpaRepository.findById(skill.getId());
        Skill oldSkill = skillOpt.get();

        oldSkill.setName(skill.getName() != null ? skill.getName() : oldSkill.getName());
        oldSkill.setDescription(skill.getDescription() != null ? skill.getDescription() : oldSkill.getDescription());

        skillJpaRepository.save(oldSkill);
        return skill;
    }

}
