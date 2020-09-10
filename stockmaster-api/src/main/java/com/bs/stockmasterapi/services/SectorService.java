package com.bs.stockmasterapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bs.stockmasterapi.exceptions.SectorException;
import com.bs.stockmasterapi.model.Sector;
import com.bs.stockmasterapi.repositories.SectorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SectorService {

	private final SectorRepository sectorRepository; 

	public void createSector(Sector sector) {
		sectorRepository.save(sector);
	}

	public List<Sector> getAllSector() {
		return sectorRepository.findAll();
	}

	public Sector getSector(Long id) {
		return sectorRepository.findById(id)
				.orElseThrow(() -> new SectorException("No Sector found with id: " + id.toString()));
	}

	public String deleteSector(Long id) {
		sectorRepository.delete(getSector(id));
		return "Sector deleted sucessfully";
	}

}
