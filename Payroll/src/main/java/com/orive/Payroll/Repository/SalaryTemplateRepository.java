package com.orive.Payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.orive.Payroll.Entity.SalaryTemplateEntity;

public interface SalaryTemplateRepository extends JpaRepository<SalaryTemplateEntity, String>{

}
