package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.utils.FileHelper;
import com.je.enterprise.mievento.domain.utils.LoadFile;

@Component
public class CitiesBlackList implements LoadFile{

	private static final String SEPARATOR = "=";
	private FileHelper fileHelper;
	private BufferedWriter bufferedWriter;
	
	@PostConstruct
	public void load(){
		this.fileHelper = new FileHelper("com/je/enterprise/mievento/domain/apiPlaces/nonresults.txt");
		this.bufferedWriter = fileHelper.initToWrite();		
	}
	
	
	public Set<CityEntity> getAllCitiesNoResult(CountryCode countryCode){
		
		List<String> lines = this.fileHelper.initToRead();
		Map<String, List<String>> citiesByCountry = Maps.newLinkedHashMap();
		
		for (String line : lines) {
			List<String> keyValue = Arrays.asList(line.split(SEPARATOR));
			
			String key = keyValue.get(0);
			if (citiesByCountry.containsKey(key)) {
				citiesByCountry.get(key).add(keyValue.get(1));
			}else{
				citiesByCountry.put(key, Lists.newArrayList(keyValue.get(1)));
			}
		}
		
		citiesByCountry.get(countryCode);
			
		return Sets.newLinkedHashSet(Lists.transform(citiesByCountry.get(countryCode), new Function<String, CityEntity>(){
			@Override
			public CityEntity apply(String input) {
				return new CityEntity(input, StringUtils.EMPTY, StringUtils.EMPTY);
			}
			
		}));
	}
	
	
	
	public void setCityNoResult(CountryCode countryCode,CityEntity city){
			try {
				bufferedWriter.write(StringUtils.join(Arrays.asList(countryCode,city.getName()),SEPARATOR));
				bufferedWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	
}
