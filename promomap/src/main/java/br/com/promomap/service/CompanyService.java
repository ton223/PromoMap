/*
 * @(#)CompanyService.java 10 de mai de 2017 - 23:20:09
 *
 */
package br.com.promomap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promomap.beans.persistence.Company;
import br.com.promomap.beans.persistence.Location;
import br.com.promomap.beans.persistence.User;
import br.com.promomap.beans.transport.CompanyObject;
import br.com.promomap.beans.transport.TaskObject;
import br.com.promomap.dao.CompanyDAO;
import br.com.promomap.dao.LocationDAO;
import br.com.promomap.utils.Utils;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Service
public class CompanyService {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired LocationDAO locationDAO;
	
	public TaskObject create(User user, CompanyObject companyO) {
		TaskObject task = new TaskObject();
		try {
			Company company = new Company();
			company.setSuperId(Utils.generateRandomUUID());
			company.setName(companyO.getName());
			company.setDescription(companyO.getDescription());
			company.setCnpj(companyO.getCnpj());
			company.setEmail(companyO.getEmail());
			company.setPhone(companyO.getPhone());
			company.setCreatedAt(new Date());
			company.setUser(user);
			Location location = new Location();
			location.setSuperId(Utils.generateRandomUUID());
			location.setLat(companyO.getLocation().getLat());
			location.setLng(companyO.getLocation().getLng());
			locationDAO.save(location);
			company.setLocation(locationDAO.findBySuperId(location.getSuperId()));
			companyDAO.save(company);
//			task.setData(company.generateTransportObject());
		} catch (Exception e) {
			task.setSuccess(false);
			task.setErrorMessage(e.getMessage());
		}
		task.setSuccess(true);
		return task;
	}
	
	public TaskObject listByUser(User user) {
		TaskObject task = new TaskObject();
		try {
			List<CompanyObject> companysO = new ArrayList<CompanyObject>();
			List<Company> companys = companyDAO.listByUser(user.getId());
			for(Company c : companys) {
				companysO.add(c.generateTransportObject());
			}
			task.setDataList(companysO);
		} catch (Exception e) {
			task.setSuccess(false);
			task.setErrorMessage(e.getMessage());
		}
		task.setSuccess(true);
		return task;
	}

}
