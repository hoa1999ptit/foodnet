/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
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
    
    private static final String RAPORTS_DIR;
    private static final String EXTENSION = ".jrxml";
    
    static {
        RAPORTS_DIR = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "raports").toString();
        System.out.println("RAPORTS_DIR:" + RAPORTS_DIR);
    }
    
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
            JasperReport jasperReport = JasperCompileManager.compileReport(Paths.get(RAPORTS_DIR, filename + EXTENSION).toString());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, getConnection());
            byte[] generatedPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            System.out.println("Generated PDF length: " + generatedPdf.length);
            return generatedPdf;
        } catch (JRException ex) {
            System.out.println("JRE EXCEPTION: " + ex.getMessage());
        }
        return null;
    }
}
