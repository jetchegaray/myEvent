package com.je.enterprise.mievento.domain.transformer;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class TransformerList<D, A> {

	private Transformer<D, A> transformer;

	public TransformerList(Transformer<D, A> transformer) {
		this.transformer = transformer;
	}

	public List<A> transformDomainToApi(List<D> domainObjects) {
		List<A> apiObjects = Lists.newArrayList();
		
		try {
			Preconditions.checkNotNull(domainObjects);
			for (D d : domainObjects) {
				try{	
					A a = transformer.transformDomainToApi(d);
					Validate.notNull(d);
					apiObjects.add(a);
				}catch(Exception e){
					continue;
				}
			}
		return apiObjects;
		} catch (NullPointerException ex) {
			return apiObjects;
		}
	}

	public List<D> transformApiToDomain(List<A> apiObjects) {
		List<D> domainObjects = Lists.newArrayList();
		try {
			Preconditions.checkNotNull(domainObjects);

			for (A a : apiObjects) {
				try{
					D d = transformer.transformApiToDomain(a);
					Validate.notNull(d);
					domainObjects.add(d);
				}catch(Exception e){
					continue;
				}
			}
			return domainObjects;

		} catch (NullPointerException ex) {
			return domainObjects;
		}
	}

}
