/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class RaportsService {
    
    @Autowired
    private DataSource dataSource;
    
    private final String RAPORTS_DIR = "C:\\Users\\Eustass\\Documents\\NetBeansProjects\\FoodNetServer\\raports\\";
    private final String EXTENSION = ".jrxml";
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public byte[] getRaportFile(String filename) {
        Map<String, Object> parameters = new HashMap<>();
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(RAPORTS_DIR + filename + EXTENSION);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Eustass\\Desktop\\Test.pdf");
            byte[] generatedPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            System.out.println("Generated PDF length: " + generatedPdf.length);
            return generatedPdf;
        } catch (JRException ex) {
            System.out.println("JRE EXCEPTION: " + ex.getMessage());
        }
        return null;
    }
}
