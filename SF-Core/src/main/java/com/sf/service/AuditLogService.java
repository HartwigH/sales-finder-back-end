package com.sf.service;


import com.sf.beans.AuditLogDto;
import com.sf.dao.AuditLogDao;
import com.sf.model.AuditLog;
import com.sf.util.BeanMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuditLogService {

    @Autowired
    AuditLogDao auditLogDao;

    public List<AuditLog> findAllAudits() {return auditLogDao.findAll();}

    public void saveAudit(AuditLogDto auditLogDto) {
        AuditLog auditLog = BeanMappingUtils.dto2Model(auditLogDto);

        auditLogDao.save(auditLog);
    }

    public List<AuditLog> findAuditsByActionAndDate(Long actionTaken, String givenDate) {
        return auditLogDao.findByActionIdAndDate(actionTaken, givenDate);
    }
}
