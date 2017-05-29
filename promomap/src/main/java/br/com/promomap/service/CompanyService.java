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
import br.com.promomap.utils.Utils;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Service
public class CompanyService {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private LocationService locationService;
	
	public Company findBySuperId(String superId) {
		return companyDAO.findBySuperId(superId);
	}
	
	public Company findByLocation(Location location) {
		return this.companyDAO.findByLocationId(location.getId());
	}
	
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
			Location location = this.locationService.create(companyO.getLocation());
			company.setLocation(location);
			companyDAO.save(company);
			task.setData(company.generateTransportObject());
		} catch (Exception e) {
			task.setSuccess(false);
			task.setErrorMessage(e.getMessage());
			return task;
		}
		task.setSuccess(true);
		return task;
	}
	
	public TaskObject delete(String CompanySuperId, User user) {
		TaskObject task = new TaskObject();
		Company company = this.companyDAO.findBySuperId(CompanySuperId);
		if(!company.getUser().getSuperId().equals(user.getSuperId())) {
			task.setSuccess(false);
			task.setErrorMessage("Você não é dono desta company");
			return task;
		}
		this.locationService.delete(company.getLocation());
		company.setDeleted(true);
		company.setDeletedDate(new Date());
		this.companyDAO.save(company);
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
			return task;
		}
		task.setSuccess(true);
		return task;
	}
	
	public TaskObject edit(User user, String companySuperId, CompanyObject companyEdited) {
		TaskObject task = new TaskObject();
		Company company = this.companyDAO.findBySuperId(companySuperId);
		if(!company.getUser().getSuperId().equals(user.getSuperId())) {
			task.setSuccess(false);
			task.setErrorMessage("Você não pode editar um estabelecimanto de outra pessoa");
			return task;
		}
		company.setName(companyEdited.getName());
		company.setCnpj(companyEdited.getCnpj());
		company.setDescription(companyEdited.getDescription());
		company.setEmail(companyEdited.getEmail());
		company.setPhone(companyEdited.getPhone());
		
		this.companyDAO.save(company);
		task.setSuccess(true);
		return task;
	}
	
	public TaskObject getBySuperId(User user, String companySuperId) {
		TaskObject task = new TaskObject();
		try {
			Company company = this.companyDAO.findBySuperId(companySuperId);
			if(!company.getUser().getSuperId().equals(user.getSuperId())) {
				task.setSuccess(false);
				task.setErrorMessage("Você não pode listar uma company que não é sua.");
				return task;
			}
			task.setData(company.generateTransportObject());
		} catch(Exception e) {
			task.setSuccess(false);
			task.setErrorMessage(e.getMessage());
			return task;
		}
		task.setSuccess(true);
		return task;
	}

}
