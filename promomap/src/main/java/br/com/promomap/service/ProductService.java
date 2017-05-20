/*
 * @(#)ProductService.java 20 de mai de 2017 - 15:51:48
 *
 */
package br.com.promomap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promomap.beans.persistence.Company;
import br.com.promomap.beans.persistence.Product;
import br.com.promomap.beans.persistence.User;
import br.com.promomap.beans.transport.ProductObject;
import br.com.promomap.beans.transport.TaskObject;
import br.com.promomap.dao.CompanyDAO;
import br.com.promomap.dao.ProductDAO;
import br.com.promomap.utils.Utils;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired 
	private CompanyDAO companyDAO;

	public TaskObject listByCompany(String companySuperId, User user) {
		TaskObject task = new TaskObject();
		Company company = this.companyDAO.findBySuperId(companySuperId);
//		if(!company.getUser().getSuperId().equals(user.getSuperId())) {
//			task.setSuccess(false);
//			task.setErrorMessage("Você não pode listar produtos do estabelecimento de outra pessoa.");
//			return task;
//		}
		List<Product> list = this.productDAO.listByCompany(company.getId());
		List<ProductObject> products = new ArrayList<ProductObject>();
		list.forEach(p -> products.add(p.generateTransportObject()));
		task.setDataList(products);
		task.setSuccess(true);
		return task;
	}
	
	public TaskObject create(String companySuperId, ProductObject productO, User user) {
		TaskObject task = new TaskObject();
		Company company = this.companyDAO.findBySuperId(companySuperId);
		if(company == null) {
			task.setSuccess(false);
			task.setErrorMessage("Estabelecimanto inválido");
			return task;
		}
		if(!company.getUser().getSuperId().equals(user.getSuperId())) {
			task.setSuccess(false);
			task.setErrorMessage("Você não pode criar produtos no estabelecimento de outra pessoa.");
			return task;
		}
		Product product = new Product();
		product.setSuperId(Utils.generateRandomUUID());
		product.setName(productO.getName());
		product.setDescription(productO.getDescription());
		product.setCode(productO.getCode());
		product.setPrice(productO.getPrice());
		product.setCreatedAt(new Date());
		product.setCompany(company);
		this.productDAO.save(product);
		task.setData(product.generateTransportObject());
		task.setSuccess(true);
		return task;
	}
	
	public TaskObject delete(String productSuperId, User user) {
		TaskObject task = new TaskObject();
		Product product = this.productDAO.findBySuperId(productSuperId);
		if(!product.getCompany().getUser().getSuperId().equals(user.getSuperId())) {
			task.setSuccess(false);
			task.setErrorMessage("Você não pode deletar produtos do estabelecimento de outra pessoa.");
			return task;
		}
		product.setDeleted(true);
		product.setDeletedDate(new Date());
		this.productDAO.save(product);
		task.setSuccess(true);
		return task;
	}
}
