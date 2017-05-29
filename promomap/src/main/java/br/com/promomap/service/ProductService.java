/*
 * @(#)ProductService.java 20 de mai de 2017 - 15:51:48
 *
 */
package br.com.promomap.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promomap.beans.persistence.Company;
import br.com.promomap.beans.persistence.Product;
import br.com.promomap.beans.persistence.User;
import br.com.promomap.beans.transport.CompanyObject;
import br.com.promomap.beans.transport.LocationObject;
import br.com.promomap.beans.transport.ProductObject;
import br.com.promomap.beans.transport.TaskObject;
import br.com.promomap.dao.ProductDAO;
import br.com.promomap.model.enums.CategoryEnum;
import br.com.promomap.model.enums.TravelModeEnum;
import br.com.promomap.model.graph.Graph;
import br.com.promomap.utils.Utils;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private DistanceService distanceService;

	public TaskObject listByCompany(String companySuperId, User user) {
		TaskObject task = new TaskObject();
		Company company = this.companyService.findBySuperId(companySuperId);
		// if(!company.getUser().getSuperId().equals(user.getSuperId())) {
		// task.setSuccess(false);
		// task.setErrorMessage("Você não pode listar produtos do
		// estabelecimento de outra pessoa.");
		// return task;
		// }
		List<Product> list = this.productDAO.listByCompany(company.getId());
		List<ProductObject> products = new ArrayList<ProductObject>();
		list.forEach(p -> products.add(p.generateTransportObject()));
		task.setDataList(products);
		task.setSuccess(true);
		return task;
	}

	public TaskObject create(String companySuperId, ProductObject productO, User user) {
		TaskObject task = new TaskObject();
		Company company = this.companyService.findBySuperId(companySuperId);
		if (company == null) {
			task.setSuccess(false);
			task.setErrorMessage("Estabelecimanto inválido");
			return task;
		}
		if (!company.getUser().getSuperId().equals(user.getSuperId())) {
			task.setSuccess(false);
			task.setErrorMessage("Você não pode criar produtos no estabelecimento de outra pessoa.");
			return task;
		}
		Product product = new Product();
		product.setSuperId(Utils.generateRandomUUID());
		product.setImagePublicId(productO.getImagePublicId());
		product.setName(productO.getName());
		product.setDescription(productO.getDescription());
		product.setCode(productO.getCode());
		product.setPrice(productO.getPrice());
		product.setDiscount(productO.getDiscount());
		product.setCategory(CategoryEnum.findFromDescription(productO.getCategory()));
		product.setCreatedAt(new Date());
		product.setCompany(company);
		this.productDAO.save(product);
		task.setData(product.generateTransportObject());
		task.setSuccess(true);
		return task;
	}
	
	public TaskObject listProductsArround(LocationObject origin){
		TaskObject task = new TaskObject();
		task.setInfo("Loading Graph");
		Map<CategoryEnum , List<ProductObject>> productsMap = new HashMap<CategoryEnum , List<ProductObject>>();
		Map<CompanyObject, BigDecimal> distances = new HashMap<CompanyObject, BigDecimal>();
		this.locationService.getLocationsInRadius(origin).forEach(l -> {
			Company company = this.companyService.findByLocation(l);
			for(CategoryEnum category : CategoryEnum.values()) {
				List<ProductObject> products = new ArrayList<ProductObject>();
				this.productDAO.listByCompanyAndCategory(company.getId(), category).forEach(p -> {
					products.add(p.generateTransportObject());
				});
				if(!products.isEmpty()) {
					List<ProductObject> list = productsMap.get(category);
					if(list != null) {
						list.addAll(products);
					} else {
						productsMap.put(category, products);
					}
				}
			}
			try {
				this.distanceService.configure(origin, company.getLocation().generateTransportObject(), TravelModeEnum.DRIVING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			distances.put(company.generateTransportObject(), this.distanceService.getDistance());
		});
		Graph graph = new Graph();
		graph.mount(productsMap, distances);
		task.setData(graph);
		task.setSuccess(true);
		return task;
	}

	public TaskObject delete(String productSuperId, User user) {
		TaskObject task = new TaskObject();
		Product product = this.productDAO.findBySuperId(productSuperId);
		if (!product.getCompany().getUser().getSuperId().equals(user.getSuperId())) {
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
