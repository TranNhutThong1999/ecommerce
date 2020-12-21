package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.AddressDTO;
import com.tmdt.dto.DistrictDTO;
import com.tmdt.dto.ProvincialDTO;
import com.tmdt.dto.WardDTO;
import com.tmdt.entity.Address;
import com.tmdt.entity.District;
import com.tmdt.entity.Provincial;
import com.tmdt.entity.Ward;
import com.tmdt.repository.DistrictRepository;
import com.tmdt.repository.ProvincialRepository;
import com.tmdt.repository.WardRepository;

@Component
public class AddressConverter implements IConverter<Address, AddressDTO>{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired 
	private ProvincialRepository provincialRepository;
	
	public AddressDTO toDTO(Address u) {
		AddressDTO dto = modelMapper.map(u, AddressDTO.class);
		dto.setWard(modelMapper.map(u.getWard(), WardDTO.class));
		dto.setDistrict(modelMapper.map(u.getDistrict(), DistrictDTO.class));
		dto.setProvincial(modelMapper.map(u.getProvincial(), ProvincialDTO.class));
		return dto;
	}

	public Address toEntity(AddressDTO v) {
		Address en = modelMapper.map(v, Address.class);
		en.setWard(wardRepository.findOneById(v.getWard().getId()));
		en.setDistrict(districtRepository.findOneById(v.getDistrict().getId()));
		en.setProvincial(provincialRepository.findOneById(v.getProvincial().getId()));
		return en;
	}

	
}
