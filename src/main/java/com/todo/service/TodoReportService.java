package com.todo.service;

import com.todo.entity.Todo;
import com.todo.repository.TodoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoReportService {

    @Autowired
    private TodoRepository todoRepo;

    public String exportTodoReport(String reportFormat) throws FileNotFoundException, JRException {

        List<Todo> todos = todoRepo.findAll();

        String path = "D:\\Clone-API\\JasperReport";

        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Todo_App.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(todos);
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("createdBy", "todo_app");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);

        if (reportFormat.equalsIgnoreCase("pdf"))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\todo_sample.pdf");
        }
        if (reportFormat.equalsIgnoreCase("html"))
        {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\todo_sample.html");
        }

        return "Report generated successfully !!";
    }
}
