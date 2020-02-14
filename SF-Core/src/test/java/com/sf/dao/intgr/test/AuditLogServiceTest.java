package com.sf.dao.intgr.test;

import com.sf.beans.AuditLogDto;
import com.sf.config.SalesFinderCoreContext;
import com.sf.model.AuditLog;
import com.sf.service.AuditLogService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@Ignore
public class AuditLogServiceTest {

    @Test
    public void findLastEntryTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        AuditLogService auditLogService = context.getBean(AuditLogService.class);

        System.out.println(auditLogService.findAllAudits());
    }

    @Test
    public void saveAuditTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        AuditLogService auditLogService = context.getBean(AuditLogService.class);

        AuditLogDto auditLogDto = new AuditLogDto();
        auditLogDto.setProductId(100L);
        auditLogDto.setUserId(1L);
        auditLogDto.setActionId(1L);
        auditLogDto.setDate("2020-02-13");

        auditLogService.saveAudit(auditLogDto);
    }

    @Test
    public void findAuditsByActionAndDateTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        AuditLogService auditLogService = context.getBean(AuditLogService.class);
        List<AuditLog> auditLogs = auditLogService.findAuditsByActionAndDate(1L, "2020-02-13");

        auditLogs.forEach(e -> System.out.println(e.getProductId()));
    }
}
