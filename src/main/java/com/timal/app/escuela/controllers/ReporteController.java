package com.timal.app.escuela.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timal.app.escuela.dao.IAlumnosDao;
import com.timal.app.escuela.dtos.AlumnoCreateDTO;
import com.timal.app.escuela.dtos.AlumnoListDTO;
import com.timal.app.escuela.dtos.ObjResponse;
import com.timal.app.escuela.models.Alumno;
import com.timal.app.escuela.services.IService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/reporte")
@CrossOrigin(origins = "*")
public class ReporteController {
	@Autowired
	private IAlumnosDao alumnosDao;
	@GetMapping
	public void getListaMaterias() throws JRException {
		String filepath = "C:\\Users\\maste\\Downloads\\buenoEclipse\\workspace\\PruebaTecnicaJavier\\src\\main\\resources\\Alumnos.jrxml";
		Map<String, Object> parameters = new HashMap<>();
		//parameters.put("id_t_usuarios", "2");
		List<Alumno> alumnos = alumnosDao.findAll();
		JRBeanCollectionDataSource d = new JRBeanCollectionDataSource(alumnos);
		JasperReport reporte = JasperCompileManager.compileReport(filepath);
		JasperPrint print = JasperFillManager.fillReport(reporte, parameters, d);
		
		JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\maste\\Downloads\\buenoEclipse\\workspace\\PruebaTecnicaJavier\\src\\main\\resources\\reporte.pdf");
	}
}
