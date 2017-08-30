package com.meeno.ext.product.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeno.ext.product.goods.dao.SourceDao;
import com.meeno.ext.product.goods.entity.Source;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;

@Service
public class SourceServiceImpl extends BaseDaoImpl implements SourceService {
	@Autowired
	private SourceDao sourceDao;

	@Override
	public CutPageBean findbyPartsList(CutPageBean pageBean) {
		return this.sourceDao.findbyPartsList(pageBean);
	}

	@Override
	public void addSource(String sourceName) {
		MeenoAssert.notNull(sourceName, "货源名称不能为空");
		Source existSe = this.sourceDao.findSourceByName(sourceName);
		if (existSe != null) {
			throw new BusinessRuntimeException("货源已存在");
		}
		Source source = new Source();
		source.setSourceName(sourceName);
		source.setStatus(StringContant.STATUS_AVA);
		this.sourceDao.save(source);
	}

	@Override
	public void editSource(Long id, String sourceName) {
		MeenoAssert.notNull(sourceName, "货源名称不能为空");
		MeenoAssert.notNull(id, ProductError.ID_NULL);
		Source source = this.sourceDao.get(Source.class, id);
		MeenoAssert.notNull(source, ProductError.SOURCE_NOT_EXIST);
		if (source.getSourceName().equals(sourceName)) {
			throw new BusinessRuntimeException(ProductError.NO_MODIFICATION);
		}
		Source existSe = this.sourceDao.findSourceByName(sourceName);
		if (existSe != null) {
			throw new BusinessRuntimeException("货源已存在");
		}
		source.setSourceName(sourceName);
		this.sourceDao.update(source);
	}
}
